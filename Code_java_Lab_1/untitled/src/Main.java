import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int threadCount = 8;

        int[] secondsArray = generateArray(threadCount, 10, 30);
        int[] additionsArray = generateArray(threadCount, 1, 10);

        System.out.println("Randomly generated arrays:");
        printArray("Seconds Array", secondsArray);
        printArray("Additions Array", additionsArray);

        /*
        int[] manualSeconds = new int[]{15, 20, 25};
        int[] manualAdditions = new int[]{2, 5, 10};
        */

        /*
        System.out.println("\nManually provided arrays:");
        printArray("Manual Seconds Array", manualSeconds);
        printArray("Manual Additions Array", manualAdditions);
        */

        startThreads(threadCount, secondsArray, additionsArray);
    }

    private static void startThreads(int threadCount, int[] secondsArray, int[] additionsArray) {
        for (int threadId = 0; threadId < threadCount; threadId++) {
            BreakThread breakThread = new BreakThread(secondsArray[threadId]); // створення потоку зі зміною canBreak через secondsArray секунд
            new Thread(breakThread).start();
            new CalculateNum(threadId, breakThread, additionsArray).start();
        }
    }

    public static int[] generateArray(int count, int minValue, int maxValue) {
        Random random = new Random();
        int[] result = new int[count];

        for (int i = 0; i < count; i++) {
            result[i] = random.nextInt(maxValue - minValue + 1) + minValue; // генеруємо в межах
        }

        return result;
    }

    public static void printArray(String name, int[] array) {
        System.out.println(name + ": " + String.join(", ",
                java.util.Arrays.stream(array).mapToObj(String::valueOf).toArray(String[]::new)));
    }
}
