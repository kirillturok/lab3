package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите количество очередей.");
        Scanner scan = new Scanner(System.in);
        int numberOfQueues = scan.nextInt();
        System.out.println("Введите пропускную способность.");
        int capacity = scan.nextInt();
        double priority[] = new double[numberOfQueues - 1];

        System.out.println("Введите количество элементов приоритетной очереди.");
        //создаем приоритетную очередь
        Queue priorityQueue = new Queue();
        priorityQueue.add(scan.nextInt());
        //создаем взвешанные очереди
        Queue wheighedQueues[] = new Queue[numberOfQueues - 1];
        //prioritySum - сумма долей взвешенных очередей
        double prioritySum = 0;
        //если кол-во очередей=1, то взешенные очереди создаваться не будут
        if (numberOfQueues > 1) {

            Scanner s = new Scanner(System.in);
            //ввод данных взвешенных оередей
            for (int i = 0; i < numberOfQueues - 1; i++) {
                System.out.println("Введите приоритет " + (i + 1) + " взвешенной очереди.");
                priority[i] = s.nextDouble();
                prioritySum += priority[i];
                System.out.println("Ведите количество ее элементов.");
                wheighedQueues[i] = new Queue();
                wheighedQueues[i].add(scan.nextInt());
            }
        }
        //сначала выведем элементы приоритетной очереди
        //в этом цикле выводятся элементы только в том случае, если элементы приоритетной очереди
        //полностью покрывают пропускную спосоность
        for (int i = 0; i < (priorityQueue.getSize() / capacity); ) {
            System.out.print("Элементы приоритетной очереди:");
            priorityQueue.show(capacity);
            System.out.println();
        }
        //если не вывелись все элементы приоритетной очереди, т. е. осталось такое кол-во,
        //которое не покрываает пропускную способность,
        //то выводятся сначала элементы приоритетной очерели, потом из взвешенных
        if (priorityQueue.getSize() != 0) {
            int cap = capacity - priorityQueue.getSize();
            System.out.print("Элементы приоритетной очереди: ");
            priorityQueue.show(priorityQueue.getSize());
            System.out.print("Элементы взвешенных очередей: ");
            showWeighedQueues(wheighedQueues, priority, prioritySum, cap);
            System.out.println();
        }
        //цикл вывода элементов взвешенных очередей
        while (true) {
            prioritySum = 0;
            for (int i = 0; i < priority.length; i++) {
                if (wheighedQueues[i].getSize() != 0) prioritySum += priority[i];
            }
            if (prioritySum != 0) {
                showWeighedQueues(wheighedQueues, priority, prioritySum, capacity);
                System.out.println();
            } else break;
        }

    }

    private static void showWeighedQueues(Queue[] queues, double[] pr, double sum, int capacity) {
        boolean en = true;
        int cap = capacity;
        double s = sum;
        for (int i = 0; i < queues.length; i++) {
            int num = (int) Math.round((double) capacity / sum * pr[i]);
            //System.out.println("-----"+sum+"-----");
            if (num <= queues[i].getSize()) {
                queues[i].show(num);
                cap -= num;
            } else {
                cap -= queues[i].getSize();
                queues[i].show(queues[i].getSize());
                en = false;
                s -= pr[i];
                pr[i] = 0;

            }
        }
        if (!en) {
            //System.out.println("---"+cap+" "+s+"---");
            showWeighedQueues(queues, pr, s, cap);
        }
        // System.out.println(Arrays.toString(pr));


    }
}
