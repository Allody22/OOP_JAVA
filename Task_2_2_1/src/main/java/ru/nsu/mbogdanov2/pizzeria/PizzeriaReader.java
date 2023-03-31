package ru.nsu.mbogdanov2.pizzeria;

import ru.nsu.mbogdanov2.json.BakerJson;
import ru.nsu.mbogdanov2.model.people.Customers;
import ru.nsu.mbogdanov2.model.people.Baker;
import ru.nsu.mbogdanov2.model.people.Courier;
import ru.nsu.mbogdanov2.json.CourierJson;
import ru.nsu.mbogdanov2.json.PizzeriaJson;
import ru.nsu.mbogdanov2.model.order.Order;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * This class sets JSON with main pizzeria fields and implements run method.
 */
public class PizzeriaReader implements Runnable {
    private boolean runPizzeria;
    private List<Baker> bakers;
    private List<Courier> couriers;
    private final Customers customers;
    private final MyBlockingDeque<Order> queue;
    private final MyBlockingDeque<Order> storage;

    private void setBakers(BakerJson[] bakers) {
        Stream<BakerJson> bakerJsonStream = Arrays.stream(bakers);
        this.bakers = bakerJsonStream
                .map(bakerJSON -> new Baker(bakerJSON.getId(), bakerJSON.getWorkingExperience(),
                        this.queue, this.storage))
                .toList();
    }

    private void setCouriers(CourierJson[] couriers) {
        Stream<CourierJson> courierJsonStream = Arrays.stream(couriers);
        this.couriers = courierJsonStream
                .map(courierJson -> new Courier(courierJson.getId(),
                        courierJson.getBagCapacity(), this.storage))
                .toList();
    }

    /**
     * Sets up a pizzeria based on the pizzeriaJson content.
     *
     * @param settings - pizzeria configuration.
     */
    public PizzeriaReader(PizzeriaJson settings) {
        this.runPizzeria = false;
        this.queue = new MyBlockingDeque<>(settings.getQueueSize());
        this.storage = new MyBlockingDeque<>(settings.getStorageSize());
        this.customers = new Customers(this.queue);
        setBakers(settings.getBakers());
        setCouriers(settings.getCouriers());
    }

    /**
     * Launches a pizzeria.
     * The pizzeria stops working either when the stop method is called, or when an error occurs.
     */
    @Override
    public void run() {
        runPizzeria = true;
        ExecutorService bakersThreadPool = Executors.newFixedThreadPool(bakers.size());
        ExecutorService couriersThreadPool = Executors.newFixedThreadPool(couriers.size());
        bakers.forEach(bakersThreadPool::execute);
        couriers.forEach(couriersThreadPool::execute);
        Thread customersThread = new Thread(customers);
        customersThread.start();
        System.out.println("The pizzeria is up and running!");
        while (runPizzeria && !bakersThreadPool.isTerminated()
                && !couriersThreadPool.isTerminated()) {
            synchronized (this) {
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (bakersThreadPool.isTerminated() || couriersThreadPool.isTerminated()) {
            runPizzeria = false;
            System.out.println("Oops, something went wrong. "
                    + "The pizzeria is closed for a technical break.");
        }
        customersThread.interrupt();
        customers.stop();
        bakersThreadPool.shutdownNow();
        couriersThreadPool.shutdownNow();
    }

    /**
     * Stops the pizzeria from working.
     */
    public void stop() {
        runPizzeria = false;
    }
}
