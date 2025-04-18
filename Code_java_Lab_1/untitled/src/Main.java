import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int minDuration = 4;
        int maxDuration = 10;
        int minStep = 2;
        int maxStep = 6;

        int threadCount = 4;

        runTestWithThreads(threadCount, minDuration, maxDuration, minStep, maxStep);
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

        NumberCalculator[] calculators = new NumberCalculator[threadCount];
        ThreadController controllerThread = new ThreadController(threadDurations, calculators);

        for (int i = 0; i < threadCount; i++) {
            calculators[i] = new NumberCalculator(i, incrementSteps[i]);
            calculators[i].start();
        }

        controllerThread.start();
    }
}

