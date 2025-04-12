class ThreadController implements Runnable {
    int[] runTimes;
    NumberCalculator[] workers;

    public ThreadController(int[] runTimes, NumberCalculator[] workers) {
        this.runTimes = runTimes;
        this.workers = workers;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        boolean[] isFinished = new boolean[workers.length];
        int completedCount = 0;

        while (completedCount < workers.length) {
            long currentTime = System.currentTimeMillis();
            long elapsedSeconds = (currentTime - startTime) / 1000;

            for (int i = 0; i < workers.length; i++) {
                if (!isFinished[i] && elapsedSeconds >= runTimes[i]) {
                    workers[i].setStopped(true);
                    isFinished[i] = true;
                    completedCount++;
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
