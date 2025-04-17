import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        int minDuration = 4;
        int maxDuration = 10;
        int minStep = 2;
        int maxStep = 6;

        for (int threadCount = 8; threadCount <= 8; threadCount *= 2) {
            runTestWithThreads(threadCount, minDuration, maxDuration, minStep, maxStep);
        }
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

    private static void runTestWithThreads(int threadCount, int minDuration, int maxDuration, int minStep, int maxStep) {
        System.out.println("\nКількість потоків: " + threadCount);

        int[] threadDurations = generateRandomArray(threadCount, minDuration, maxDuration);
        int[] incrementSteps = generateRandomArray(threadCount, minStep, maxStep);

        System.out.print("Тривалість потоків: ");
        printArray(threadDurations);
        System.out.print("Кроки додавання: ");
        printArray(incrementSteps);

        CountDownLatch launchSignal = new CountDownLatch(1);
        NumberCalculator[] calculators = new NumberCalculator[threadCount];

        for (int i = 0; i < threadCount; i++) {
            calculators[i] = new NumberCalculator(i, incrementSteps[i], launchSignal);
            calculators[i].start();
        }

        ThreadController controller = new ThreadController(threadDurations, calculators);
        Thread controllerThread = new Thread(controller);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(">>> Початок!");
        long startTime = System.nanoTime();
        controllerThread.start();
        launchSignal.countDown();

        try {
            controllerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Загальний час виконання: %.2f мс%n", durationMs);
    }
}

