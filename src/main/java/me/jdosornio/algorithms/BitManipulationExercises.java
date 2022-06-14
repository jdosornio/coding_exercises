package me.jdosornio.algorithms;

import java.util.BitSet;

public class BitManipulationExercises {

    // https://www.hackerrank.com/challenges/counter-game/problem
    public static String counterGame(long n) {
        if (n == 1) {
            return "Richard";
        }

        BitSet binaryNum = BitSet.valueOf(new long[] { n });
        int bitsBeforeLast = binaryNum.cardinality() - 1;
        int zerosAfterLast = binaryNum.nextSetBit(0);

        return (bitsBeforeLast + zerosAfterLast) % 2 == 0 ? "Richard" : "Louise";
    }
}
