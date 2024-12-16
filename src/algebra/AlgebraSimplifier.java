package algebra;

import java.util.*;
        import java.util.regex.*;

public class AlgebraSimplifier {

    public static void main(String[] args) {
        // Create a scanner to take user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for an algebraic expression
        System.out.println("Enter an algebraic expression to simplify:");

        // Read the algebraic expression entered by the user
        String expression = scanner.nextLine();

        // Simplify the input expression using the simplifyExpression method
        String simplifiedExpression = simplifyExpression(expression);

        // Output the simplified expression
        System.out.println("Simplified expression: " + simplifiedExpression);
    }


    //Simplifies the algebraic expression by combining like terms and handling basic arithmetic operations.

    public static String simplifyExpression(String expression) {

        // Step 1: Preprocess the expression to ensure multiplication operators are inserted where missing
        expression = preprocessMissingOperators(expression);

        // Step 2: Handle multiplication and division operations in the expression
        expression = handleMultiplicationAndDivision(expression);

        // Step 3: Split the expression into individual terms based on '+' or '-' signs
        // This uses a lookahead regex to ensure the '+' or '-' stays with the term it precedes
        String[] terms = expression.split("(?<!\\^)(?=[+-])");

        // A map to store the coefficients of terms by variable and exponent (e.g., "x^2", "y^3")
        Map<String, Double> termMap = new HashMap<>();

        // Step 4: Process each term to extract coefficients and variables
        for (String term : terms) {

            // Use regex to extract the coefficient, variable part, and exponent of terms like "2.5x^2"
            Matcher matcher = Pattern.compile("([+-]?\\d*\\.?\\d*)([a-zA-Z]+)(\\^[-+]?\\d+)?").matcher(term);

            if (matcher.matches()) {
                // This term contains a variable and potentially an exponent, so we process it
                String coefficientStr = matcher.group(1);  // Get the coefficient part (e.g., "2.5")
                String variable = matcher.group(2);        // Get the variable part (e.g., "x")
                String exponentStr = matcher.group(3);     // Get the exponent part (e.g., "^2")

                // Step 5: Default the coefficient to 1 if it is missing or just "+" or "-"
                double coefficient = coefficientStr.isEmpty() || coefficientStr.equals("+") || coefficientStr.equals("-") ?
                        (coefficientStr.equals("-") ? -1 : 1) : Double.parseDouble(coefficientStr);

                // Step 6: Handle exponent, default to 1 if not provided
                int exponent = (exponentStr == null || exponentStr.isEmpty()) ? 1 : Integer.parseInt(exponentStr.substring(1)); // Remove the '^' symbol

                // Step 7: Create a unique key using the variable and exponent (e.g., "x^2")
                String key = variable + "^" + exponent;

                // Step 8: Combine terms with the same variable and exponent by adding their coefficients
                termMap.put(key, termMap.getOrDefault(key, 0.0) + coefficient);
            } else {
                // Step 9: If the term doesn't have a variable, it's a constant number (e.g., "5")
                try {
                    double constant = Double.parseDouble(term);
                    termMap.put("constant", termMap.getOrDefault("constant", 0.0) + constant);
                } catch (NumberFormatException e) {
                    // If the term is not a valid number or term, print an error and return an empty string
                    System.out.println("Invalid term: " + term);
                    return "";
                }
            }
        }

        // Step 10: Rebuild the simplified expression from the termMap
        StringBuilder result = new StringBuilder();

        // Step 11: Add the constant term if present
        double constant = termMap.getOrDefault("constant", 0.0);
        if (constant != 0) {
            result.append(constant > 0 && !result.isEmpty() ? "+" : "").append(constant);
        }

        // Step 12: Add terms with variables and exponents, sorted by variable and exponent
        termMap.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("constant"))  // Exclude the constant from this part
                .sorted(Map.Entry.comparingByKey())  // Sort terms by their variable and exponent
                .forEach(entry -> {
                    double coef = entry.getValue();
                    if (coef != 0) {
                        // If the coefficient is positive and not the first term, add a '+' sign
                        result.append(coef > 0 && !result.isEmpty() ? "+" : "")
                                .append(coef == 1 ? "" : (coef == -1 ? "-" : coef))  // Handle coefficient formatting
                                .append(entry.getKey());  // Append the variable and exponent (e.g., "x^2")
                    }
                });

