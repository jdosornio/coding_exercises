package me.jdosornio.structures.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

enum Type {
    OPEN, CLOSE
}

class Boundary {
    private Type type;
    private int pos;
    private int val;

    public Boundary(int pos, Type type, int val) {
        this.type = type;
        this.pos = pos;
        this.val = val;
    }

    public int getPos() {
        return pos;
    }

    public Type getType() {
        return type;
    }

    public int getVal() {
        return val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Boundary) {
            Boundary other = (Boundary) obj;

            return pos == other.getPos() && type == other.getType();
        }
        return false;
    }
}

public class ArrayExercises {

    public static int equilibriumPoint(int[] input) {
        int count = input.length;
        int leftSum = 0;
        int rightSum = 0;

        if (count < 4 || count % 2 == 1) {
            return 0;
        }

        int i, j;
        for (i = 0, j = count - 1; i + 1 != j; i++, j--) {
            leftSum += input[i];
            rightSum += input[j];
        }
        // Should always be pair
        // Assume equilibrium is always attainable
        return (leftSum + input[i] == rightSum) ? input[j] : input[i];
    }

    public static Integer[] sortArray012(int[] input) {
        int zeroCount = 0;
        int oneCount = 0;
        int twoCount = 0;

        for (int elem : input) {
            if (elem == 0) {
                zeroCount++;
            } else if (elem == 1) {
                oneCount++;
            } else {
                // Should be 2
                twoCount++;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < zeroCount; i++) {
            result.add(0);
        }
        for (int i = 0; i < oneCount; i++) {
            result.add(1);
        }
        for (int i = 0; i < twoCount; i++) {
            result.add(2);
        }

        return result.toArray(new Integer[] {});
    }

    public static int stockBuySell(Integer[] input) {
        int buyPrice = input[0];
        int profit = -buyPrice;

        for (int i = 1; i < input.length; i++) {
            if (input[i] < buyPrice) {
                buyPrice = input[i];
                profit += input[i - 1] - buyPrice;
            }
        }

        return profit + input[input.length - 1];
    }

    public static int largestContinuosSum(int[] input) {
        int start = 0;
        int max = Integer.MIN_VALUE;
        int totalSum = Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < input.length; i++) {
            int current = input[i];
            if (max < current) {
                max = current;
                if (totalSum + current < current) {
                    start = i;
                    totalSum = current;
                    maxSum = totalSum;
                }
            }
            if (start != i) {
                totalSum += current;
                if (maxSum < totalSum) {
                    maxSum = totalSum;
                }
            }
        }

        return maxSum;
    }

    public static int findMissing(int[] input) {
        int n = input.length + 1;
        int naturalSum = n * (n + 1) / 2;
        int realSum = Arrays.stream(input).sum();

        return naturalSum - realSum;
    }

    // https://www.hackerrank.com/challenges/sock-merchant/problem
    public static int sockMerchant(int n, List<Integer> ar) {
        Map<Integer, Integer> counter = new HashMap<>();

        for (int num : ar) {
            int count = counter.getOrDefault(num, 0);
            counter.put(num, count + 1);
        }

        counter.values().forEach(System.out::println);

        return counter.values().stream().mapToInt(c -> c / 2).sum();
    }

    // https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem
    public static int jumpingOnClouds(List<Integer> c) {
        int jumps = 0;
        int idx = 0;

        while (idx < c.size() - 1) {
            if (idx + 2 < c.size() && c.get(idx + 2) != 1) {
                idx = idx + 2;
            } else {
                idx++;
            }
            jumps++;
        }

        return jumps;
    }

    private static int getSum(int startRow, int startCol, List<List<Integer>> arr) {
        int firstRow = arr.get(startRow).subList(startCol, startCol + 3).stream().reduce(0, Integer::sum);
        int secondRow = arr.get(startRow + 1).get(startCol + 1);
        int thirdRow = arr.get(startRow + 2).subList(startCol, startCol + 3).stream().reduce(0, Integer::sum);

        return firstRow + secondRow + thirdRow;
    }

    // https://www.hackerrank.com/challenges/2d-array/problem
    public static int hourglassSum(List<List<Integer>> arr) {
        int maxSum = Integer.MIN_VALUE;

        for (int row = 0; row <= 3; row++) {
            for (int col = 0; col <= 3; col++) {
                int hSum = getSum(row, col, arr);
                if (hSum > maxSum) {
                    maxSum = hSum;
                }
            }
        }

        return maxSum;
    }

