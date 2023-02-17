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
        array[size - 1] = 187263196;
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

    @Test
    public void performanceTest() throws ExecutionException, InterruptedException, IOException {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Casual Prime Search");
        XYSeries series2 = new XYSeries("Prime Search with Streams");
        XYSeries series3 = new XYSeries("Prime Search with 2 threads");
        XYSeries series4 = new XYSeries("Prime Search with 4 threads");
        XYSeries series5 = new XYSeries("Prime Search with 8 threads");
        XYSeries series6 = new XYSeries("Prime Search with 15 threads");
        XYSeries series7 = new XYSeries("Prime Search with 20 threads");
        XYSeries series8 = new XYSeries("Prime Search with 25 threads");
        XYSeries series9 = new XYSeries("Prime Search with 30 threads");
        XYSeries series10 = new XYSeries("Prime Search with 35 threads");
        XYSeries series11 = new XYSeries("Prime Search with 40 threads");
        XYSeries series12 = new XYSeries("Prime Search with 100 threads");

        for (int size = 10; size <= 100000; size *= 10) {
            int[] array = createArray(size);
            long time = singleTest(new PrimeSearch(), array);
            series1.add(size, time);
            time = singleTest(new PrimeSearchWithStreams(), array);
            series2.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 2);
            series3.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 4);
            series4.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 8);
            series5.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 15);
            series6.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 20);
            series7.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 25);
            series8.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 30);
            series9.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 35);
            series10.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 40);
            series11.add(size, time);
            time = singleTestForThreads(new PrimeSearchWithThread(), array, 100);
            series12.add(size, time);
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);
        dataset.addSeries(series5);
        dataset.addSeries(series6);
        dataset.addSeries(series7);
        dataset.addSeries(series8);
        dataset.addSeries(series9);
        dataset.addSeries(series10);
        dataset.addSeries(series11);
        dataset.addSeries(series12);
        ChartRepresentation chart = new ChartRepresentation(dataset);
        chart.createFile();
    }
}