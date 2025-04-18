import java.util.concurrent.CountDownLatch;

class NumberCalculator extends Thread {
    int threadId;
    int increment;
    volatile boolean stopped = false;

    public NumberCalculator(int threadId, int increment) {
        this.threadId = threadId;
        this.increment = increment;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    @Override
    public void run() {

        long totalSum = 0;
        long termsCount = 0;

        while (!stopped) {
            totalSum += increment;
            termsCount++;
        }

        System.out.println("Thread ID: " + threadId +
                ", Sum: " + totalSum +
                ", Terms: " + termsCount +
                ", Step: " + increment);
    }
}
