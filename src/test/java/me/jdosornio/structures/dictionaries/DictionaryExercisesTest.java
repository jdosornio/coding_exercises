package me.jdosornio.structures.dictionaries;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DictionaryExercisesTest {

    @Test
    public void checkMagazine() {
        List<String> mag = Arrays.asList("give", "me", "one", "grand", "today", "night");
        List<String> note = Arrays.asList("give", "one", "grand", "today");
        boolean output = DictionaryExercises.checkMagazineInner(mag, note);

        assertEquals(true, output);

        mag = Arrays.asList("two", "times", "three", "is", "not", "four");
        note = Arrays.asList("two", "times", "two", "is", "four");
        output = DictionaryExercises.checkMagazineInner(mag, note);

        assertEquals(false, output);

        mag = Arrays.asList("ive", "got", "a", "lovely", "bunch", "of", "coconuts");
        note = Arrays.asList("ive", "got", "some", "coconuts");
        output = DictionaryExercises.checkMagazineInner(mag, note);

        assertEquals(false, output);
    }

    @Test
    public void twoStrings() {
        String s1 = "hello";
        String s2 = "world";
        String output = DictionaryExercises.twoStrings(s1, s2);

        assertEquals("YES", output);

        s1 = "hi";
        s2 = "world";
        output = DictionaryExercises.twoStrings(s1, s2);

        assertEquals("NO", output);

        s1 = "wouldyoulikefries";
        s2 = "abcabcabcabcabcabc";
        output = DictionaryExercises.twoStrings(s1, s2);

        assertEquals("NO", output);

        s1 = "hackerrankcommunity";
        s2 = "cdecdecdecde";
        output = DictionaryExercises.twoStrings(s1, s2);

        assertEquals("YES", output);

        s1 = "jackandjill";
        s2 = "wentupthehill";
        output = DictionaryExercises.twoStrings(s1, s2);

        assertEquals("YES", output);

        s1 = "writetoyourparents";
        s2 = "fghmqzldbc";
        output = DictionaryExercises.twoStrings(s1, s2);

        assertEquals("NO", output);
    }

    @Test
    public void sherlockAndAnagrams() {
        String input = "abba";
        int output = DictionaryExercises.sherlockAndAnagrams(input);

        assertEquals(4, output);

        input = "abcd";
        output = DictionaryExercises.sherlockAndAnagrams(input);

        assertEquals(0, output);

        input = "ifailuhkqq";
        output = DictionaryExercises.sherlockAndAnagrams(input);

        assertEquals(3, output);

        input = "kkkk";
        output = DictionaryExercises.sherlockAndAnagrams(input);

        assertEquals(10, output);

        input = "cdcd";
        output = DictionaryExercises.sherlockAndAnagrams(input);

        assertEquals(5, output);
    }

    @Test
    public void freqQuery() {
        List<List<Integer>> input = Arrays.asList(Arrays.asList(1, 5), Arrays.asList(1, 6),
                Arrays.asList(3, 2), Arrays.asList(1, 10), Arrays.asList(1, 10),
                Arrays.asList(1, 6), Arrays.asList(2, 5), Arrays.asList(3, 2));
        List<Integer> output = DictionaryExercises.freqQuery(input);

        assertArrayEquals(new Integer[] { 0, 1 }, output.toArray());

        input = Arrays.asList(Arrays.asList(3, 4), Arrays.asList(2, 1003),
                Arrays.asList(1, 16), Arrays.asList(3, 1));
        output = DictionaryExercises.freqQuery(input);

        assertArrayEquals(new Integer[] { 0, 1 }, output.toArray());

        input = Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 3),
                Arrays.asList(3, 2), Arrays.asList(1, 4), Arrays.asList(1, 5),
                Arrays.asList(1, 5), Arrays.asList(1, 4), Arrays.asList(3, 2),
                Arrays.asList(2, 4), Arrays.asList(3, 2));
        output = DictionaryExercises.freqQuery(input);

        assertArrayEquals(new Integer[] { 0, 1, 1 }, output.toArray());
    }
}
