package me.jdosornio.structures.dictionaries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;

public class DictionaryExercises {

    protected static boolean checkMagazineInner(List<String> magazine, List<String> note) {
        Map<String, Integer> words = new HashMap<>();

        magazine.forEach(word -> {
            // count + inc (1)
            words.merge(word, 1, Integer::sum);
        });

        for (String word : note) {
            if (!words.containsKey(word)) {
                return false;
            }
            words.computeIfPresent(word, (w, count) -> {
                if (count - 1 == 0) {
                    return null;
                } else {
                    return count - 1;
                }
            });
        }

        return true;
    }

    // https://www.hackerrank.com/challenges/ctci-ransom-note/problem
    public static void checkMagazine(List<String> magazine, List<String> note) {
        boolean res = checkMagazineInner(magazine, note);

        if (res) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    // https://www.hackerrank.com/challenges/two-strings/problem
    public static String twoStrings(String s1, String s2) {
        Set<Character> h1 = new HashSet<>(
                s1.chars().mapToObj(c -> (char) c).collect(toList()));
        Set<Character> h2 = new HashSet<>(
                s2.chars().mapToObj(c -> (char) c).collect(toList()));

        h1.retainAll(h2);

        if (!h1.isEmpty()) {
            return "YES";
        } else {
            return "NO";
        }
    }

    // https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem
    public static int sherlockAndAnagrams(String s) {
        int n = s.length();
        int count = 0;

        for (int size = 1; size <= n - 1; size++) {
            Map<String, Integer> subCounts = new HashMap<>();
            for (int i = 0; i + size <= n; i++) {
                char[] substr = s.substring(i, i + size).toCharArray();
                Arrays.sort(substr);
                String sortedStr = new String(substr);

                subCounts.merge(sortedStr, 1, (total, curr) -> total + curr);
            }
            count += subCounts.values().stream().mapToInt(c -> {
                int p = 0;
                for (int e = c - 1; e >= 1; e--) {
                    p += e;
                }

                return p;
            }).sum();
        }

        return count;
    }

    // TODO: Finish this
    // https://www.hackerrank.com/challenges/count-triplets-1/problem
    static long countTriplets(List<Long> arr, long r) {
        long count = 0;
        Map<Long, Integer> counter = new HashMap<>();
        Map<Long, Integer> pairCounter = new HashMap<>();

        if (r == 1) {
            // TODO: Handle this case
            long n = arr.size() + 1;
            long total = 0;
            while (n > 0) {
                total += n;
                n /= 2;
            }
            return total;
        }

        for (int i = arr.size() - 1; i >= 0; i--) {
            long current = arr.get(i);
            if (pairCounter.containsKey(current * r)) {
                count += pairCounter.get(current * r);
            }
            if (counter.containsKey(current * r)) {
                pairCounter.put(
                        current,
                        pairCounter.getOrDefault(current, 0)
                                + counter.get(current * r));
            }
            counter.put(current, counter.getOrDefault(current, 0) + 1);
        }

        System.out.println(count);
        return count;
    }

    // https://www.hackerrank.com/challenges/frequency-queries/problem
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> outputs = new ArrayList<>();
        Map<Integer, Integer> numToCount = new HashMap<>();
        Map<Integer, Set<Integer>> countToNum = new HashMap<>();

        for (List<Integer> q : queries) {
            int num = q.get(1);
            int op = q.get(0);
            int count = numToCount.getOrDefault(num, 0);
            Set<Integer> numSet = countToNum.getOrDefault(count, new HashSet<>());

            if (op < 3) {
                numSet.remove(num);
                if (numSet.isEmpty()) {
                    countToNum.remove(count);
                }
            }

            switch (op) {
                case 1:
                    count++;
                    break;
                case 2:
                    count--;
                    break;
                case 3:
                    if (countToNum.containsKey(num)) {
                        outputs.add(1);
                    } else {
                        outputs.add(0);
                    }
                    break;
            }
            if (count < 1) {
                numToCount.remove(num);
            } else {
                numToCount.put(num, count);
                Set<Integer> set = countToNum.getOrDefault(count, new HashSet<>());

                set.add(num);
                countToNum.put(count, set);
            }
        }

        return outputs;
    }
}
