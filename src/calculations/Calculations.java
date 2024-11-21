package calculations;

import java.math.BigDecimal;
import java.util.*;

public class Calculations {

    protected String calculationInput;
    protected BigDecimal answer;
    protected String originalInput;


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

        System.out.printf("Please enter a sum: ");
        calculationInput = scanner.nextLine(); // Store the user input
        originalInput = calculationInput; //Just saving the original input to output to terminal later as it looks cleaner than the version with the regex

        calculationInput = preprocessInput(calculationInput); //Regex for calculation input

        BigDecimal result = evaluate(calculationInput); //Run the evaluate method with the input given by the user
    }

    // Method to preprocess the calculation input with regex
    private String preprocessInput(String input) {
        //Remove all whitespace
        input = input.replaceAll("\\s+", "");
        // Add * between a number and a letter
        input = input.replaceAll("(\\d)([a-zA-Z])", "$1*$2");
        // Replace ")(" with ")*("
        input = input.replaceAll("\\)\\(", ")*(");
        //Replace "- -" with "+" and "- ( -" with "+ ("
        input = input.replaceAll("--", "+");
        //Replace -(- with +(
        input = input.replaceAll("-\\(-", "+(");
        // Replacing factorial with factorial number needed for the 2 argument constructor
        input = input.replaceAll("!", "!0");

        // Constants
        // Replace e with Euler constant
        input = input.replaceAll("\\be\\b", String.valueOf(Math.E));

        // Replace k with Boltzmann constant
        input = input.replaceAll("\\bk\\b", String.valueOf(1.3806505/ Math.pow(10, 23)));

        // Replace F with the Faraday constant
        input = input.replaceAll("\\bF\\b", String.valueOf(96485.3383));

        // Replace eV with the electron volt
        input = input.replaceAll("\\beV\\b", String.valueOf(1.60217653/ Math.pow(10, 19)));

        // Replace G with the gravitational constant
        input = input.replaceAll("\\bG\\b", String.valueOf(6.6742/ Math.pow(10, 11)));

        // Replace pi with string conversion of Math.PI
        input = input.replaceAll("\\bpi\\b", String.valueOf(Math.PI));

        // Replace g with gravity
        input = input.replaceAll("\\bg\\b", String.valueOf(9.81));

        // Replace h with Planck's Constant
        input = input.replaceAll("\\bh\\b", String.valueOf(6.62607015/ Math.pow(10,34)));

        // Replace c with the speed of light in vacuo
        input = input.replaceAll("\\bc\\b", String.valueOf(2.99792458 * Math.pow(10, 8)));

        // Replace R with Universal Gas Constant
        input = input.replaceAll("\\bR\\b", String.valueOf(8.314472));

        // Replace u with Unified Atomic Mass Unit
        input = input.replaceAll("\\bu\\b", String.valueOf(1.6605402/ Math.pow(10, 27)));

        //More stuff other than e & pi can be added if needed

        return input;
    }

    protected BigDecimal evaluate(String calculationInput){
        this.calculationInput = calculationInput;

        // Shunting Yard Algorithm implementation (https://brilliant.org/wiki/shunting-yard-algorithm/)

        //Tokenize the expression
        List<String> tokens = tokenize(calculationInput);

        //Convert to RPN
        List<String> rpn = toRPN(tokens);

        //Evaluate the expression in RPN
        answer = BigDecimal.valueOf(evaluateRPN(rpn));

        return answer;
    }

    private List<String> tokenize(String calculationInput){

        this.calculationInput = calculationInput;
        List<String> tokens = new ArrayList<>(); //initialising tokens list for return statement
        StringBuilder currentToken = new StringBuilder(); //initialising string builder to store information

        for(int i = 0; i < calculationInput.length(); i++){ // Corrected loop condition
            char ch = calculationInput.charAt(i);

            //Adding all characters to the currentToken list
            if(Character.isWhitespace(ch)){
                continue; //ignore the white space if there is any
            }
            else if(Character.isDigit(ch) || ch == '.'){
                currentToken.append(ch); //if digit or decimal, add to the current token
            }
            else if(Character.isLetter(ch)){
                currentToken.append(ch); //add letters to the current token
            }
            else {
                if(currentToken.length() > 0){ //if an operator or parenthesis
                    tokens.add(currentToken.toString()); // Corrected to add the current token
                    currentToken.setLength(0); //reset the current token list
                }
                tokens.add(Character.toString(ch)); //add operator / parentheses
            }
        }
        //Add the last token (if there is any)
        if(currentToken.length() > 0){
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    //Convert tokens to Reverse Polish Notation (This is the notation needed for the shunting yard algorithm to work)
    //https://brilliant.org/wiki/shunting-yard-algorithm/#reverse-polish

    private List<String> toRPN(List<String> tokens){
        List<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        Map<String, Integer> precedence = Map.of( //Setting levels of precendence to operators
                "+", 1, "-", 1,
                "*", 2, "/", 2,
                "^", 3, "%",3,"!",4);

        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token);
            } else if (isFunction(token)) {
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                operators.pop(); // Remove "("
                if (!operators.isEmpty() && isFunction(operators.peek())) {
                    output.add(operators.pop()); // Add function
                }
            } else { // Operator
                while (!operators.isEmpty() && precedence.getOrDefault(operators.peek(), 0) >= precedence.getOrDefault(token, 0)) {
                    output.add(operators.pop());
                }
                operators.push(token);
            }
        }
        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }
        return output;
    }

    //Evaluate the RPN expression
    private double evaluateRPN(List<String> rpn){
        Stack<Double> stack = new Stack<>();

        for(String token: rpn){
            if(isNumber(token)) {
                stack.push(Double.parseDouble(token));//Converts the token string into a double if it's detected that it is a number
            } else if(isFunction(token)) { //Apply function to if a function token is detected
                if (stack.isEmpty()) {
                    throw new IllegalStateException("Invalid RPN expression. Not enough values for function: " + token);
                }
                double a = stack.pop();
                stack.push(applyFunction(token, a));
            } else { //if it's not a number or a function, it is going to be an operator
                if (stack.size() < 2) {
                    throw new IllegalStateException("Invalid RPN expression. Not enough values for operator: " + token);
                }
                double b = stack.pop();
                double a = stack.pop();
                stack.push(applyOperator(a, b, token)); //Apply one of the operators to a & b
            }
        }
        if (stack.size() != 1) {
            throw new IllegalStateException("Invalid RPN expression. Stack size should be 1 but is: " + stack.size());
        }
        return stack.pop();
    }

    private boolean isNumber(String token){
        try {
            Double.parseDouble(token); //Tests if the string can be converted to a double
            return true;
        } catch (NumberFormatException e) {
            return false; //Returns false if an error occurs trying to convert to a double i.e. not a number
        }
    }

    private boolean isFunction(String token){
        return token.equals("sin") || token.equals("cos") || token.equals("log")
                || token.equals("ln") || token.equals("sqrt") || token.equals("tan")
                || token.equals("sinh") || token.equals("cosh") || token.equals("tanh"); //As many functions can be added to this as needed
        //I intend to edit the log function later so that any type of log can be used
    }


    private double applyOperator(double a, double b, String operator) {
        return switch (operator) { //Returns one of the options depending on the case
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) { //Error handling for divide by 0

                    throw new ArithmeticException("Division by zero is not allowed!");
                }
                yield a / b;
            }
            case "%" -> a%b;
            case "^" -> Math.pow(a, b);
            case "!" ->{
                double factorialA;
                // checks if a is zero
                if(a == 0){
                    yield 1;

                    //checks if a is a decimail
                } else if (a%1==0) {
                    factorialA = a;

                    // for loop to create X!
                    for (int factorialCounter = 1; factorialCounter < a; factorialCounter++) {
                        factorialA *= factorialCounter;
                    }
                    yield factorialA; //returns a!
                }else {
                    throw new IllegalArgumentException("Can't get the factorial of a decimal " + operator);
                }
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }


    private double applyFunction(String function, double a) {
        return switch (function) {
            case "sin" -> Math.sin(Math.toRadians(a));
            case "cos" -> Math.cos(Math.toRadians(a));
            case "tan" -> Math.tan(Math.toRadians(a));
            case "sinh" -> Math.sinh(Math.toRadians(a));
            case "cosh" -> Math.cosh(Math.toRadians(a));
            case "tanh" -> Math.tanh(Math.toRadians(a));
            case "log" -> Math.log10(a);
            case "sqrt" -> Math.sqrt(a);
            case "ln" -> Math.log(a);
            //Room to add more functions here if needed
            default -> throw new IllegalArgumentException("Unknown function: " + function);
        };
    }

    public String getInput(){
        return calculationInput;
    }

    public BigDecimal getAnswer(){
        return answer;
    }

    public String getOriginalInput(){
        return originalInput;
    }

}
