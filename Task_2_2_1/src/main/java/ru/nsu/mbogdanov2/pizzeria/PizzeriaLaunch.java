package ru.nsu.mbogdanov2.pizzeria;

import ru.nsu.mbogdanov2.json.BakerJson;
import ru.nsu.mbogdanov2.json.CourierJson;
import ru.nsu.mbogdanov2.json.JsonReader;
import ru.nsu.mbogdanov2.json.PizzeriaJson;

/**
 * This class reads the parameters of the pizzeria from the file and launches it.
 */
public class PizzeriaLaunch implements Runnable {

    /**
     * The running time of the pizzeria in milliseconds.
     */
    private final static long RUNNING_TIME = 30 * 500;

    /**
     * The PizzeriaJSON object that contains information about the pizzeria.
     */
    private PizzeriaJson pizzeriaJSON;

    /**
     * The PizzeriaReader object that represents the pizzeria.
     */
    private PizzeriaReader pizzeriaReader;

    /**
     * This method sets the PizzeriaJSON object by reading it from the file.
     */
    private void setPizzeriaJSON() {
        JsonReader jsonReader = new JsonReader();
        jsonReader.open();
        pizzeriaJSON = jsonReader.read();
        jsonReader.close();
    }

    /**
     * This method sets the PizzeriaReader object based on the information from the PizzeriaJSON object.
     * If there is an error with the JSON file, the method prints an error message to the console.
     */
    private void setPizzeria() {
        if (pizzeriaJSON == null) {
            System.err.println("No pizzeria JSON.");
            return;
        }
        if (pizzeriaJSON.queueSize() <= 0) {
            System.err.println("Queue size must be greater than zero.");
            return;
        }
        if (pizzeriaJSON.storageSize() <= 0) {
            System.err.println("Storage size must be greater than zero.");
            return;
        }
        BakerJson[] bakersJSON = pizzeriaJSON.bakers();
        if (bakersJSON == null || bakersJSON.length == 0) {
            System.err.println("No information about bakers in the JSON file.");
            return;
        }
        CourierJson[] couriersJSON = pizzeriaJSON.couriers();
        if (couriersJSON == null || couriersJSON.length == 0) {
            System.err.println("No information about bakers in the JSON file.");
            return;
        }
        pizzeriaReader = new PizzeriaReader(pizzeriaJSON);
    }

    /**
     * Constructor of the PizzeriaApplication class.
     */
    public PizzeriaLaunch() {
        setPizzeriaJSON();
        setPizzeria();
    }

    @Override
    public void run() {
        if (pizzeriaReader == null) {
            System.err.println("Failed to start pizzeria application.");
            System.exit(1);
        }
        Thread pizzeriaThread = new Thread(pizzeriaReader);
        pizzeriaThread.start();
        try {
            Thread.sleep(RUNNING_TIME);
            pizzeriaReader.stop();
            System.exit(0);
        } catch (InterruptedException exception) {
            System.err.println("The pizzeria application ended with an error.");
            System.exit(2);
        }
    }
}
