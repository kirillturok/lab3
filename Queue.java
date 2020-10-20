package com.company;

public class Queue {
    //указатели на первый и последний элементы очереди
    Node first = null;
    Node last = null;
    int size = 0;

    //описываем элемент очереди
    public class Node {
        Node next;
        public int number;

        //конструктор элемента
        public Node(int name, Node next) {
            this.number = name;
            this.next = next;
        }
    }

    public int getSize() {
        return size;
    }

    //метод добавления элементов в очередь
    public void add(int numberOfElements) {
        if (numberOfElements == 0) {
            return;
        }
        first = last = new Node(1, null);
        for (int i = 2; i <= numberOfElements; i++) {
            last.next = new Node(i, null);
            last = last.next;
        }
        size = numberOfElements;
    }

    //метод отображения и удаления элементов из очереди
    public void show(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print(first.number + " ");
            first = first.next;
            size--;
        }
    }


}
