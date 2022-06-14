package me.jdosornio.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortingExercises {

    protected static int bubbleSort(List<Integer> a) {
        int swaps = 0;

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size() - 1; j++) {
                if (a.get(j) > a.get(j + 1)) {
                    int temp = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set(j + 1, temp);

                    swaps++;
                }
            }
        }

        return swaps;
    }

    // https://www.hackerrank.com/challenges/ctci-bubble-sort/problem
    public static void countSwaps(List<Integer> a) {
        int swaps = bubbleSort(a);

        System.out.printf("Array is sorted in %d swaps.\n", swaps);
        System.out.printf("First Element: %d\n", a.get(0));
        System.out.printf("Last Element: %d\n", a.get(a.size() - 1));
    }

    // https://www.hackerrank.com/challenges/mark-and-toys/problem
    public static int maximumToys(List<Integer> prices, int k) {
        int count = 0;
        Integer[] arr = prices.toArray(new Integer[] {});
        Arrays.sort(arr);

        for (int price : arr) {
            k -= price;
            if (k >= 0) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    // TODO: Make it less complex
    // https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem
    public static int activityNotifications(List<Integer> expenditure, int d) {
        int notifications = 0;
        int[] counts = new int[201];

        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }
        for (int i = 0; i < d; i++) {
            counts[expenditure.get(i)]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        for (int i = d; i < expenditure.size(); i++) {
            // List<Integer> trailing = expenditure.subList(i - d, i);
            double median = 0;
            Map<Integer, Integer> tempCounter = new HashMap<>();
            // trailing.sort(Comparator.naturalOrder());
            if (i > d) {
                // shift sublist
                // re count
                int newVal = expenditure.get(i - 1);
                int oldVal = expenditure.get(i - d - 1);

                if (newVal != oldVal) {
                    for (int j = counts.length - 1; j >= newVal; j--) {
                        counts[j]++;
                    }
                    for (int j = counts.length - 1; j >= oldVal; j--) {
                        counts[j]--;
                    }
                }
            }
            // find median
            if (d % 2 == 0) {
                int first = d / 2;
                int second = d / 2 - 1;

                for (int j = i - d; j < i; j++) {
                    int num = expenditure.get(j);
                    Integer idx = tempCounter.get(num);

                    if (idx == null) {
                        idx = counts[num];
                    }
                    // if (idx <= 0) {
                    // break;
                    // }

                    if (idx - 1 == first) {
                        median += num;
                        first = -1;
                    } else if (idx - 1 == second) {
                        median += num;
                        second = -1;
                    }
                    if (first == -1 && second == -1) {
                        median /= 2.0;
                        break;
                    }
                    tempCounter.put(num, idx - 1);
                }
            } else {
                int m = d / 2;

                for (int j = i - d; j < i; j++) {
                    int num = expenditure.get(j);
                    Integer idx = tempCounter.get(num);

                    if (idx == null) {
                        idx = counts[num];
                    }
                    // if (idx <= 0) {
                    // break;
                    // }

                    if (idx - 1 == m) {
                        median = num;
                        break;
                    }
                    tempCounter.put(num, idx - 1);
                }
            }

            if (expenditure.get(i) >= 2 * median) {
                notifications++;
            }
        }

        return notifications;
    }
}