import java.util.concurrent.CountDownLatch;

class NumberCalculator extends Thread {
    int threadId;
    int increment;
    volatile boolean stopped = false;
    CountDownLatch startLatch;

    public NumberCalculator(int threadId, int increment, CountDownLatch startLatch) {
        this.threadId = threadId;
        this.increment = increment;
        this.startLatch = startLatch;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    @Override
    public void run() {
        try {
            startLatch.await(); // Очікуємо сигналу запуску
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
