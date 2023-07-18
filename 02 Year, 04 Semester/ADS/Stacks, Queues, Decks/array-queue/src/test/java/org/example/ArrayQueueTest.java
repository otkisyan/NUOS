package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

    ArrayQueue<Integer> queue;

    @BeforeEach
    void setUp() {

        queue = new ArrayQueue<>(Integer.class, 10);
    }

    @Test
    void enqueue() {

        queue.enqueue(2);
        queue.enqueue(5);
        queue.enqueue(9);

        assertEquals(9, queue.peekRear());

    }


    @Test
    void dequeue() {

        queue.enqueue(2);
        queue.enqueue(5);
        queue.enqueue(9);
        queue.dequeue();

        assertEquals(5, queue.peekFront());

    }

}