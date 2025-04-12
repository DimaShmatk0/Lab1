using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Code_c__Lab_1
{
    class BreakThread
    {
        private int[] runTimes;
        private NumberCalculator[] workers;

        public BreakThread(int[] runTimes, NumberCalculator[] workers)
        {
            this.runTimes = runTimes;
            this.workers = workers;
        }

        public void Run()
        {
            long startTime = DateTimeOffset.Now.ToUnixTimeMilliseconds();

            bool[] isFinished = new bool[workers.Length];
            int completedCount = 0;

            while (completedCount < workers.Length)
            {
                long currentTime = DateTimeOffset.Now.ToUnixTimeMilliseconds();
                long elapsedSeconds = (currentTime - startTime) / 1000;

                for (int i = 0; i < workers.Length; i++)
                {
                    if (!isFinished[i] && elapsedSeconds >= runTimes[i])
                    {
                        workers[i].SetStopped(true);
                        isFinished[i] = true;
                        completedCount++;
                    }
                }

                Thread.Sleep(200);
            }
        }
    }
