package ru.nsu.mbogdanov2.pizzeria;

import ru.nsu.mbogdanov2.json.BakerJSON;
import ru.nsu.mbogdanov2.json.CourierJSON;
import ru.nsu.mbogdanov2.json.JSONReader;
import ru.nsu.mbogdanov2.json.PizzeriaJSON;

/**
 * This class reads the parameters of the pizzeria from the file and launches it.
 */
public class PizzeriaLaunch implements Runnable {
    private final static long RUNNING_TIME = 30 * 500;
    private PizzeriaJSON pizzeriaJSON;
    private PizzeriaReader pizzeriaReader;

    private void setPizzeriaJSON() {
        JSONReader jsonReader = new JSONReader();
        jsonReader.open();
        pizzeriaJSON = jsonReader.read();
        jsonReader.close();
    }

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
        BakerJSON[] bakersJSON = pizzeriaJSON.bakers();
        if (bakersJSON == null || bakersJSON.length == 0) {
            System.err.println("No information about bakers in the JSON file.");
            return;
        }
        CourierJSON[] couriersJSON = pizzeriaJSON.couriers();
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
