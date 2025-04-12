import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        int numberOfThreads = 3;
        int minDuration = 3;     // мінімальна тривалість потоку (сек)
        int maxDuration = 10;    // максимальна тривалість потоку (сек)
        int minStep = 1;         // мінімальний крок додавання
        int maxStep = 5;         // максимальний крок додавання

        int[] threadDurations = generateRandomArray(numberOfThreads, minDuration, maxDuration);
        int[] incrementSteps = generateRandomArray(numberOfThreads, minStep, maxStep);

        System.out.print("Тривалість потоків: ");
        printArray(threadDurations);
        System.out.print("Кроки додавання: ");
        printArray(incrementSteps);

        CountDownLatch launchSignal = new CountDownLatch(1); // Сигнал запуску всіх потоків
        NumberCalculator[] calculators = new NumberCalculator[threadDurations.length];

        for (int i = 0; i < calculators.length; i++) {
            calculators[i] = new NumberCalculator(i, incrementSteps[i], launchSignal);
            calculators[i].start(); // Запуск потоку в стані очікування
        }

        ThreadController controller = new ThreadController(threadDurations, calculators);
        Thread controllerThread = new Thread(controller);

        System.out.println(">>> Початок!");
        controllerThread.start();
        launchSignal.countDown(); // Дати сигнал на запуск всім потокам
    }

    public static int[] generateRandomArray(int size, int min, int max) {
        Random rand = new Random();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = rand.nextInt(max - min + 1) + min;
        }
        return result;
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}

