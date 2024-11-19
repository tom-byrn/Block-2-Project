package calculations;

public class CalculationsManager {

    public static void start(){
        System.out.println("Calculation function chosen");

        //Create calculations object
        Calculations calculations = new Calculations();
        calculations.promptForCalculation();
    }
    
}
