package ru.nsu.mbogdanov2.speed;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Class to create picture with performance.
 */
public class ChartRepresentation {
    private final JFreeChart chart;

    public ChartRepresentation(XYSeriesCollection dataset) {
        chart = createChart(dataset);
    }

    /**
     * We just create image of performance tests.
     */
    public void createFile() throws IOException {
        File file = new File("performanceTest.jpg");
        if (file.exists()) {
            file.delete();
        }
        ChartUtils.saveChartAsJPEG(file, chart, 1980, 1980);
    }

    private JFreeChart createChart(XYSeriesCollection dataset) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Performance comparison of prime number search algorithms",
                "Array size",
                "Time",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );
        chart.setBackgroundPaint(Color.white);
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(232, 232, 232));
        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        ValueAxis axis = plot.getDomainAxis();
        axis.setAxisLineVisible(false);
        return chart;
    }
}
