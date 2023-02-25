package ru.nsu.mbogdanov2.speed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov2.PrimeSearch;
import ru.nsu.mbogdanov2.PrimeSearchWithStreams;
import ru.nsu.mbogdanov2.PrimeSearchWithThread;
import ru.nsu.mbogdanov2.PrimeSearchWithThreadsBySeparation;

/**
 * We perform algorithms sequentially, with streams and with different number of threads.
 * The result is on the performanceTest image.
 */
public class PerformanceTest {
    private int[] createArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 1073676287);
        return array;
    }

    private long singleTest(PrimeSearch notPrimeSearch, int[] array) {
        List<Long> results = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            long time = System.nanoTime();
            notPrimeSearch.search(array);
            time = System.nanoTime() - time;
            results.add(time);
        }
        return Collections.min(results);
    }

    private long singleTestForThreads(PrimeSearchWithThread notPrimeSearch, int[] array,
                                      int threadsNumber) throws ExecutionException,
            InterruptedException {
        List<Long> results = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            long time = System.nanoTime();
            notPrimeSearch.search(array, false, threadsNumber);
            time = System.nanoTime() - time;
            results.add(time);
        }
        return Collections.min(results);
    }

    private long singleTestForThreadsBySeparations(PrimeSearchWithThreadsBySeparation notPrimeSearch, int[] array,
                                                   int threadsNumber) {
        List<Long> results = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Integer[] array2 = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Collections.addAll(list, array2);
        for (int i = 0; i < 3; ++i) {
            long time = System.nanoTime();
            notPrimeSearch.search(threadsNumber, false, list);
            time = System.nanoTime() - time;
            results.add(time);
        }
        return Collections.min(results);
    }

    @Test
    public void performanceTest() throws ExecutionException, InterruptedException, IOException {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Prime Search");
        XYSeries series2 = new XYSeries("Streams");
        XYSeries series3 = new XYSeries("8 threads Sep");
        XYSeries series4 = new XYSeries("15 threads Sep");
        XYSeries series5 = new XYSeries("8 threads");
        XYSeries series6 = new XYSeries("30 threads Sep");
        XYSeries series7 = new XYSeries("15 threads");
        XYSeries series8 = new XYSeries("30 threads");

        for (int size = 10; size <= 1000; size *= 10) {
            int[] array = createArray(size);
            long time = singleTest(new PrimeSearch(), array);
            series1.add(size, time);
            time = singleTest(new PrimeSearchWithStreams(), array);
            series2.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 8);
            series5.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 15);
            series7.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 30);
            series8.add(size, time);
            time = singleTestForThreadsBySeparations(new PrimeSearchWithThreadsBySeparation(), array, 8);
            series3.add(size, time);
            time = singleTestForThreadsBySeparations(new PrimeSearchWithThreadsBySeparation(), array, 15);
            series4.add(size, time);
            time = singleTestForThreadsBySeparations(new PrimeSearchWithThreadsBySeparation(), array, 30);
            series6.add(size, time);
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);
        dataset.addSeries(series6);
        dataset.addSeries(series5);
        dataset.addSeries(series7);
        dataset.addSeries(series8);
        ChartRepresentation chart = new ChartRepresentation(dataset);
        chart.createFile();
    }
}