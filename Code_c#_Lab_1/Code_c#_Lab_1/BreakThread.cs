using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Code_c__Lab_1
{
    public class BreakThread
    {
        private volatile bool canBreak = false;
        private readonly int waitSeconds;

        public BreakThread(int waitSeconds)
        {
            this.waitSeconds = waitSeconds;
        }

        public bool CanBreak
        {
            get { return canBreak; }
        }

        public void Run()
        {
            try
            {
                Thread.Sleep(waitSeconds * 1000);
            }
            catch (ThreadInterruptedException e)
            {
                throw new Exception("Thread was interrupted", e);
            }
            canBreak = true;
        }
    }
}