    // https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem
    public static List<Integer> rotLeft(List<Integer> a, int d) {
        List<Integer> toMove = a.subList(0, d);
        List<Integer> remaining = a.subList(d, a.size());

        remaining.addAll(toMove);

        return remaining;
    }

    // https://www.hackerrank.com/challenges/new-year-chaos/problem
    protected static String bribes(List<Integer> q) {
        int bribes = 0;
        for (int i = q.size() - 1; i >= 0; i--) {
            if (q.get(i) - (i + 1) > 2) {
                return "Too chaotic";
            }
            for (int j = Integer.max(0, q.get(i) - 2); j < i; j++) {
                if (q.get(j) > q.get(i)) {
                    bribes++;
                }
            }
        }
        return Integer.toString(bribes);
    }

    public static void minimumBribes(List<Integer> q) {
        System.out.println(bribes(q));
    }

    // https://www.hackerrank.com/challenges/minimum-swaps-2/problem
    static int minimumSwaps(int[] arr) {
        int swaps = 0;
        int i = 0;

        while (i < arr.length) {
            if (arr[i] != i + 1) {
                int other = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = other;
                swaps++;
            }
            if (arr[i] == i + 1) {
                i++;
            }
        }

        return swaps;
    }

    // https://www.hackerrank.com/challenges/crush/problem
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        PriorityQueue<Boundary> ops = new PriorityQueue<>((a, b) -> {
            int diff = a.getPos() - b.getPos();

            if (diff == 0) {
                return a.getType().ordinal() - b.getType().ordinal();
            }

            return diff;
        });

        queries.forEach(q -> {
            Boundary start = new Boundary(q.get(0), Type.OPEN, q.get(2));
            Boundary end = new Boundary(q.get(1), Type.CLOSE, q.get(2));

            ops.add(start);
            ops.add(end);
        });

        long current = 0;
        long max = 0;
        while (!ops.isEmpty()) {
            Boundary b = ops.poll();
            if (b.getType() == Type.OPEN) {
                current += b.getVal();
            } else {
                current -= b.getVal();
            }
            if (max < current) {
                max = current;
            }
        }

