package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.json.JsonReader;
import ru.nsu.mbogdanov2.json.PizzeriaJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for JsonReader class.
 */
public class JsonReaderTest {

    @Test
    public void testRead() {
        JsonReader reader = new JsonReader();
        reader.open();
        PizzeriaJson pizzeria = reader.read();
        reader.close();
        assertNotNull(pizzeria);
    }

    @Test
    public void testReadAllLines() throws IOException {
        String testString = "Hello\nworld!\n";
        Reader stringReader = new StringReader(testString);
        BufferedReader bufferedReader = new BufferedReader(stringReader);

        JsonReader reader = new JsonReader();
        String content = reader.readAllLines(bufferedReader);

        assertEquals(testString, content);
    }

    @Test
    public void testOpen() {
        JsonReader reader = new JsonReader();
        reader.open();
        assertNotNull(reader.getReader());
        reader.close();
    }
}
