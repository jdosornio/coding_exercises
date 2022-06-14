package me.jdosornio.algorithms;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class GreedyExercises {
    // https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem
    public static int minimumAbsoluteDifference(List<Integer> arr) {
        List<Integer> cArr = new ArrayList<>(arr);

        cArr.sort((a, b) -> a - b);
        int min = Math.abs(cArr.get(0) - cArr.get(1));

        if (min == 0) {
            return 0;
        }

        for (int i = 1; i < cArr.size() - 1; i++) {
            int abs = Math.abs(cArr.get(i) - cArr.get(i + 1));
            if (abs == 0) {
                return 0;
            }
            if (min > abs) {
                min = abs;
            }
        }

        return min;
    }

    // https://www.hackerrank.com/challenges/luck-balance/problem
    public static int luckBalance(int k, List<List<Integer>> contests) {
        int luck = 0;
        List<List<Integer>> important = contests.stream().filter(c -> c.get(1) == 1)
                .sorted((a, b) -> a.get(0) - b.get(0)).collect(toList());
        int lastContest = important.size() - k;

        for (int i = 0; i < important.size() && i < lastContest; i++) {
            luck -= important.get(i).get(0);
        }

        luck += contests.stream().filter(c -> c.get(1) == 0).mapToInt(c -> c.get(0)).sum();
        luck += important.stream().skip(Integer.max(lastContest, 0))
                .mapToInt(c -> c.get(0)).sum();

        return luck;
    }
}