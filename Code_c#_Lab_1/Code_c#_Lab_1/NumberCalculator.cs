using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Code_c__Lab_1
{
    class NumberCalculator
    {
        private int threadId;
        private int increment;
        private volatile bool stopped = false;
        private CountdownEvent startLatch;

        public NumberCalculator(int threadId, int increment, CountdownEvent startLatch)
        {
            this.threadId = threadId;
            this.increment = increment;
            this.startLatch = startLatch;
        }

        public void SetStopped(bool stopped)
        {
            this.stopped = stopped;
        }

        public void Run()
        {
            startLatch.Wait(); // Очікуємо сигналу запуску

            long totalSum = 0;
            long termsCount = 0;

            while (!stopped)
            {
                totalSum += increment;
                termsCount++;
            }

            Console.WriteLine($"Thread ID: {threadId}, Sum: {totalSum}, Terms: {termsCount}, Step: {increment}");
        }
    }
}
