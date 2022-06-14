package me.jdosornio.structures.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExercises {

    // https://www.hackerrank.com/challenges/counting-valleys/problem
    public static int countingValleys(int steps, String path) {
        int prevLevel = 0;
        int level = 0;
        int valleyCount = 0;

        for (int i = 0; i < steps; i++) {
            char step = path.charAt(i);

            if (step == 'U') {
                level += 1;
            } else {
                level -= 1;
            }

            if (prevLevel == 0 && level < 0) {
                valleyCount++;
            }
            prevLevel = level;
        }

        return valleyCount;
    }

    // https://www.hackerrank.com/challenges/repeated-string/problem
    public static long repeatedString(String s, long n) {
        long times = n / s.length();
        long aCount = s.chars().filter(c -> c == 'a').count();
        long remain = n % s.length();
        String lastPiece = s.substring(0, (int) remain);

        return times * aCount + lastPiece.chars().filter(c -> c == 'a').count();
    }

    // https://www.hackerrank.com/challenges/ctci-making-anagrams/problem
    public static int makeAnagram(String a, String b) {
        Map<Character, Integer> counts = new HashMap<>();

        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            counts.merge(c, 1, (count, curr) -> count + curr);
        }
        for (int i = 0; i < b.length(); i++) {
            char c = b.charAt(i);
            counts.put(c, counts.getOrDefault(c, 0) - 1);
        }

        return counts.values().stream().reduce(0, (c1, c2) -> Math.abs(c1) + Math.abs(c2));

    }

    // https://www.hackerrank.com/challenges/alternating-characters/problem
    public static int alternatingCharacters(String s) {
        Pattern p = Pattern.compile("(A+{2,}|B+{2,})");
        Matcher match = p.matcher(s);
        int count = 0;

        while (match.find()) {
            count += match.group().length() - 1;
        }

        return count;
    }

    // https://leetcode.com/problems/longest-palindromic-substring/submissions/
    public String longestPalindrome(String s) {
        int maxLength = 0;
        String palindrome = "";

        for (int i = 0, n = s.length(); i < n; i++) {
            int left = i, right = i;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                int palLength = right - left + 1;
                if (maxLength < palLength) {
                    maxLength = palLength;
                    palindrome = s.substring(left, right + 1);
                }
                left--;
                right++;
            }

            left = i;
            right = i + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                int palLength = right - left + 1;
                if (maxLength < palLength) {
                    maxLength = palLength;
                    palindrome = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
        }

        return palindrome;
    }
}
