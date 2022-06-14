package me.jdosornio.structures.arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ArrayExercisesTest {

    @Test
    public void equilibriumPoint() {
        int[] input = { 1, 4, 2, 5 };
        int output = ArrayExercises.equilibriumPoint(input);

        assertEquals(2, output);

        input = new int[] { 2, 3, 4, 1, 4, 5 };
        output = ArrayExercises.equilibriumPoint(input);

        assertEquals(1, output);
    }

    @Test
    public void sortArray012() {
        int[] input = { 0, 1, 2, 0, 1, 2 };
        Integer[] output = ArrayExercises.sortArray012(input);

        assertArrayEquals(new Integer[] { 0, 0, 1, 1, 2, 2 }, output);

        input = new int[] { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
        output = ArrayExercises.sortArray012(input);

        assertArrayEquals(new Integer[] { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2 }, output);
    }

    @Test
    public void stockBuySell() {
        Integer[] input = { 100, 180, 260, 310, 40, 535, 695 };
        int output = ArrayExercises.stockBuySell(input);

        assertEquals(865, output);
    }

    @Test
    public void largestContinuosSum() {
        int[] input = { -2, -3, 4, -1, -2, 1, 5, -3 };
        int output = ArrayExercises.largestContinuosSum(input);

        assertEquals(7, output);
    }

    @Test
    public void findMissing() {
        int[] input = { 1, 2, 4, 6, 3, 7, 8 };
        int output = ArrayExercises.findMissing(input);

        assertEquals(5, output);

        input = new int[] { 1, 2, 3, 5 };
        output = ArrayExercises.findMissing(input);

        assertEquals(4, output);
    }

    @Test
    public void sockMerchant() {
        List<Integer> input = Arrays.asList(10, 20, 20, 10, 10, 30, 50, 10, 20);
        int output = ArrayExercises.sockMerchant(input.size(), input);

        assertEquals(3, output);

        input = Arrays.asList(6, 5, 2, 3, 5, 2, 2, 1, 1, 5, 1, 3, 3, 3, 5);
        output = ArrayExercises.sockMerchant(input.size(), input);

        assertEquals(6, output);
    }

    @Test
    public void jumpingOnClouds() {
        List<Integer> input = Arrays.asList(0, 0, 1, 0, 0, 1, 0);
        int output = ArrayExercises.jumpingOnClouds(input);

        assertEquals(4, output);

        input = Arrays.asList(0, 0, 0, 1, 0, 0);
        output = ArrayExercises.jumpingOnClouds(input);

        assertEquals(3, output);
    }

    @Test
    public void hourglassSum() {
        List<List<Integer>> input = Arrays.asList(Arrays.asList(1, 1, 1, 0, 0, 0), Arrays.asList(0, 1, 0, 0, 0, 0),
                Arrays.asList(1, 1, 1, 0, 0, 0), Arrays.asList(0, 0, 2, 4, 4, 0), Arrays.asList(0, 0, 0, 2, 0, 0),
                Arrays.asList(0, 0, 1, 2, 4, 0));
        int output = ArrayExercises.hourglassSum(input);

        assertEquals(19, output);

        input = Arrays.asList(Arrays.asList(1, 1, 1, 0, 0, 0), Arrays.asList(0, 1, 0, 0, 0, 0),
                Arrays.asList(1, 1, 1, 0, 0, 0), Arrays.asList(0, 9, 2, -4, -4, 0), Arrays.asList(0, 0, 0, -2, 0, 0),
                Arrays.asList(0, 0, -1, -2, -4, 0));
        output = ArrayExercises.hourglassSum(input);

        assertEquals(13, output);

        input = Arrays.asList(Arrays.asList(-9, -9, -9, 1, 1, 1), Arrays.asList(0, -9, 0, 4, 3, 2),
                Arrays.asList(-9, -9, -9, 1, 2, 3), Arrays.asList(0, 0, 8, 6, 6, 0), Arrays.asList(0, 0, 0, -2, 0, 0),
                Arrays.asList(0, 0, 1, 2, 4, 0));
        output = ArrayExercises.hourglassSum(input);

        assertEquals(28, output);
    }

    @Test
    public void rotLeft() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> output = ArrayExercises.rotLeft(input, 4);

        assertArrayEquals(new Integer[] { 5, 1, 2, 3, 4 }, output.toArray());

        input = new ArrayList<>(
                Arrays.asList(41, 73, 89, 7, 10, 1, 59, 58, 84, 77, 77, 97, 58, 1, 86, 58, 26, 10, 86, 51));
        output = ArrayExercises.rotLeft(input, 10);

        assertArrayEquals(new Integer[] { 77, 97, 58, 1, 86, 58, 26, 10, 86, 51, 41, 73, 89, 7, 10, 1, 59, 58, 84, 77 },
                output.toArray());

        input = new ArrayList<>(
                Arrays.asList(33, 47, 70, 37, 8, 53, 13, 93, 71, 72, 51, 100, 60, 87, 97));
        output = ArrayExercises.rotLeft(input, 13);

        assertArrayEquals(new Integer[] { 87, 97, 33, 47, 70, 37, 8, 53, 13, 93, 71, 72, 51, 100, 60 },
                output.toArray());
    }

    @Test
    public void minimumBribes() {
        List<Integer> input = Arrays.asList(2, 1, 5, 3, 4);
        String output = ArrayExercises.bribes(input);

        assertEquals("3", output);

        input = Arrays.asList(2, 5, 1, 3, 4);
        output = ArrayExercises.bribes(input);

        assertEquals("Too chaotic", output);

        input = Arrays.asList(5, 1, 2, 3, 7, 8, 6, 4);
        output = ArrayExercises.bribes(input);

        assertEquals("Too chaotic", output);

        input = Arrays.asList(1, 2, 5, 3, 7, 8, 6, 4);
        output = ArrayExercises.bribes(input);

        assertEquals("7", output);

        input = Arrays.asList(1, 2, 5, 3, 4, 7, 8, 6);
        output = ArrayExercises.bribes(input);

        assertEquals("4", output);
    }

    @Test
    public void minimumSwaps() {
        int[] input = { 4, 3, 1, 2 };
        int output = ArrayExercises.minimumSwaps(input);

        assertEquals(3, output);

        input = new int[] { 2, 3, 4, 1, 5 };
        output = ArrayExercises.minimumSwaps(input);

        assertEquals(3, output);

        input = new int[] { 1, 3, 5, 2, 4, 6, 7 };
        output = ArrayExercises.minimumSwaps(input);

        assertEquals(3, output);
    }

    @Test
    public void arrayManipulation() {
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(1, 2, 100),
                Arrays.asList(2, 5, 100),
                Arrays.asList(3, 4, 100));
        long output = ArrayExercises.arrayManipulation(5, input);

        assertEquals(200, output);

        input = Arrays.asList(
                Arrays.asList(1, 5, 3),
                Arrays.asList(4, 8, 7),
                Arrays.asList(6, 9, 1));
        output = ArrayExercises.arrayManipulation(10, input);

        assertEquals(10, output);

        input = Arrays.asList(
                Arrays.asList(2, 6, 8),
                Arrays.asList(3, 5, 7),
                Arrays.asList(1, 8, 1),
                Arrays.asList(5, 9, 15));
        output = ArrayExercises.arrayManipulation(10, input);

        assertEquals(31, output);
    }

    @Test
    public void shipmentImbalance() {
        List<Integer> input = Arrays.asList(1, 3, 2);
        int output = ArrayExercises.shipmentImbalance(input);

        assertEquals(5, output);

        input = Arrays.asList(1, 2, 3);
        output = ArrayExercises.shipmentImbalance(input);

        assertEquals(4, output);
    }

}