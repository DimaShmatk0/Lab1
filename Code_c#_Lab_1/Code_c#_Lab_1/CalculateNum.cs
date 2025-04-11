using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Code_c__Lab_1
{
    public class CalculateNum
    {
        private readonly int threadId;
        private readonly BreakThread breakThread;
        private readonly int additionValue;
        private readonly Thread thread;

        public CalculateNum(int threadId, BreakThread breakThread, int[] additionsArray)
        {
            this.threadId = threadId;
            this.breakThread = breakThread;
            additionValue = additionsArray[threadId];
            thread = new Thread(new ThreadStart(Run));
        }

        public void Start()
        {
            thread.Start();
        }
        public void Run()
        {
            long sum = 0;
            long numberOfTerms = 0;
            while (!breakThread.CanBreak)
            {
                sum += additionValue;
                numberOfTerms++;
            }
            Console.WriteLine($"Thread id: {threadId} Sum: {sum} Number of terms: {numberOfTerms} Addition Value: {additionValue}");
        }
    }
}
