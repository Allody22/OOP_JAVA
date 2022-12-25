package ru.nsu.mbogdanov2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.cli.*;

/**
 * Notebook class that implements cmd options and serialization.
 */
public class NoteBook {
    public static File notes = new File("notes.json");
    private static final Options parserOptions = new Options();
    public static ArrayOfNotes notesArray = new ArrayOfNotes();

    static {
        newOpt("show", "Show all nodes in "
                + "special time interval or by keyword ", Option.UNLIMITED_VALUES);
        newOpt("add", "Add new note with title and description", 2);
        newOpt("rm", "Delete one note by title", 1);
        if (!notes.exists()) {
            try {
                notes.createNewFile();
                Gson gson = new Gson();
                try (FileWriter writer = new FileWriter(notes)) {
                    gson.toJson(notesArray, writer);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Gson gson = new Gson();
        try {
            notesArray = gson
                    .fromJson(new BufferedReader(new FileReader(notes)), ArrayOfNotes.class);
            if (notesArray == null) {
                try (FileWriter writer = new FileWriter(notes)) {
                    writer.write("{}");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Main works that allows user to work with notebook.
     *
     * @param args command line arguments
     * @throws java.text.ParseException exception with parsing data
     */
    public static void main(String[] args) throws java.text.ParseException {
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(parserOptions, args);
            if (cmd.hasOption("add") && args.length == 3) {
                addNote(cmd);
            } else if (cmd.hasOption("rm") && args.length == 2) {
                removeNote(cmd);
            } else if (cmd.hasOption("show")) {
                showRecord(cmd, args.length);
            } else {
                throw new IllegalAccessException("Something wrong with your requests");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void newOpt(String nameOpt, String description, int countArgs) {
        Option opt = new Option(nameOpt, description);
        opt.setOptionalArg(true);
        opt.setArgs(countArgs);
        parserOptions.addOption(opt);
    }

    private static void addNote(CommandLine cmd) throws IllegalAccessException {
        String[] arguments = cmd.getOptionValues("add");
        if (arguments.length != 2) {
            throw new IllegalAccessException("Something wrong with your request");
        }
        notesArray.addNote(new Note(arguments[0], arguments[1]));
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(notes)) {
            gson.toJson(notesArray, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void removeNote(CommandLine cmd) throws IllegalAccessException {
        String[] arguments = cmd.getOptionValues("rm");
        if (arguments.length != 1 && !notesArray.checkExistence(arguments[0])) {
            throw new IllegalAccessException("Something wrong with your requests");
        }
        notesArray.removeNoteByTitle(arguments[0]);
        Gson gson = new GsonBuilder().create();
        try (FileWriter writer = new FileWriter(notes)) {
            gson.toJson(notesArray, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showRecord(CommandLine cmd, int argsLength)
            throws java.text.ParseException, IllegalAccessException {
        String[] arguments = new String[argsLength - 1];
        if (argsLength != 1) {
            arguments = cmd.getOptionValues("show");
        }
        if (arguments.length == 1) {
            throw new IllegalAccessException("Something wrong with your requests");
        }
        ArrayOfNotes notesInTime = new ArrayOfNotes();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy, H:m:s a", Locale.ENGLISH);
        Date firstDate = formatter.parse(arguments[0]);
        Date secondDate = formatter.parse(arguments[1]);
        if (arguments.length == 2) {
            for (int i = 0; i < notesArray.getSize(); i++) {
                var currentNote = notesArray.getById(i);
                if (currentNote.getTimeOfNote().after(firstDate)) {
                    while (currentNote.getTimeOfNote().before(secondDate)
                            && i < notesArray.getSize()) {
                        notesInTime.addNote(currentNote);
                        i++;
                        if (i < notesArray.getSize()) {
                            currentNote = notesArray.getById(i);
                        }
                    }
                    System.out.println(notesInTime.stringify());
                    return;
                }

            }
        } else {
            List<String> keywords =
                    new ArrayList<>(Arrays.asList(arguments).subList(2, arguments.length));
            for (int i = 0; i < notesArray.getSize(); i++) {
                var currentNote = notesArray.getById(i);
                if (currentNote.getTimeOfNote().after(firstDate)
                        && currentNote.getTimeOfNote().before(secondDate)) {
                    while (currentNote.getTimeOfNote().before(secondDate)) {
                        for (var oneKeyword : keywords) {
                            if (currentNote.getTitle().contains(oneKeyword)) {
                                notesInTime.addNote(currentNote);
                                break;
                            }
                        }
                        i++;
                        if (i < notesArray.getSize()) {
                            currentNote = notesArray.getById(i);
                        }
                    }
                    System.out.println(notesInTime.stringify());
                    return;
                }
            }
        }
    }

}