        // Step 13: Return the final simplified expression or "0" if there are no terms
        return !result.isEmpty() ? result.toString() : "0";
    }


    //Handles multiplication and division in the expression by calling separate methods for each.
    public static String handleMultiplicationAndDivision(String expression) {
        // Step 1: First handle division (e.g., 6x^2 / 2y => 6x^2 * 0.5y^-2)
        expression = divideTerms(expression);

        // Step 2: Remove all spaces in the expression to make parsing easier
        expression = expression.replaceAll("\\s+", "");
        // Step 3: Then handle multiplication (e.g., 3x^2 * 4x => 12x^3)
        expression = multiplyTerms(expression);
        return expression;
    }


    // This method processes the entire mathematical expression, identifying and handling multiplication and division
    private static String multiplyTerms(String expression) {
        // This regular expression splits the expression into individual terms while keeping the operators ('+' or '-')
        String[] terms = expression.split("(?=(?<!\\^)\\+|(?<!\\^)-)");


        // StringBuilder to accumulate the resulting expression after processing multiplication terms
        StringBuilder resultExpression = new StringBuilder();

        // Iterate through each term in the expression
        for (String term : terms) {
            term = term.trim();  // Remove any leading or trailing whitespace for cleaner processing
            System.out.println(Arrays.toString(terms));
            // Check if the term contains multiplication ('*')
            if (term.contains("*")) {
                resultExpression.append(processMultiplication(term));  // Process the multiplication for this term
            } else {
                // If there's no multiplication, append the term as-is (with the sign if present)
                resultExpression.append(term);
            }
        }

        // Return the final expression after processing all terms and handling multiplication
        return resultExpression.toString();
    }

    // This method processes multiplication of terms, combining like terms and handling coefficients and exponents
    private static String processMultiplication(String term) {
        // Regex pattern to match terms like '6x', '4y', '2x^2', '3x^3', etc.
        // The pattern captures three parts:
        // 1. The coefficient (optional) - could be a number or empty (default to 1 if not present)
        // 2. The variable(s) - could be one or more variables (e.g., 'x', 'xy', 'xyz')
        // 3. The exponent (optional) - could be a power (e.g., '^2', '^3') or absent (default to exponent 1)
        Pattern multiplicationPattern = Pattern.compile("([+-]?\\d*\\.?\\d*)([a-zA-Z]+)(\\^[-+]?\\d*)?");
        Matcher matcher = multiplicationPattern.matcher(term);  // Matcher to apply the regex to the current term

        // Map to store the accumulated exponents of each variable (e.g., 'x^3', 'y^2')
        Map<String, Integer> variableExponentMap = new HashMap<>();
        double totalCoefficient = 1;  // Initialize the overall coefficient to 1 (to be multiplied)

        // Process each match found by the regex in the term (there could be multiple parts to multiply)
        while (matcher.find()) {
            // Parse the coefficient (if not found, default to 1)
            double coefficient = matcher.group(1).isEmpty() ? 1 : Double.parseDouble(matcher.group(1));

            // Extract the variables (e.g., 'x', 'y', 'xy') from the term
            String variables = matcher.group(2);

            // Parse the exponent (if not found, default to 1)
            int exponent = matcher.group(3) == null ? 1 : Integer.parseInt(matcher.group(3).substring(1));

            // Multiply the coefficient of this match with the overall coefficient so far
            totalCoefficient *= coefficient;

            // Process each individual variable (such as 'x', 'y', 'z' in the term)
            for (char variable : variables.toCharArray()) {
                String variableStr = String.valueOf(variable);  // Convert the character to a string
                // Update the exponent for this variable by adding the current exponent
                // If the variable already exists in the map, its exponent is increased, otherwise it's set to the current exponent
                variableExponentMap.put(variableStr, variableExponentMap.getOrDefault(variableStr, 0) + exponent);
            }
        }

        // StringBuilder to build the resulting expression after multiplication
        StringBuilder resultTerm = new StringBuilder();

        // If the total coefficient is not 1, append it to the result
        if (totalCoefficient != 1) {
            resultTerm.append(totalCoefficient);  // Add the coefficient to the result term
        }

        // Create a list of variables and their exponents so they can be sorted and displayed
        List<Map.Entry<String, Integer>> sortedVariables = new ArrayList<>(variableExponentMap.entrySet());

        // Sort the list of variables:
        // 1. First by exponent in ascending order (lower exponents come first)
        // 2. If exponents are equal, sort alphabetically by the variable
        sortedVariables.sort((entry1, entry2) -> {
            int exponentComparison = Integer.compare(entry1.getValue(), entry2.getValue());  // Compare exponents in ascending order
            if (exponentComparison == 0) {
                return entry1.getKey().compareTo(entry2.getKey());  // If exponents are equal, sort alphabetically by variable
            }
            return exponentComparison;  // Return the result of exponent comparison
        });

        // Append each sorted variable and its exponent to the result term
        for (Map.Entry<String, Integer> entry : sortedVariables) {
            String variable = entry.getKey();  // Get the variable (e.g., 'x', 'y')
            int exponent = entry.getValue();  // Get the exponent for this variable

            resultTerm.append(variable);  // Append the variable itself

            // If the exponent is not 1, append the exponent (e.g., 'x^2' instead of 'x')
            if (exponent != 1) {
                resultTerm.append("^").append(exponent);  // Append the exponent in the format "^n"
            }
        }

        // Return the final processed term after handling multiplication
        return resultTerm.toString();
    }

    //Divides terms in the expression and returns the updated expression.
    public static String divideTerms(String inputExpression) {
        // Step 1: Handle division operations (adjust numbers or variables)
        inputExpression = handleDivision(inputExpression);

        // Step 2: Handle exponentiation (reverse the sign of exponents)
        inputExpression = handlePowers(inputExpression);

        return inputExpression;
    }

    // Handles division and adjusts the expression accordingly (e.g., "a / b" becomes "a * (1/b)")
    private static String handleDivision(String inputExpression) {
        // Regular expression to find division patterns, e.g., "9 / 4" or "3 / 5b"
        Pattern divisionPattern = Pattern.compile("(\\S+) / (\\S+)");  // Matches non-whitespace sequences around "/"
        Matcher divisionMatcher = divisionPattern.matcher(inputExpression);

        // StringBuffer to build the result string as we modify the expression
        StringBuilder transformedExpression = new StringBuilder();

        // Iterate through all matches of the division pattern
        while (divisionMatcher.find()) {
            String numerator = divisionMatcher.group(1);    // The part before the division (numerator)
            String denominator = divisionMatcher.group(2);  // The part after the division (denominator)

            // Case 1: Denominator is a single number (e.g., "9 / 5")
            if (denominator.matches("\\d+")) {

                if(Double.parseDouble(denominator)== 0){
                    throw new ArithmeticException("Error Divide by zero");
                }
                double reciprocal = 1.0 / Double.parseDouble(denominator);  // Calculate the reciprocal of the denominator

                divisionMatcher.appendReplacement(transformedExpression, numerator + " * " + reciprocal);

            }
            // Case 2: Denominator contains single or multiple alphabetic characters (no powers and optional constant), (e.g., "9 / mcd") -> (9 * m^1c^1d^1)
            else if (denominator.matches("\\d*[a-zA-Z]+")) {
                double reciprocal = getReciprocal(denominator);

                String variablePart = denominator.replaceAll("\\d", "");      // Extract variable part (e.g., "b" from "5b")

                StringBuilder transformedDenominator = new StringBuilder();

                // Append "^1" for each letter in the denominator
                for (char variable : variablePart.toCharArray()) {
                    transformedDenominator.append(variable).append("^1");
                }

                divisionMatcher.appendReplacement(transformedExpression, numerator + " * " + reciprocal + transformedDenominator);
            } else if (denominator.matches("(\\d*[a-zA-Z](\\^([+-]?\\d+))?)+")) {
                double reciprocal = getReciprocal(denominator);


                String variablePart = transformString(denominator);

                divisionMatcher.appendReplacement(transformedExpression, numerator + " * " + reciprocal + variablePart);
            }
            // Case 3: Denominator is a number followed by a variable with a power (e.g., "5x^4")
            else {
                divisionMatcher.appendReplacement(transformedExpression, numerator + " * " + "1/" + denominator);
            }
        }
        // Append the remaining part of the expression that wasn't matched
        divisionMatcher.appendTail(transformedExpression);

        return transformedExpression.toString();
    }

    private static double getReciprocal(String denominator) {
        double numberPart = 1;
        String numberPartString = denominator.replaceAll("[a-zA-Z]", "").replaceAll("\\^[+-]?\\d*","");
        try { // tries to find a number if not it ignores the exception
            numberPart = Double.parseDouble(numberPartString);  // Extract numeric part (e.g., "5" from "5b")
        }
        catch(Exception ignored){}

        if(numberPart == 0.0){
            throw new ArithmeticException("Error Divide by zero");
        }
        // Calculate the reciprocal of the numeric part
        return 1.0 / numberPart;
    }

    // Handles powers and reverses the sign of exponents (e.g., "x^4" becomes "x^-4")
    private static String handlePowers(String inputExpression) {
        // Regular expression to find exponentiation patterns, e.g., "x^4" or "z^-1"
        Pattern powerPattern = Pattern.compile("(\\w+)\\^(-?\\d+)");  // Matches variables with exponents (e.g., "x^4")
        Matcher powerMatcher = powerPattern.matcher(inputExpression);

        // StringBuffer to build the result string as we modify the expression
        StringBuilder transformedExpression = new StringBuilder();

        // Iterate through all matches of the exponentiation pattern
        while (powerMatcher.find()) {
            String variable = powerMatcher.group(1);  // The variable (e.g., "x")
            int exponent = Integer.parseInt(powerMatcher.group(2));  // The exponent (e.g., 4)

            // Reverse the sign of the exponent (e.g., "x^4" becomes "x^-4")
            powerMatcher.appendReplacement(transformedExpression, variable + "^" + (-exponent));
        }

        // Append the remaining part of the expression that wasn't matched
        powerMatcher.appendTail(transformedExpression);

        return transformedExpression.toString();
    }


    // This method transforms the input string by adding "^1" to letters that are not followed by a '^'.
    // If the letter is followed by '^' (like x^3), it remains unchanged.
    public static String transformString(String input) {

        // Check if the first character is a digit
        input = input.matches("^\\d.*") // If the string starts with a digit
                ? input.substring(1)     // Remove the first character (if it's a number)
                : input;                 // Otherwise, leave the string unchanged

        // Use StringBuilder to construct the transformed string efficiently
        StringBuilder transformedStringBuilder = new StringBuilder();

        // Iterate through each character in the input string
        for (int currentIndex = 0; currentIndex < input.length(); currentIndex++) {
            // Get the current character in the string
            char currentCharacter = input.charAt(currentIndex);

            // Check if the current character is a letter (either uppercase or lowercase)
            if (isLetter(currentCharacter)) {
                // Append the letter to the result
                transformedStringBuilder.append(currentCharacter);

                // Check if the next character is not '^' (if it's not an exponent format)
                if (currentIndex + 1 >= input.length() || input.charAt(currentIndex + 1) != '^') {
                    // If not followed by '^', append "^1" after the letter
                    transformedStringBuilder.append("^1");
                }
            } else {
                // If it's not a letter (i.e., a number, symbol, etc.), just append it to the result
                transformedStringBuilder.append(currentCharacter);
            }
        }

        // Return the final transformed string
        return transformedStringBuilder.toString();
    }

    // This helper method checks if a character is a letter (either uppercase or lowercase)
    public static boolean isLetter(char character) {
        // Check if the character is in the range 'a' to 'z' or 'A' to 'Z'
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z');
    }


    //Ensures multiplication operators are inserted where missing in the expression.
    //For example, "3x" becomes "3*x", "x2" becomes "x*2", and "x" becomes "1x".
    private static String preprocessMissingOperators(String expression) {
        // Add a multiplication operator between numbers and variables, e.g., "3*x" becomes "3x"
        expression = expression.replaceAll("([+-]?\\d*\\.?\\d)*([a-zA-Z])", "$1$2");

        // Add a multiplication operator between variables and numbers, e.g., "x2" becomes "x*2"
        expression = expression.replaceAll("([a-zA-Z])([+-]?\\d*\\.?\\d)", "$1*$2");
        return expression;
    }
}
