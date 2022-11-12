package ru.nsu.mbogdanov2;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

/**
 * Testing of this algorithm
 * A lot of tests for all cases of life
 */
public class AlgorithmTest {
    /*@Test
    public void oneBigLineTest() throws IOException {
        try (BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src\\test\\resources\\test.txt"), StandardCharsets.UTF_8))) {
            String pattern = "aaa";
            KnuthMorrisPratt ans = new KnuthMorrisPratt(file, pattern);
            System.out.println(ans.ansList);
        }
    }

    @Test
    public void severalLinesTest() throws IOException {
        try (BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src\\test\\resources\\new.in"), StandardCharsets.UTF_8))) {
            String pattern = "aaa";
            KnuthMorrisPratt ans = new KnuthMorrisPratt(file, pattern);
            System.out.println(ans.ansList);
        }
    }*/

    @Test
    public void timeTest() throws IOException {

        long startTime = System.nanoTime();
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src\\test\\resources\\text.txt"), StandardCharsets.UTF_8));
        String pattern = "QWE";
        KnuthMorrisPratt ans = new KnuthMorrisPratt(file, pattern);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("duration OneLine " +duration);
        System.out.println(ans.ansList);

        long startTime2 = System.nanoTime();
        BufferedReader file2 = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src\\test\\resources\\text.txt"), StandardCharsets.UTF_8));
        String line = file2.readLine();
        int pos2 = line.indexOf("QWE");
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);
        System.out.println("duration OneLine indexOf " +duration2);
        System.out.println(pos2);
        //Assertions.assertTrue(duration>duration2);
    }

    /*@Test
    public void oneBigLineTest123() throws IOException {
        String name = "C:\\Users\\IAMNO\\OOP_JAVA\\Task_1_3_1\\src\\test\\resources\\10GB.txt";
        String pattern = "HelloWorld";
        BufferedReader file = new BufferedReader(new InputStreamReader(
                new FileInputStream(name),
                StandardCharsets.UTF_8));
        KnuthMorrisPratt ans = new KnuthMorrisPratt(file, pattern);

        System.out.println("VeryVeryBig " + ans.ansList);
    }*/
}

