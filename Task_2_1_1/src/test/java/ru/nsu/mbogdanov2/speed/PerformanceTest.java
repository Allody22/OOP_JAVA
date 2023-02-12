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
 * We name a lot of arrays of different length.
 * And fill this arrays with only 1 not prime number.
 */
public class PerformanceTest {
    private int[] createArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        int[] array = new int[size];
        Arrays.fill(array, 0, size - 1, 282589933);
        array[size - 1] = 187263196;
        return array;
    }

    private long singleTest(PrimeSearch notPrimeSearch, int[] array) throws ExecutionException,
            InterruptedException {
        List<Long> results = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            long time = System.nanoTime();
            notPrimeSearch.search(array);
            time = System.nanoTime() - time;
            results.add(time);
        }
        return Collections.min(results);
    }

    @Test
    public void performanceTest() throws ExecutionException, InterruptedException, IOException {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Casual Prime Search");
        XYSeries series2 = new XYSeries("Prime Search with Threads");
        XYSeries series3 = new XYSeries("Prime Search with Streams");

        for (int size = 10; size <= 1000000; size *= 10) {
            int[] array = createArray(size);
            long time = singleTest(new PrimeSearch(), array);
            series1.add(size, time);
            time = singleTest(new PrimeSearchWithThread(), array);
            series2.add(size, time);
            time = singleTest(new PrimeSearchWithStreams(), array);
            series3.add(size, time);
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        LineGraph chart = new LineGraph(dataset);
        chart.createFile();
    }
}
