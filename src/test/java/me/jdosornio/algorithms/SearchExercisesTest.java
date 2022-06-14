package me.jdosornio.algorithms;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SearchExercisesTest {

        @Test
        public void swapNodes() {
                String input = "2 3\n-1 -1\n-1 -1";
                List<List<Integer>> tree = Arrays.stream(input.split("\n"))
                                .map(contest -> Arrays.stream(contest.split("\\s"))
                                                .map(Integer::parseInt).collect(toList()))
                                .collect(toList());
                List<Integer> queries = Arrays.asList(1, 1);
                List<List<Integer>> output = SearchExercises.swapNodes(tree, queries);
                List<List<Integer>> expected = Arrays.stream("3 1 2\n2 1 3".split("\n"))
                                .map(contest -> Arrays.stream(contest.split("\\s"))
                                                .map(Integer::parseInt).collect(toList()))
                                .collect(toList());

                for (int i = 0; i < output.size(); i++) {
                        assertArrayEquals(expected.get(i).toArray(), output.get(i).toArray());
                }

                input = "2 3\n4 -1\n5 -1\n6 -1\n7 8\n-1 9\n-1 -1\n10 11\n-1 -1\n-1 -1\n-1 -1";
                tree = Arrays.stream(input.split("\n"))
                                .map(contest -> Arrays.stream(contest.split("\\s"))
                                                .map(Integer::parseInt).collect(toList()))
                                .collect(toList());
                queries = Arrays.asList(2, 4);
                output = SearchExercises.swapNodes(tree, queries);
                expected = Arrays.stream("2 9 6 4 1 3 7 5 11 8 10\n2 6 9 4 1 3 7 5 10 8 11".split("\n"))
                                .map(contest -> Arrays.stream(contest.split("\\s"))
                                                .map(Integer::parseInt).collect(toList()))
                                .collect(toList());

                for (int i = 0; i < output.size(); i++) {
                        assertArrayEquals(expected.get(i).toArray(), output.get(i).toArray());
                }
        }
}