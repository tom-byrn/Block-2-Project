package functions;

import calculations.Calculations;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

import static functions.Functions.singleVariableFunction;

public class FunctionGraph extends Functions {

    static Calculations calculations = new Calculations();

    public FunctionGraph(String function){

    }

    public FunctionGraph(){}

    public static void graphFunction(){

        Functions f = new Functions();
        String functionInput = f.promptFunctionInput();
        double startRange = f.promptStartRange();
        double endRange = f.promptEndRange();
        double stepSize = f.promptStepSize();
        f = new Functions(functionInput, startRange, endRange, stepSize);

        XYSeries series = new XYSeries(functionInput);

        for (double x = startRange; x <= endRange; x += stepSize) {
            String function = functionInput.replaceAll("\\bx\\b", "(" + x + ")");
            double y = calculations.evaluate(function);
            series.add(x, y);
        }

    }

}
