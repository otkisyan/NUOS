package org.example;

import java.util.NoSuchElementException;

/**
 * Постановка запросов выполняется по приоритету.
 * Приоритет - по минимальному значению, при совпадении значений - FIFO (First in First Out).
 * Извлечение запросов - в обычном порядке из начала очереди.
 */
public class LinkedPriorityQueue<T extends Comparable<T>> {

    private class Node {

        T data;
        Node next;

        Node(T data) {

            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public LinkedPriorityQueue() {

        head = null;
        size = 0;
    }

    /**
     * Добавляет новый узел с указанными данными в очередь приоритетов.
     * Узел вставляется в правильную позицию на основе его приоритета
     *
     * @param data данные для добавления в очередь
     * @throws NullPointerException если указанные данные равны null
     * @throws ClassCastException   если тип указанных данных не позволяет сравнивать их
     *                              с элементами, находящимися в этой очереди приоритетов.
     */
    public void enqueue(T data) {

        if (data == null) {

            throw new NullPointerException();
        }

        try {

            Node newNode = new Node(data);

            // Проверяем, пуста ли очередь приоритетов или новые данные имеют более высокий приоритет, чем текущий head
            // compareTo возвращает отрицательное целое число, ноль или положительное целое число,
            // если данный объект меньше, равен или больше указанного объекта соответственно.
            if (head == null || data.compareTo(head.data) < 0) {

                // Если одно из условий истинно, то устанавливаем новый узел в качестве нового head очереди,
                // при этом предыдущий узел становится следующим узлом нового узла:
                newNode.next = head;
                head = newNode;

            } else {

                Node current = head;

                // Этот цикл будет продолжать итерацию, пока мы не достигнем конца очереди приоритетов
                // или пока не найдем узел с более низким приоритетом, чем новые данные.
                while (current.next != null && data.compareTo(current.next.data) >= 0) {

                    current = current.next;
                }

                // Как только мы найдем нужную позицию, вставляем новый узел между текущим и следующим узлом:
                newNode.next = current.next;
                current.next = newNode;

            }

        } catch (ClassCastException e) {

            throw new ClassCastException();

        }

        size++;
    }

    /**
     * Удаляет и возвращает данные узла, находящегося в начале очереди.
     *
     * @return данные узла в начале очереди.
     * @throws NoSuchElementException если очередь пуста.
     */
    public T dequeue() {

        if (isEmpty()) {

            throw new NoSuchElementException();
        }

        T data = head.data;
        head = head.next;
        size--;

        return data;
    }

    /**
     * Возвращает данные узла находящегося в начале очереди
     *
     * @return данные узла находящиеся в начале очереди
     * @throws NoSuchElementException если очередь пуста.
     */
    public T peekFront() {

        if (isEmpty()) {

            throw new NoSuchElementException();

        } else {

            return head.data;
        }
    }

    public int size() {

        return size;
    }

    public boolean isEmpty() {

        return head == null;
    }

    @Override
    public String toString() {

        if (isEmpty()) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder();

        Node cur = head;
        builder.append("[");

        while (cur != null) {

            builder.append(cur.data).append(", ");
            cur = cur.next;
        }

        builder.replace(builder.length() - 2, builder.length(), "]");
        return builder.toString();
    }

}
