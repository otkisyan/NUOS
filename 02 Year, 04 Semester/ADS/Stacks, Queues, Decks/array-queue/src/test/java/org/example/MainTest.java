package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Main main;

    @BeforeEach
    void setUp() {

        main = new Main();
    }

    @Test
    void getSumOfNumbers() {

        String sequence = "hljf2a8be4";

        ArrayQueue<Integer> numberQueue = new ArrayQueue<>(Integer.class, sequence.length());
        ArrayQueue<Character> alphaQueue = new ArrayQueue<>(Character.class, sequence.length());
        main.processQueues(sequence, numberQueue, alphaQueue);

        assertEquals(14, main.getSumOfNumbers(numberQueue));
    }

    @Test
    void getNumberOfConsonants() {

        String sequence = "hljf2a8bei4";

        ArrayQueue<Integer> numberQueue = new ArrayQueue<>(Integer.class, sequence.length());
        ArrayQueue<Character> alphaQueue = new ArrayQueue<>(Character.class, sequence.length());
        main.processQueues(sequence, numberQueue, alphaQueue);

        assertEquals(5, main.getNumberOfConsonants(alphaQueue));

    }
}