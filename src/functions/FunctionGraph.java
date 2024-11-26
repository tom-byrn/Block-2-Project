package functions;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

import static functions.Functions.singleVariableFunction;

public class FunctionGraph extends Functions {

    public FunctionGraph(String function){

    }

    public FunctionGraph(){}

    public static void graphFunction(){

        Functions f = new Functions();

        String functionInput = f.promptFunctionInput();
        double startRange = f.promptStartRange();
        double endRange = f.promptEndRange();

        f = new Functions(functionInput, startRange, endRange);

    }

}
