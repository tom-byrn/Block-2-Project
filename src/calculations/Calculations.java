package calculations;

import java.util.*;

public class Calculations {

    protected String calculationInput;
    protected double answer;

    public Calculations(String calculationInput){
        this.calculationInput = calculationInput;
    }

    public Calculations(){}

    protected String getCalculationInput(){
        return calculationInput;
    }

    // Method to prompt the user for a calculation and store the input
    public void promptForCalculation() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Please enter a calculation:");
        calculationInput = scanner.nextLine(); // Store the user input
        System.out.println("You entered: " + calculationInput); // Echo the input back to the user
        evaluate(calculationInput);
    }

    protected double evaluate(String calculationInput){
        this.calculationInput = calculationInput;

        // Shunting Yard Algorithm implementation (https://brilliant.org/wiki/shunting-yard-algorithm/)
        System.out.println("Running evaluation method");

        //Tokenize the expression
        List<String> tokens = tokenize(calculationInput);
        System.out.println("Tokens: " + tokens.toString());

        return answer;
    }

    private List<String> tokenize(String calculationInput){

        this.calculationInput = calculationInput;
        List<String> tokens = new ArrayList<>(); //initialising tokens list for return statement
        StringBuilder currentToken = new StringBuilder(); //initialising string builder to store information

        for(int i = 0; i > calculationInput.length(); i++){
            char ch = calculationInput.charAt(i);

            //Adding all characters to the currentToken list
            if(Character.isWhitespace(ch)){
                continue; //ignore white space
            }
            else if(Character.isDigit(ch) || ch == '.'){
                currentToken.append(ch); //if digit or decimal, add to the current token
            }
            else if(Character.isLetter(ch)){
                currentToken.append(ch);
            }
            else {
                if(currentToken.length() > 0){ //if an operator or parenthesis
                    tokens.add(Character.toString(ch));
                    currentToken.setLength(0); //reset the current token list
                }
                tokens.add(Character.toString(ch)); //add operator / parentheses
            }
            //Add the last token (if there is any)
            if(currentToken.length() > 0){
                tokens.add(currentToken.toString());
            }
        }

        return tokens;
    }

    //Convert tokens to Reverse Polish Notation (This is the notation needed for the shunting yard algorithm to work)
    //https://brilliant.org/wiki/shunting-yard-algorithm/#reverse-polish
    private List<String> toRPN(List<String> tokens){
        List<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        return null;
    }

}
