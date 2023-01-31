package part1Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import part1.Executor;


import static org.junit.jupiter.api.Assertions.*;

class ExecutorTest {

    Executor main;

    @BeforeEach
    void setUp() {
        main = new Executor();
    }

    @Test
    void modifyStringTest() {

        int wordLength = 5;
        String initialStr = "Lorem ipsum dolor sit amet";
        String expectedStr = "ipsum sit amet";

        initialStr = main.modifyString(wordLength, initialStr);

        assertEquals(expectedStr, initialStr);
    }

    @Test
    void isConsonantTest() {

        assertTrue(main.isConsonant('b'));
    }

    @Test
    void isConsonantTest2 (){

        assertFalse(main.isConsonant('a'));
    }
}