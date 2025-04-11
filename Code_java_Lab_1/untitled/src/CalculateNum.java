public class CalculateNum extends Thread {
    private final int threadId;
    private final BreakThread breakThread;
    private final int additionValue;

    public long getThreadId() {
        return threadId;
    }

    public int getAdditionValue() {
        return additionValue;
    }

    public CalculateNum(int threadId, BreakThread breakThread, int[] additionsArray) {
        this.threadId = threadId;
        this.breakThread = breakThread;
        this.additionValue = additionsArray[threadId];
    }

    @Override
    public void run() {
        long sum = 0;
        long numberOfTerms = 0;
        while (!breakThread.isCanBreak()) {
            sum += additionValue;
            numberOfTerms++;
        }
        System.out.println("Thread id: " + getThreadId() + " Sum: " + sum + " Number of terms: " + numberOfTerms + " Addition Value " + getAdditionValue());
    }
}
