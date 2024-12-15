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
        // Step 1: Remove all spaces in the expression to make parsing easier
        expression = expression.replaceAll("\\s+", "");

        // Step 2: Preprocess the expression to ensure multiplication operators are inserted where missing
        expression = preprocessMissingOperators(expression);

        // Step 3: Handle multiplication and division operations in the expression
        expression = handleMultiplicationAndDivision(expression);

        // Step 4: Split the expression into individual terms based on '+' or '-' signs
        // This uses a lookahead regex to ensure the '+' or '-' stays with the term it precedes
        String[] terms = expression.split("(?<!\\^)(?=[+-])");

        // A map to store the coefficients of terms by variable and exponent (e.g., "x^2", "y^3")
        Map<String, Double> termMap = new HashMap<>();

        // Step 5: Process each term to extract coefficients and variables
        for (String term : terms) {

            // Use regex to extract the coefficient, variable part, and exponent of terms like "2.5x^2"
            Matcher matcher = Pattern.compile("([+-]?\\d*\\.?\\d*)([a-zA-Z]+)(\\^[-+]?\\d+)?").matcher(term);

            if (matcher.matches()) {
                // This term contains a variable and potentially an exponent, so we process it
                String coefficientStr = matcher.group(1);  // Get the coefficient part (e.g., "2.5")
                String variable = matcher.group(2);        // Get the variable part (e.g., "x")
                String exponentStr = matcher.group(3);     // Get the exponent part (e.g., "^2")

                // Step 6: Default the coefficient to 1 if it is missing or just "+" or "-"
                double coefficient = coefficientStr.isEmpty() || coefficientStr.equals("+") || coefficientStr.equals("-") ?
                        (coefficientStr.equals("-") ? -1 : 1) : Double.parseDouble(coefficientStr);

                // Step 7: Handle exponent, default to 1 if not provided
                int exponent = (exponentStr == null || exponentStr.isEmpty()) ? 1 : Integer.parseInt(exponentStr.substring(1)); // Remove the '^' symbol

                // Step 8: Create a unique key using the variable and exponent (e.g., "x^2")
                String key = variable + "^" + exponent;

                // Step 9: Combine terms with the same variable and exponent by adding their coefficients
                termMap.put(key, termMap.getOrDefault(key, 0.0) + coefficient);
            } else {
                // Step 10: If the term doesn't have a variable, it's a constant number (e.g., "5")
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

        // Step 11: Rebuild the simplified expression from the termMap
        StringBuilder result = new StringBuilder();

        // Step 12: Add the constant term if present
        double constant = termMap.getOrDefault("constant", 0.0);
        if (constant != 0) {
            result.append(constant > 0 && !result.isEmpty() ? "+" : "").append(constant);
        }

        // Step 13: Add terms with variables and exponents, sorted by variable and exponent
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

        // Step 14: Return the final simplified expression or "0" if there are no terms
        return !result.isEmpty() ? result.toString() : "0";
    }


    //Handles multiplication and division in the expression by calling separate methods for each.


    public static String handleMultiplicationAndDivision(String expression) {
        // Step 1: First handle division (e.g., 6x^2 / 2y => 3x^2 / y)
        expression = divideTerms(expression);
        // Step 2: Then handle multiplication (e.g., 3x^2 * 4x => 12x^3)
        expression = multiplyTerms(expression);
        return expression;
    }


    // This method processes the entire mathematical expression, identifying and handling multiplication and division
    private static String multiplyTerms(String expression) {
        // This regular expression splits the expression into individual terms while keeping the operators ('+' or '-')
        String[] terms = expression.split("(?=[+/-])");

        // StringBuilder to accumulate the resulting expression after processing multiplication terms
        StringBuilder resultExpression = new StringBuilder();

        // Iterate through each term in the expression
        for (String term : terms) {
            term = term.trim();  // Remove any leading or trailing whitespace for cleaner processing

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
        // 1. First by exponent in descending order (higher exponents come first)
        // 2. If exponents are equal, sort alphabetically by the variable
        sortedVariables.sort((entry1, entry2) -> {
            int exponentComparison = Integer.compare(entry2.getValue(), entry1.getValue());  // Compare exponents in descending order
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
    private static String divideTerms(String expression) {
        // Regex pattern to match division between terms, e.g., 6x^2 / 3x or 4x^3 / 2x^2
        // Breakdown:
        // ([+-]?\\d*\\.?\\d*)  -> matches the coefficient (optional sign and decimal part)
        // ([a-zA-Z]*)          -> matches the variable part (like 'x', 'y', etc.)
        // (\\^[-+]?\\d*)?      -> matches the exponent part (optional, like '^2' or '^3')
        // /                    -> matches the division operator
        // This pattern captures both sides of the division, including coefficient, variable, and exponent parts
        Pattern dividePattern = Pattern.compile("([+-]?\\d*\\.?\\d*)([a-zA-Z]*)(\\^[-+]?\\d*)?/([+-]?\\d*\\.?\\d*)([a-zA-Z]*)(\\^[-+]?\\d*)?");
        Matcher matcher = dividePattern.matcher(expression); // Create a matcher object for the given expression

        // StringBuilder to store the result of the division
        StringBuilder result = new StringBuilder();

        // Loop through all matches found by the regex pattern
        while (matcher.find()) {
            // Extract the coefficient, variable, and exponent for both terms from the match groups
            // If no coefficient is provided, assume it to be 1 (e.g., in 'x^2' the coefficient is implicitly 1)
            double coef1 = matcher.group(1).isEmpty() ? 1 : Double.parseDouble(matcher.group(1)); // Coefficient of the first term
            double coef2 = matcher.group(4).isEmpty() ? 1 : Double.parseDouble(matcher.group(4)); // Coefficient of the second term

            String var1 = matcher.group(2);  // Variable part of the first term (e.g., 'x' in '6x')
            String var2 = matcher.group(5);  // Variable part of the second term (e.g., 'x' in '3x')

            // Exponent handling: If no exponent is given, assume it to be 1 (e.g., 'x' implies 'x^1')
            // If an exponent is given, we strip the '^' and convert it to an integer.
            int exp1 = matcher.group(3) == null || matcher.group(3).isEmpty() ? 1 : Integer.parseInt(matcher.group(3).substring(1));  // Exponent of the first term
            int exp2 = matcher.group(6) == null || matcher.group(6).isEmpty() ? 1 : Integer.parseInt(matcher.group(6).substring(1));  // Exponent of the second term

            // If a term has no variable part, treat it as an empty variable and set exponent to 0
            if (var1.isEmpty()) {
                var1 = "";
                exp1 = 0;
            }
            if (var2.isEmpty()) {
                var2 = "";
                exp2 = 0;
            }

            // Perform the division: divide coefficients and subtract exponents
            double newCoef = coef1 / coef2;  // Coefficients division
            int newExp = exp1 - exp2;        // Exponent subtraction

            // Replace the matched term with the result of the division
            if (newExp == 0) {
                // If the exponent becomes 0, the term simplifies to a constant (e.g., x^0 -> 1)
                // In this case, only append the new coefficient as a constant.
                matcher.appendReplacement(result, newCoef + "");
            } else {
                // If the exponent is not 0, the result will still have a variable part
                // Special handling:
                // - If the new exponent is 1, we just append the variable (no need to include ^1).
                // - If the exponent is -1, append "^-" for the inverse (e.g., x^-1).
                // - For any other exponent, append "^" followed by the exponent value.
                matcher.appendReplacement(result, newCoef + var1 + var2 + (newExp == 1 ? "" : "^" + newExp));
            }
        }

        // Append the remaining part of the expression after all matches have been processed
        matcher.appendTail(result);

        // Return the final result as a string
        return result.toString();
    }



    //Ensures multiplication operators are inserted where missing in the expression.
    //For example, "3x" becomes "3*x", "x2" becomes "x*2", and "x" becomes "1x".

    private static String preprocessMissingOperators(String expression) {
        // Add a multiplication operator between numbers and variables, e.g., "3x" becomes "3*x"
        expression = expression.replaceAll("(\\d)*([a-zA-Z])", "$1$2");

        // Add a multiplication operator between variables and numbers, e.g., "x2" becomes "x*2"
        expression = expression.replaceAll("([a-zA-Z])(\\d)", "$1*$2");
        return expression;
    }
}
