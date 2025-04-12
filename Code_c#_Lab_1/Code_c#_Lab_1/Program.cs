using System;
using System.Linq;
using System.Threading;

namespace Code_c__Lab_1
{
    class Program
    {
        static void Main(string[] args)
        {
            int numberOfThreads = 3;
            int minDuration = 3;
            int maxDuration = 10;
            int minStep = 1;
            int maxStep = 5;

            int[] threadDurations = GenerateRandomArray(numberOfThreads, minDuration, maxDuration);
            int[] incrementSteps = GenerateRandomArray(numberOfThreads, minStep, maxStep);

            Console.Write("Тривалість потоків: ");
            PrintArray(threadDurations);
            Console.Write("Кроки додавання: ");
            PrintArray(incrementSteps);

            CountdownEvent launchSignal = new CountdownEvent(1); // Сигнал запуску всіх потоків
            NumberCalculator[] calculators = new NumberCalculator[threadDurations.Length];

            for (int i = 0; i < calculators.Length; i++)
            {
                calculators[i] = new NumberCalculator(i, incrementSteps[i], launchSignal);
                Thread thread = new Thread(calculators[i].Run); // Запуск потоку в стані очікування
                thread.Start();
            }

            BreakThread controller = new BreakThread(threadDurations, calculators);
            Thread controllerThread = new Thread(controller.Run);

            Console.WriteLine(">>> Початок!");
            controllerThread.Start();
            launchSignal.Signal(); // Дати сигнал на запуск всім потокам
        }

        public static int[] GenerateRandomArray(int size, int min, int max)
        {
            Random rand = new Random();
            return Enumerable.Range(0, size).Select(_ => rand.Next(min, max + 1)).ToArray();
        }

        public static void PrintArray(int[] array)
        {
            foreach (var value in array)
            {
                Console.Write(value + " ");
            }
            Console.WriteLine();
        }
    }
}