        return max;
    }

    // https://leetcode.com/problems/sum-of-subarray-ranges/discuss/1856764/Two-Stack-o(n)
    public static int shipmentImbalance(List<Integer> weights) {
        Stack<Integer> minStack = new Stack<>(), maxStack = new Stack<>();
        int res = 0;

        minStack.push(-1);
        maxStack.push(-1);

        for (int i = 0; i < weights.size(); i++) {
            int w = weights.get(i);

            while (minStack.size() > 1 && w <= weights.get(minStack.peek())) {
                int top = minStack.pop();
                int val = weights.get(top) * (i - top) * (top - minStack.peek());

                res -= val;
            }
            minStack.push(i);
            while (maxStack.size() > 1 && w >= weights.get(maxStack.peek())) {
                int top = maxStack.pop();
                int val = weights.get(top) * (i - top) * (top - maxStack.peek());

                res += val;
            }
            maxStack.push(i);
        }
        int length = weights.size();

        while (minStack.size() > 1) {
            int top = minStack.pop();
            res -= weights.get(top) * (length - top) * (top - minStack.peek());
        }
        while (maxStack.size() > 1) {
            int top = maxStack.pop();
            res += weights.get(top) * (length - top) * (top - maxStack.peek());
        }

        return res;
    }

    // https://www.hackerrank.com/challenges/sparse-arrays/problem
    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        List<Integer> counts = new ArrayList<>();
        Map<String, Integer> stringCounter = new HashMap<>();

        for (String str : strings) {
            stringCounter.merge(str, 1, (total, current) -> total + current);
        }
        for (String query : queries) {
            counts.add(stringCounter.getOrDefault(query, 0));
        }

        return counts;
    }

    // https://leetcode.com/problems/median-of-two-sorted-arrays/submissions/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int globalIdx = 0;
        int totalLength = nums1.length + nums2.length;
        int half = totalLength / 2;
        Integer firstPart = null;

        while (i < nums1.length && j < nums2.length) {
            while (i < nums1.length && nums1[i] <= nums2[j]) {
                // Get median
                if (totalLength % 2 == 0) {
                    if (globalIdx == half - 1) {
                        firstPart = nums1[i];
                    } else if (globalIdx == half) {
                        return (firstPart + nums1[i]) / 2.0;
                    }
                } else if (globalIdx == half) {
                    return nums1[i];
                }
                i++;
                globalIdx++;
            }

            // nums1 > nums2
            while (j < nums2.length && i < nums1.length && nums1[i] > nums2[j]) {
                // Get median
                if (totalLength % 2 == 0) {
                    if (globalIdx == half - 1) {
                        firstPart = nums2[j];
                    } else if (globalIdx == half) {
                        return (firstPart + nums2[j]) / 2.0;
                    }
                } else if (globalIdx == half) {
                    return nums2[j];
                }
                j++;
                globalIdx++;
            }

        }

        int idxDiff = half - globalIdx;

        if (i >= nums1.length) {
            // only nums2 remain
            if (totalLength % 2 == 0) {
                if (firstPart != null) {
                    return (firstPart + nums2[j + idxDiff]) / 2.0;
                } else {
                    return (nums2[j + idxDiff - 1] + nums2[j + idxDiff]) / 2.0;
                }
            } else {
                return nums2[j + idxDiff];
            }

        } else if (j >= nums2.length) {
            if (totalLength % 2 == 0) {
                if (firstPart != null) {
                    return (firstPart + nums1[i + idxDiff]) / 2.0;
                } else {
                    return (nums1[i + idxDiff - 1] + nums1[i + idxDiff]) / 2.0;
                }
            } else {
                return nums1[i + idxDiff];
            }
        }

        return 0;
    }

    // Not working
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        int half = total / 2;
        int[] A = nums1, B = nums2;

        if (B.length < A.length) {
            int[] temp = A;
            A = B;
            B = temp;
        }

        int left = 0, right = A.length - 1;
        while (true) {
            int i = (left + right) / 2;
            int j = half - i - 2;

            int ALeft = (i >= 0) ? A[i] : Integer.MIN_VALUE;
            int ARight = (i + 1 < A.length) ? A[i + 1] : Integer.MAX_VALUE;
            int BLeft = (j >= 0) ? B[j] : Integer.MIN_VALUE;
            int BRight = (j + 1 < B.length) ? B[j + 1] : Integer.MAX_VALUE;

            if (ALeft <= BRight && BLeft <= ARight) {
                // Left partition found
                if (total % 2 == 1) {
                    return Math.min(ARight, BRight);
                }
                return (Math.max(ALeft, BLeft) + Math.min(BRight, ARight)) / 2.0;
            } else if (ALeft > BRight) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
    }

    // https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        List<Integer> results = new ArrayList<>(player.size());
        // or use array struct
        Map<Integer, Integer> rankingCache = new HashMap<>();
        int maxRanked = ranked.get(0);
        int minRanked = ranked.get(ranked.size() - 1);

        // sort in natural order
        int i = 0;
        int j = ranked.size() - 1;
        while (i < j) {
            // swap
            int temp = ranked.set(i, ranked.get(j));
            ranked.set(j, temp);
            i++;
            j--;
        }

        int ranking = 1;
        for (i = ranked.size() - 1; i >= 0; i--) {
            if (rankingCache.putIfAbsent(ranked.get(i), ranking) == null) {
                ranking++;
            }
        }

        // Check ranking results
        for (i = 0; i < player.size(); i++) {
            int play = player.get(i);
            Integer rank = rankingCache.get(play);

            if (rank != null) {
                results.add(rank);
            } else {
                if (play < minRanked) {
                    rankingCache.put(play, rankingCache.get(minRanked) + 1);
                } else if (play > maxRanked) {
                    rankingCache.put(play, 1);
                } else {
                    // in between
                    // else search in ranking, get the next bigger and +1 ranking
                    int pos = Collections.binarySearch(ranked, play);
                    int prevRanking = rankingCache.get(ranked.get((pos + 1) * -1));
                    rankingCache.put(play, prevRanking + 1);
                }
                results.add(rankingCache.get(play));
            }
        }

        return results;
    }

}
