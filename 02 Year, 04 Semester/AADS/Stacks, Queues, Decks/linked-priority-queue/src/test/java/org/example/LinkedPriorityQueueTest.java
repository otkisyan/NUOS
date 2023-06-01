package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedPriorityQueueTest {

    LinkedPriorityQueue<Integer> queue;

    @BeforeEach
    void setUp() {

        queue = new LinkedPriorityQueue<>();
    }

    @Test
    void enqueue() {

        queue.enqueue(0);
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(2);
        queue.enqueue(1);

        List<Integer> listActual = new ArrayList<>();
        List<Integer> listExpected = new ArrayList<>(Arrays.asList(0, 1, 1, 2, 2, 2, 4));

        while (!queue.isEmpty()) {
            listActual.add(queue.dequeue());
        }

        assertEquals(listExpected, listActual);


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