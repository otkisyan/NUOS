package org.example;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * Реализует очередь
 * <p>
 * Структура данных очереди функционирует так же, как и реальная очередь. Элементы,
 * которые добавляются первыми, удаляются первыми. Новые элементы добавляются в
 * задней части очереди.
 * </p>
 * Пример очереди – очередь людей. Последний занял последним и будешь, а первый первым ее и покинет.
 *
 * @see <a href="https://habr.com/ru/post/422259/">Habr: Основные структуры данных</a>
 */
public class ArrayQueue<T> {

    private T[] array;
    private int front;
    private int rear;
    private int size;

    public ArrayQueue(Class<T> instance, int capacity) {

        array = (T[]) Array.newInstance(instance, capacity);
        front = 0;
        rear = -1;
        size = 0;

    }

    /**
     * Добавляет указанный элемент в конец очереди.
     *
     * @param element элемент, добавляемый в очередь.
     * @throws IllegalStateException если очередь переполнена
     */
    public void enqueue(T element) {

        if (isFull()) {

            throw new IllegalStateException("Queue is full");
        }

        // Переменная rear инициализируется в -1,чтобы указать, что очередь изначально пуста
        // В методе enqueue(), когда в очередь добавляется первый элемент,
        // rear увеличивается до 0, и элемент сохраняется под индексом 0 в массиве.
        // При последующих добавлениях в очередь значение rear увеличивается,
        // и элемент сохраняется по следующему доступному индексу в буфере.
        rear = (rear + 1) % array.length;
        array[rear] = element;
        size++;

    }

    /**
     * Удаляет и возвращает элемент, находящийся в начале очереди.
     *
     * @return элемент в начале очереди.
     * @throws NoSuchElementException, если очередь пуста.
     */
    public T dequeue() {

        if (isEmpty()) {

            throw new NoSuchElementException("Queue is empty");
        }

        T element = array[front];

        // Допустим, у нас есть массив размером 5, и front в настоящее время указывает на индекс 2.
        // Если мы хотим снять элемент с очереди, нам нужно сместить front на одну позицию вправо,
        // чтобы он указывал на следующий элемент в очереди.
        front = (front + 1) % array.length;
        size--;
        return element;

    }

    /**
     * Возвращает элемент, находящийся в начале очереди
     *
     * @return элемент в начале очереди.
     * @throws NoSuchElementException если очередь пуста.
     */
    public T peekFront() {

        if (isEmpty()) {

            throw new NoSuchElementException("Queue is empty");
        }

        return array[front];
    }

    /**
     * Возвращает элемент, находящийся в конце очереди
     *
     * @return элемент в конце очереди
     * @throws NoSuchElementException если очередь пуста.
     */
    public T peekRear() {

        if (isEmpty()) {

            throw new NoSuchElementException("Queue is empty");
        }

        return array[rear];
    }

    /**
     * Возвращает true, если очередь заполнена, false в противном случае.
     *
     * @return true, если очередь заполнена, false в противном случае.
     */
    public boolean isFull() {

        return size == array.length;
    }

    /**
     * Возвращает true, если очередь пуста, false в противном случае.
     *
     * @return true, если очередь пуста, false в противном случае.
     */
    public boolean isEmpty() {

        return size == 0;
    }

    /**
     * Возвращает количество элементов в очереди.
     *
     * @return число элементов в очереди.
     */
    public int size() {

        return size;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = front; i < size; i++) {

            sb.append(array[i]).append(", ");

            if (i == rear) {

                break;
            }
        }

        sb.replace(sb.length() - 2, sb.length(), "]");
        return sb.toString();
    }
}


