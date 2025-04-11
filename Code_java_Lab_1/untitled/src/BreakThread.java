public class BreakThread implements Runnable {
    private volatile boolean canBreak = false;
    private final int waitSeconds;

    public BreakThread(int waitSeconds) {
        this.waitSeconds = waitSeconds;
    }

    public boolean isCanBreak() {
        return canBreak;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(waitSeconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        canBreak = true;
    }
}
