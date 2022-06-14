package me.jdosornio.algorithms;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SortingExercisesTest {
    @Test
    public void countSwaps() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        int output = SortingExercises.bubbleSort(input);

        assertEquals(0, output);

        input = Arrays.asList(3, 2, 1);
        output = SortingExercises.bubbleSort(input);

        assertEquals(3, output);

        input = Arrays.asList(4, 2, 3, 1);
        output = SortingExercises.bubbleSort(input);

        assertEquals(5, output);
    }

    @Test
    public void maximumToys() {
        List<Integer> input = Arrays.asList(1, 12, 5, 111, 200, 1000, 10);
        int output = SortingExercises.maximumToys(input, 50);

        assertEquals(4, output);

        input = Arrays.asList(1, 2, 3, 4);
        output = SortingExercises.maximumToys(input, 7);

        assertEquals(3, output);

        input = Arrays.asList(3, 7, 2, 9, 4);
        output = SortingExercises.maximumToys(input, 15);

        assertEquals(3, output);
    }
}