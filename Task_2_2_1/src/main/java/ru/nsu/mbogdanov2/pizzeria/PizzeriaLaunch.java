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
    private static final long RUNNING_TIME = 30 * 500;

    /**
     * The PizzeriaJSON object that contains information about the pizzeria.
     */
    private PizzeriaJson pizzeriaJson;

    /**
     * The PizzeriaReader object that represents the pizzeria.
     */
    private PizzeriaReader pizzeriaReader;

    /**
     * This method sets the PizzeriaJSON object by reading it from the file.
     */
    private void setPizzeriaJson() {
        JsonReader jsonReader = new JsonReader();
        jsonReader.open();
        pizzeriaJson = jsonReader.read();
        jsonReader.close();
    }

    /**
     * This method sets the PizzeriaReader object
     * based on the information from the PizzeriaJSON object.
     * If there is an error with the JSON file,
     * the method prints an error message to the console.
     */
    private void setPizzeria() {
        if (pizzeriaJson == null) {
            System.err.println("No pizzeria JSON.");
            return;
        }
        if (pizzeriaJson.getQueueSize() <= 0) {
            System.err.println("Queue size must be greater than zero.");
            return;
        }
        if (pizzeriaJson.getStorageSize() <= 0) {
            System.err.println("Storage size must be greater than zero.");
            return;
        }
        BakerJson[] bakersJson = pizzeriaJson.getBakers();
        if (bakersJson == null || bakersJson.length == 0) {
            System.err.println("No information about bakers in the JSON file.");
            return;
        }
        CourierJson[] couriersJson = pizzeriaJson.getCouriers();
        if (couriersJson == null || couriersJson.length == 0) {
            System.err.println("No information about bakers in the JSON file.");
            return;
        }
        pizzeriaReader = new PizzeriaReader(pizzeriaJson);
    }

    /**
     * Constructor of the PizzeriaApplication class.
     */
    public PizzeriaLaunch() {
        setPizzeriaJson();
        setPizzeria();
    }

    @Override
    public void run() {
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
