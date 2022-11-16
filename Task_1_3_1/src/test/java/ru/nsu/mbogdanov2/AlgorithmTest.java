package ru.nsu.mbogdanov2;


import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for this algorithm.
 * A lot of tests for all cases of life
 */
public class AlgorithmTest {

    /**
     * This is time test and its result is not so good.
     * Java has method indexOf that returns with index of substring in string
     * And I decided to test the speed of this function and my algorithm.
     * I made a file with one big line and one substring at the end of the file.
     * My algorithm is slower by  times on average, but KMP can find all
     * indexes of substring and can work with the whole file (not only 1 string)
     * I'm testing algorithms around 100 times for correct calculations
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void timeTest() throws IOException {
        long duration = 0;
        for (int i = 0; i < 100; i++) {
            long startTime = System.nanoTime();

            String pattern = "QWE";
            KnuthMorrisPratt ans = new KnuthMorrisPratt(new BufferedReader(new InputStreamReader(
                    Objects.requireNonNull(getClass().getClassLoader()
                            .getResourceAsStream("timeTest.txt")))), pattern);

            long endTime = System.nanoTime();
            duration += (endTime - startTime);
        }
        duration /= 100;

        long durationJava = 0;
        for (int i = 0; i < 100; i++) {
            long startTime2 = System.nanoTime();

            BufferedReader file2 = new BufferedReader(new InputStreamReader(
                    Objects.requireNonNull(getClass().getClassLoader()
                            .getResourceAsStream("timeTest.txt"))));
            String line = file2.readLine();
            int pos2 = line.indexOf("QWE");

            long endTime2 = System.nanoTime();
            durationJava += (endTime2 - startTime2);
        }
        durationJava /= 100;
        Assertions.assertTrue(duration < durationJava * 3);
    }

    /**
     * Test for empty file.
     * We can see that my algorithm returns an empty list
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void emptyFileTest() throws IOException {
        try (var file = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream("emptyFile.txt"))))) {
            String pattern = " ";
            KnuthMorrisPratt actual = new KnuthMorrisPratt(file, pattern);
            List<Integer> expected = new ArrayList<>();
            Assertions.assertEquals(expected, actual.ansList);
        }
    }

    /**
     * We check algorithms exceptions.
     * User can't use empty or null substring, because it's useless
     */
    @Test
    public void exceptionsTest() throws IOException {
        try (var file = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream("timeTest.txt"))))) {
            IllegalArgumentException exceptionNull = assertThrows(IllegalArgumentException.class,
                    () -> new KnuthMorrisPratt(file, null));
            Assertions.assertEquals("Invalid substring", exceptionNull.getMessage());
        }
        try (var file = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream("timeTest.txt"))))) {
            String pattern = "";
            IllegalArgumentException exceptionEmptyPattern = assertThrows(
                    IllegalArgumentException.class, () -> new KnuthMorrisPratt(file, pattern));
            Assertions.assertEquals("Invalid substring", exceptionEmptyPattern.getMessage());
        }
    }

    /**
     * Test with only substring in original text.
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void firstElementTest() throws IOException {
        try (var file = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream("firstElement.txt"))))) {
            String pattern = "qwerty";
            KnuthMorrisPratt actual = new KnuthMorrisPratt(file, pattern);
            List<Integer> expected = new ArrayList<>();
            expected.add(0);
            Assertions.assertEquals(expected, actual.ansList);
        }
    }

    /**
     * Test with only one element in text and substring.
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void oneElementTest() throws IOException {
        try (var file = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream("one.txt"))))) {
            String pattern = "a";
            KnuthMorrisPratt actual = new KnuthMorrisPratt(file, pattern);
            Assertions.assertEquals(1, actual.ansList.size());
        }
    }

    /**
     * Test with only one element in text and substring.
     */
    @Test
    public void prefixFuncTest() {
        String word1 = "aaaa";
        var prefixArrayActual = KnuthMorrisPratt.findPrefixArray(word1);
        int[] prefixArrayExpected = {-1, 0, 1, 2, 3};
        Assertions.assertArrayEquals(prefixArrayExpected, prefixArrayActual);

        String word2 = "a";
        prefixArrayActual = KnuthMorrisPratt.findPrefixArray(word2);
        prefixArrayExpected = new int[]{-1, 0};
        Assertions.assertArrayEquals(prefixArrayExpected, prefixArrayActual);
    }
}

