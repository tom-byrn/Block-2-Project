package functions;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.RectangleInsets;

import java.awt.*;

public class GraphTheme {

    //Setting a theme for the graphs, this could be edited via settings file
    protected static void applyDarkTheme(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();

        // Set the plot background to dark
        plot.setBackgroundPaint(Color.BLACK);
        chart.getLegend().setBackgroundPaint(Color.DARK_GRAY);

        // Set grid line colours
        Color transparentGray = new Color(128, 128, 128, 100);
        plot.setDomainGridlinePaint(transparentGray);
        plot.setRangeGridlinePaint(transparentGray);

        // Set gridlines to solid
        BasicStroke solidStroke = new BasicStroke(1.0f); // Thickness of 1.0f
        plot.setDomainGridlineStroke(solidStroke);
        plot.setRangeGridlineStroke(solidStroke);

        // Set the chart background color
        chart.setBackgroundPaint(Color.DARK_GRAY);

        // Customize the title color and font
        chart.getTitle().setPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));

        // Customize axis labels and ticks
        plot.getDomainAxis().setLabelPaint(Color.WHITE);
        plot.getDomainAxis().setTickLabelPaint(Color.WHITE);
        plot.getRangeAxis().setLabelPaint(Color.WHITE);
        plot.getRangeAxis().setTickLabelPaint(Color.WHITE);

        // Customize legend
        chart.getLegend().setBackgroundPaint(null); // Remove the background
        chart.getLegend().setItemPaint(Color.WHITE); // Set text color to white

        // Remove padding between plot and chart edges for a cleaner look
        plot.setInsets(new RectangleInsets(10, 10, 10, 10));
    }

}
