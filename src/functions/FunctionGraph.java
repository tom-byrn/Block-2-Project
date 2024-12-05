package functions;

import calculations.Calculations;
import calculations.CalculationsProcessor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FunctionGraph extends Functions {

    static Calculations calculations = new Calculations();

    public FunctionGraph(String function){

    }

    public FunctionGraph(){}

    public void graphFunction(){

        Functions f = new Functions();
        String functionInput = f.promptFunctionInput();
        double startRange = f.promptStartRange();

        do {
            double endRange = f.promptEndRange();
            if(endRange < startRange){
                System.out.println("End range value must be greater than start range value!");
            }
        }
        while(endRange < startRange);

        //Calculate a step size
        if((f.getStartRange() < 0 && f.getEndRange() <= 0) || (f.getStartRange() >= 0 && f.getEndRange() > 0)){
            f.setStepSize(Math.abs((f.getStartRange() + f.getEndRange()) * 0.01 ));
        }
        else{
            f.setStepSize(Math.abs((f.getEndRange() - f.getStartRange()) * 0.01));
        }

        f = new Functions(f.getFunctionInput(), f.getStartRange(), f.getEndRange(), f.getStepSize());
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


            // Add * between a number and a letter
            functionInput = functionInput.replaceAll("(\\d)([a-zA-Z])", "$1*$2");


            //Regex to replace certain Strings with constants e.g. g=9.81
            CalculationsProcessor processor = new CalculationsProcessor(functionInput);
            String function = processor.toString();
            function = function.replaceAll("\\bx\\b", String.valueOf(x));
            double y = calculations.evaluate(function); // Replace with your actual evaluation logic
            series.add(x, y);
        }

        // Create dataset and chart
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph of f(x) = " + f.getFunctionInput(),       // Chart title
                "",                   // X-axis label
                "",                   // Y-axis label
                dataset                // Dataset
        );
        applyDarkTheme(chart);
        return new ChartPanel(chart);
    }

    private void displayGraph(JPanel graphPanel) {
        JFrame frame = new JFrame("Function Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(graphPanel); // Add the graph panel to the frame

        URL resource = getClass().getResource("/GraphIcon.png");
        System.out.println("Resource URL: " + resource);
        ImageIcon icon = new ImageIcon(resource);
        frame.setIconImage(icon.getImage()); //Setting the window icon

        frame.setVisible(true); // Make the frame visible
    }


    //Setting a theme for the graphs, this could be edited via settings file
    private static void applyDarkTheme(JFreeChart chart) {
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
