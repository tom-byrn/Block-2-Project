package algebra;

public class AlgebraProcessor extends processor.InputProcessor{

    private static String processedString;

    public AlgebraProcessor(String input) {
        super(input);
        input = getProcessedString();

        setProcessedString(input);


    }

    public static String getProcessedString() {
        return processedString;
    }

    public static void setProcessedString(String processedString) {
        AlgebraProcessor.processedString = processedString;
    }


    @Override
    public String toString() {
        return getProcessedString();
    }
}

