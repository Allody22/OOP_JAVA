package ru.nsu.mbogdanov2;


import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testing of this algorithm
 * A lot of tests for all cases of life
 */
public class AlgorithmTest {

    /*@Test
    public void warAndPeaceTest() throws IOException {
        long startTime = System.nanoTime();

        String name = "C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src\\test\\resources\\warAndPeaceFirstTome.txt";
        String pattern = "Разговор показался ему интересен";
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
        KnuthMorrisPratt ans = new KnuthMorrisPratt(file, pattern);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Duration for War And Peace test - " + duration + " nanoseconds");
        System.out.println("Answer for War And Peace: " + ans.ansList);
    }

    @Test
    public void oneBigLine2GBTest() throws IOException {
        long startTime = System.nanoTime();
        BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src\\test\\resources\\2GB.txt")));
        String pattern = "aaa";
        KnuthMorrisPratt ans = new KnuthMorrisPratt(file, pattern);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Duration for 2GB test - " + duration + " nanoseconds");
        System.out.println("Answer for 2GB: " + ans.ansList);
    }

    @Test
    public void tenGBTest() throws IOException {
        long startTime = System.nanoTime();
        BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src\\test\\resources\\10GB.txt")));
        String pattern = "aaa";
        KnuthMorrisPratt ans = new KnuthMorrisPratt(file, pattern);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Duration for 10GB test - " + duration + " nanoseconds");
        System.out.println("Answer for 10GB: " + ans.ansList);
    }*/

    /** This is time test and its result is not so good.
     * Java has method indexOf that returns with index of substring in string
     * And I decided to test the speed of this function and my algorithm.
     * I made a file with one big line and one substring at the end of the file.
     * My algorithm is slower by 10 times on average, but KMP can find all
     * indexes of substring and can work with the whole file (not only 1 string)
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void timeTest() throws IOException {
        long startTime = System.nanoTime();

        String nameOfFile = "C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src\\test\\resources\\timeTest.txt";
        String pattern = "QWE";
        KnuthMorrisPratt ans = new KnuthMorrisPratt(new BufferedReader(new InputStreamReader(new FileInputStream(nameOfFile))), pattern);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        long startTime2 = System.nanoTime();

        BufferedReader file2 = new BufferedReader(new InputStreamReader(new FileInputStream(nameOfFile)));
        String line = file2.readLine();
        int pos2 = line.indexOf("QWE");

        long endTime2 = System.nanoTime();
        long durationJava = (endTime2 - startTime2);
        Assertions.assertTrue(duration < durationJava * 12);
    }

    /** Test for empty file.
     * We can see that my algorithm returns an empty list
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void emptyFileTest() throws IOException {
        String nameOfFile = "C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src" +
                "\\test\\resources\\emptyFile.txt";
        String pattern = " ";
        KnuthMorrisPratt actual = new KnuthMorrisPratt(new BufferedReader(new InputStreamReader(new FileInputStream(nameOfFile))), pattern);
        List<Integer> expected = new ArrayList<>();
        Assertions.assertEquals(expected, actual.ansList);
    }

    /** We check algorithms exceptions.
     * User can't use empty or null substring, because it's useless
     *
     */
    @Test
    public void exceptionsTest() {
        String nameOfFile = "C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src" +
                "\\test\\resources\\timeTest.txt";
        IllegalArgumentException exceptionNull = assertThrows(IllegalArgumentException.class,
                () -> new KnuthMorrisPratt(new BufferedReader(new InputStreamReader
                        (new FileInputStream(nameOfFile))), null));
        Assertions.assertEquals("Invalid substring", exceptionNull.getMessage());

        String pattern = "";
        IllegalArgumentException exceptionEmptyPattern = assertThrows(IllegalArgumentException.class,
                () -> new KnuthMorrisPratt(new BufferedReader(new InputStreamReader
                        (new FileInputStream(nameOfFile))), pattern));
        Assertions.assertEquals("Invalid substring", exceptionEmptyPattern.getMessage());
    }

    /** Test with only substring in original text.
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void firstElementTest() throws IOException {
        String nameOfFile = "C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src" +
                "\\test\\resources\\firstElement.txt";
        String pattern = "qwerty";
        KnuthMorrisPratt actual = new KnuthMorrisPratt(new BufferedReader(new InputStreamReader(new FileInputStream(nameOfFile))), pattern);
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        Assertions.assertEquals(expected, actual.ansList);
    }

    /** Test with only one element in text and substring.
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void oneElementTest() throws IOException {
        String nameOfFile = "C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src" +
                "\\test\\resources\\one.txt";
        String pattern = "a";
        KnuthMorrisPratt actual = new KnuthMorrisPratt(new BufferedReader(new InputStreamReader(new FileInputStream(nameOfFile))), pattern);
        Assertions.assertEquals(1, actual.ansList.size());
    }
}

