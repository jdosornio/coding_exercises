package me.jdosornio.structures.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringExercisesTest {

    @Test
    public void countingValleys() {
        String input = "UDDDUDUU";
        int output = StringExercises.countingValleys(input.length(), input);

        assertEquals(1, output);

        input = "DDUUDDUDUUUD";
        output = StringExercises.countingValleys(input.length(), input);

        assertEquals(2, output);
    }

    @Test
    public void repeatedString() {
        String input = "aba";
        long output = StringExercises.repeatedString(input, 10);

        assertEquals(7, output);

        input = "a";
        output = StringExercises.repeatedString(input, 1000000000000L);

        assertEquals(1000000000000L, output);
    }

    @Test
    public void makeAnagram() {
        String a = "cde";
        String b = "abc";
        int output = StringExercises.makeAnagram(a, b);

        assertEquals(4, output);

        a = "fcrxzwscanmligyxyvym";
        b = "jxwtrhvujlmrpdoqbisbwhmgpmeoke";
        output = StringExercises.makeAnagram(a, b);

        assertEquals(30, output);

        a = "showman";
        b = "woman";
        output = StringExercises.makeAnagram(a, b);

        assertEquals(2, output);
    }

    @Test
    public void alternatingCharacters() {
        String input = "AAAA";
        int output = StringExercises.alternatingCharacters(input);

        assertEquals(3, output);

        input = "BBBBB";
        output = StringExercises.alternatingCharacters(input);

        assertEquals(4, output);

        input = "ABABABAB";
        output = StringExercises.alternatingCharacters(input);

        assertEquals(0, output);

        input = "BABABA";
        output = StringExercises.alternatingCharacters(input);

        assertEquals(0, output);

        input = "AAABBB";
        output = StringExercises.alternatingCharacters(input);

        assertEquals(4, output);
    }
}
