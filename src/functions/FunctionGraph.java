package functions;

import calculations.Calculations;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class FunctionGraph extends Functions {

    static Calculations calculations = new Calculations();

    public FunctionGraph(String function){

    }

    public FunctionGraph(){}

    public void graphFunction(){

        Functions f = new Functions();
        String functionInput = f.promptFunctionInput();
        double startRange = f.promptStartRange();
        double endRange = f.promptEndRange();
        double stepSize = f.promptValidDouble("Enter the step size: ");

        displayGraph(setGraph(f));
    }

    private static JPanel setGraph(Functions f) {
        String functionInput = f.getFunctionInput();
        double startRange = f.getStartRange();
        double endRange = f.getEndRange();
        double stepSize = f.getStepSize();

        // Create a series for the chart
        XYSeries series = new XYSeries(functionInput);

        // Loop through the range and calculate values
        for (double x = startRange; x <= endRange; x += stepSize) {
            String function = functionInput.replaceAll("\\bx\\b", "(" + x + ")");
            double y = calculations.evaluate(function); // Replace with your actual evaluation logic
            series.add(x, y);
        }

        // Create dataset and chart
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph of f(x)",       // Chart title
                "X",                   // X-axis label
                "Y",                   // Y-axis label
                dataset                // Dataset
        );
        return new ChartPanel(chart);
    }

    private void displayGraph(JPanel graphPanel) {
        JFrame frame = new JFrame("Function Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(graphPanel); // Add the graph panel to the frame
        frame.setVisible(true); // Make the frame visible
    }

}
