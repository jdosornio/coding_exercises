package me.jdosornio.algorithms;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

import org.junit.Test;

public class GreedyExercisesTest {

        @Test
        public void minimumAbsoluteDifference() {
                List<Integer> input = Arrays.asList(3, -7, 0);
                int output = GreedyExercises.minimumAbsoluteDifference(input);

                assertEquals(3, output);

                input = Arrays.asList(-59, -36, -13, 1, -53, -92, -2, -96, -54, 75);
                output = GreedyExercises.minimumAbsoluteDifference(input);

                assertEquals(1, output);

                input = Arrays.asList(1, -3, 71, 68, 17);
                output = GreedyExercises.minimumAbsoluteDifference(input);

                assertEquals(3, output);
        }

        @Test
        public void luckBalance() {
                String input = "5 1\n2 1\n1 1\n8 1\n10 0\n5 0";
                List<List<Integer>> contests = Arrays.stream(input.split("\n"))
                                .map(contest -> Arrays.stream(contest.split("\\s"))
                                                .map(Integer::parseInt).collect(toList()))
                                .collect(toList());
                int output = GreedyExercises.luckBalance(3, contests);

                assertEquals(29, output);

                input = "13 1\n10 1\n9 1\n8 1\n13 1\n12 1\n18 1\n13 1";
                contests = Arrays.stream(input.split("\n"))
                                .map(contest -> Arrays.stream(contest.split("\\s"))
                                                .map(Integer::parseInt).collect(toList()))
                                .collect(toList());
                output = GreedyExercises.luckBalance(5, contests);

                input = "5 1\n4 0\n6 1\n2 1\n8 0";
                contests = Arrays.stream(input.split("\n"))
                                .map(contest -> Arrays.stream(contest.split("\\s"))
                                                .map(Integer::parseInt).collect(toList()))
                                .collect(toList());
                output = GreedyExercises.luckBalance(2, contests);

                assertEquals(21, output);
        }
}