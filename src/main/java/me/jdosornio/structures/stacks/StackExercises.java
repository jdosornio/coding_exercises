package me.jdosornio.structures.stacks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class StackExercises {
    // https://www.hackerrank.com/challenges/maximum-element/problem
    public static List<Integer> getMax(List<String> operations) {
        List<Integer> outputs = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        PriorityQueue<Integer> maxValues = new PriorityQueue<>((a, b) -> b - a);

        for (String opStr : operations) {
            String[] operation = opStr.split("\\s");

            switch (operation[0]) {
                case "1":
                    int newVal = Integer.parseInt(operation[1]);
                    stack.push(newVal);
                    maxValues.add(newVal);
                    break;
                case "2":
                    int removedVal = stack.pop();
                    maxValues.remove(removedVal);
                    break;
                case "3":
                    outputs.add(maxValues.peek());
                    break;
            }
        }

        return outputs;
    }

    // https://www.hackerrank.com/challenges/equal-stacks/problem
    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        // Compute sum
        for (int i = h1.size() - 2; i >= 0; i--) {
            h1.set(i, h1.get(i + 1) + h1.get(i));
        }
        for (int i = h2.size() - 2; i >= 0; i--) {
            h2.set(i, h2.get(i + 1) + h2.get(i));
        }
        for (int i = h3.size() - 2; i >= 0; i--) {
            h3.set(i, h3.get(i + 1) + h3.get(i));
        }

        if (h1.size() <= h2.size() && h1.size() <= h3.size()) {
            for (int sum : h1) {
                if (Collections.binarySearch(h2, sum, (a, b) -> b - a) >= 0 &&
                        Collections.binarySearch(h3, sum, (a, b) -> b - a) >= 0) {
                    return sum;
                }
            }
        } else if (h2.size() <= h1.size() && h2.size() <= h3.size()) {
            for (int sum : h2) {
                if (Collections.binarySearch(h1, sum, (a, b) -> b - a) >= 0 &&
                        Collections.binarySearch(h3, sum, (a, b) -> b - a) >= 0) {
                    return sum;
                }
            }
        } else if (h3.size() <= h2.size() && h3.size() <= h1.size()) {
            for (int sum : h3) {
                if (Collections.binarySearch(h1, sum, (a, b) -> b - a) >= 0 &&
                        Collections.binarySearch(h2, sum, (a, b) -> b - a) >= 0) {
                    return sum;
                }
            }
        }

        return 0;
    }

    // https://www.hackerrank.com/challenges/balanced-brackets/problem
    public static String isBalanced(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);

            if (currChar == '{' || currChar == '[' || currChar == '(') {
                stack.addFirst(currChar);
            } else {
                Character removedChar = stack.pollFirst();

                if (removedChar == null) {
                    return "NO";
                }
                if ((currChar == '}' && removedChar != '{')
                        || (currChar == ')' && removedChar != '(')
                        || (currChar == ']' && removedChar != '[')) {
                    return "NO";
                }
            }
        }

        if (stack.size() > 0) {
            return "NO";
        }
        return "YES";
    }

    // https://www.hackerrank.com/challenges/game-of-two-stacks/problem
    public static int twoStacks(int maxSum, List<Integer> a, List<Integer> b) {
        int n = a.size();
        int m = b.size();
        int sum = 0;
        int count = 0;
        int i = 0, j = 0;

        while (i < n && sum + a.get(i) <= maxSum) {
            sum += a.get(i);
            i++;
        }
        count = i;

        while (j < m && i >= 0) {
            sum += b.get(j);
            j++;

            while (sum > maxSum && i > 0) {
                i--;
                sum -= a.get(i);
            }

            if (sum <= maxSum && i + j > count) {
                count = i + j;
            }
            if (sum > maxSum && i == 0) {
                break;
            }
        }

        return count;
    }
}