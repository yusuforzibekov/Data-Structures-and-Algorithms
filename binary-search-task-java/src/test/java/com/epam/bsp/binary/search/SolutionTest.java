package com.epam.bsp.binary.search;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

    //Please don't assume that this class will be used in the hidden tests.
    private static class BlackBox implements Function<Integer, Integer> {

        private int secretNumber;

        public BlackBox(int secretNumber) {
            this.secretNumber = secretNumber;
        }

        @Override
        public Integer apply(Integer value) {
            if (secretNumber == value) {
                return 0;
            }
            if (secretNumber > value) {
                return -1;
            }
            return 1;
        }
    }

    @Test
    public void testGuessNumberSample() {
        assertEquals(5,
                Solution.guessNumber(1, 10, new BlackBox(5))
        );
        assertEquals(9,
                Solution.guessNumber(1, 10, new BlackBox(9))
        );
    }

    @Test
    public void testGetRequiredTimeSample() {
        assertEquals(10,
                Solution.getRequiredTime(List.of(2, 4, 3), 10)
        );
        assertEquals(5,
                Solution.getRequiredTime(List.of(1, 1, 3, 5), 11)
        );
    }

    @Test
    public void testProcessQueriesSample() {
        assertEquals(List.of(3, 0, 3),
                Solution.processQueries(
                        List.of(0, 1, 2, 4, 6, 1000),
                        List.of(
                                new Solution.Pair(-100, 3),
                                new Solution.Pair(5, 5),
                                new Solution.Pair(1, 4)
                        )
                )
        );
        assertEquals(List.of(4, 3, 5),
                Solution.processQueries(
                        List.of(0, 0, 1, 1, 2),
                        List.of(
                                new Solution.Pair(0, 1),
                                new Solution.Pair(1, 2),
                                new Solution.Pair(-5, 10)
                        )
                )
        );
    }

}