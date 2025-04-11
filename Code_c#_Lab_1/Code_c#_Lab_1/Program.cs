using System;
using System.Threading;

namespace Code_c__Lab_1
{
    public class Program
    {
        public static void Main()
        {
            int threadCount = 8;

            int[] secondsArray = GenerateArray(threadCount, 10, 30);
            int[] additionsArray = GenerateArray(threadCount, 1, 10);

            Console.WriteLine("Randomly generated arrays:");
            Console.WriteLine("Seconds Array: " + string.Join(", ", secondsArray));
            Console.WriteLine("Additions Array: " + string.Join(", ", additionsArray));

            //int[] manualSeconds = new[] { 15, 20, 25 };
            //int[] manualAdditions = new[] { 2, 5, 10 };

            //Console.WriteLine("\nManually provided arrays:"); 
            //PrintArray("Manual Seconds Array", manualSeconds);
            //PrintArray("Manual Additions Array", manualAdditions);

            StartThreads(threadCount, secondsArray, additionsArray);
        }
        private static void StartThreads(int threadCount, int[] secondsArray, int[] additionsArray)
        {
            for (int threadId = 0; threadId < threadCount; threadId++)
            {
                BreakThread breakThread = new(secondsArray[threadId]);
                new Thread(new ThreadStart(breakThread.Run)).Start();
                new CalculateNum(threadId, breakThread, additionsArray).Start();
            }
        }

        static int[] GenerateArray(int count, int minValue, int maxValue)
        {
            Random random = new();
            int[] result = new int[count];

            for (int i = 0; i < count; i++)
            {
                result[i] = random.Next(minValue, maxValue + 1);
            }

            return result;
        }

        public static void PrintArray(string name, int[] array)
        {
            Console.WriteLine(name + ": " + string.Join(", ", array));
        }

    }
}