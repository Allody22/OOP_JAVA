package ru.nsu.mbogdanov2;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlgorithmTest {
    @Test
    public void smallTest() throws IOException {
        try (var file =
                getClass().getClassLoader().getResourceAsStream("test.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            String txt = sc.nextLine();
            String pat = "abv";

            RabinKarp searcher = new RabinKarp(pat);
            int firstIn = searcher.search(txt);

            Assertions.assertEquals(0,firstIn);

        }

    }

}
