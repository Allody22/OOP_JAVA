package ru.nsu.mbogdanov2.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * The `JSONReader` class is responsible
 * for reading and parsing a JSON file into a PizzeriaJSON object.
 */
@Getter
@Setter
public class JsonReader {
    private static final String DEFAULT_FILE_NAME = "pizzeria.json";
    private final File file;
    private BufferedReader reader;

    /**
     * Constructor for fileReader class that sets the default file for reading.
     */
    public JsonReader() {
        this.file = new File(DEFAULT_FILE_NAME);
    }

    /**
     * Opens the file for reading.
     * If the file does not exist, it will be created.
     * If an error occurs while creating the file or the reader,
     * an exception will be printed to the console.
     */
    public void open() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Reads all the lines from the file and returns it as a single string.
     * If an error occurs while reading the file, an exception will be thrown.
     *
     * @param reader the buffered reader to be used for reading the file
     * @return a string containing all the lines from the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            content.append(currentLine).append("\n");
        }
        return content.toString();
    }

    /**
     * Reads the JSON file and returns a PizzeriaJSON object.
     * If an error occurs while reading or parsing the file,
     * an exception will be printed to the console and null will be returned.
     *
     * @return a PizzeriaJSON object containing the parsed JSON data, or null if an error occurs
     */
    public PizzeriaJson read() {
        String content;
        try {
            content = readAllLines(reader);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }

        if (content.isEmpty()) {
            System.err.println("Failed to start pizzeria application.");
            System.exit(1);
        }
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(content, PizzeriaJson.class);
    }

    /**
     * Closes the file reader.
     * If an error occurs while closing the reader, an exception will be printed to the console.
     */
    public void close() {
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}