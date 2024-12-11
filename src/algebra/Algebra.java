package algebra;

//imports
import java.util.*;
import java.util.regex.*;

// Helper class to represent a single algebraic term.
class AlgebraicTerm {
    double termCoefficient;  // The coefficient of the term, e.g., 2.5 in 2.5x^2
    Map<String, Integer> variableExponents;  // The variable part of the term, e.g., {x=2} for x^2

    // Constructor to create a new term with a coefficient and a map of variables and their exponents
    AlgebraicTerm(double termCoefficient, Map<String, Integer> variableExponents) {
        this.termCoefficient = termCoefficient;  // Set the coefficient of the term
        this.variableExponents = variableExponents;  // Set the map of variables and their corresponding exponents
    }

    // Method to combine two terms if they have the same variable and exponent
    public void combineLikeTerm(AlgebraicTerm otherTerm) {
        // Check if both terms have the exact same variables and their exponents
        if (this.variableExponents.equals(otherTerm.variableExponents)) {
            // Combine the coefficients of like terms
            // For example: 3x^2y + 2x^2y becomes 5x^2y
            this.termCoefficient += otherTerm.termCoefficient;
        }
    }

    // Method to multiply two terms and return a new term with the resulting coefficient and exponents
    public static AlgebraicTerm multiplyTerms(AlgebraicTerm firstTerm, AlgebraicTerm secondTerm) {
        // Multiply the coefficients of both terms
        double newTermCoefficient = firstTerm.termCoefficient * secondTerm.termCoefficient;

        // Combine the variables from both terms and add their exponents
        Map<String, Integer> combinedVariableExponents = new HashMap<>(firstTerm.variableExponents); // Start with firstTerm's variables
        for (Map.Entry<String, Integer> variableEntry : secondTerm.variableExponents.entrySet()) {
            // Add the exponents for common variables or set it if it doesn't exist in firstTerm
            combinedVariableExponents.put(variableEntry.getKey(),
                    combinedVariableExponents.getOrDefault(variableEntry.getKey(), 0) + variableEntry.getValue());
        }

        // Return a new AlgebraicTerm with the updated coefficient and combined variables
        return new AlgebraicTerm(newTermCoefficient, combinedVariableExponents);
    }

    // Method to divide two terms and return a new term with the resulting coefficient and exponents
    public static AlgebraicTerm divideTerms(AlgebraicTerm numeratorTerm, AlgebraicTerm denominatorTerm) {
        // Divide the coefficients of both terms
        double newTermCoefficient = numeratorTerm.termCoefficient / denominatorTerm.termCoefficient;

        // Combine the variables from both terms and subtract the exponents of the denominator from the numerator
        Map<String, Integer> resultingVariableExponents = new HashMap<>(numeratorTerm.variableExponents); // Start with numeratorTerm's variables
        for (Map.Entry<String, Integer> variableEntry : denominatorTerm.variableExponents.entrySet()) {
            // Subtract the exponents of the denominator from the numerator
            resultingVariableExponents.put(variableEntry.getKey(),
                    resultingVariableExponents.getOrDefault(variableEntry.getKey(), 0) - variableEntry.getValue());
        }

        // Return a new AlgebraicTerm with the updated coefficient and combined variables
        return new AlgebraicTerm(newTermCoefficient, resultingVariableExponents);
    }

    // Method to convert the term into a string representation (used for output formatting)
    @Override
    public String toString() {
        // If the coefficient is zero, return an empty string as there's no need to display it
        if (termCoefficient == 0) return "";

        StringBuilder termStringBuilder = new StringBuilder();

        // Handle the coefficient part (e.g., "2x^2", "-x^2")
        String coefficientString = (termCoefficient == 1) ? "" : (termCoefficient == -1 ? "-" : String.valueOf(termCoefficient));
        termStringBuilder.append(coefficientString);

        // Handle the variables and their exponents (e.g., "x^2", "y^3")
        for (Map.Entry<String, Integer> variableEntry : variableExponents.entrySet()) {
            String variableName = variableEntry.getKey();
            int variableExponent = variableEntry.getValue();
            if (variableExponent != 0) {
                termStringBuilder.append(variableName);
                if (variableExponent != 1) {
                    termStringBuilder.append("^").append(variableExponent);  // Append exponent only if it's not 1
                }
            }
        }

        return termStringBuilder.toString();
    }
}

public class Algebra {

    // Method to parse an algebraic expression into a list of individual terms.
    static List<AlgebraicTerm> parseAlgebraicExpression(String expression) {
        List<AlgebraicTerm> parsedTerms = new ArrayList<>();  // List to store parsed terms

        // Remove all spaces from the expression to simplify parsing
        expression = expression.replaceAll("\\s+", "");

        // Add a leading '+' if the expression doesn't factorPrompt with a '+' or '-' to help with parsing
        if (!expression.startsWith("+") && !expression.startsWith("-")) {
            expression = "+" + expression;
        }

        // Split the expression into parts based on the '+' and '-' signs, while preserving the signs
        // This allows us to handle each term correctly, whether it's positive or negative
        String[] expressionParts = expression.split("(?=[+-])|(?<=[+-])");

        // Iterate over each part of the expression to parse it as an individual term
        for (String part : expressionParts) {
            part = part.trim();  // Remove any extra spaces around the term
            if (part.isEmpty()) continue;  // Skip empty parts if any (e.g., extra spaces)

            // If the part is just a '+' or '-', skip it as it's only a sign for the next term
            if (part.equals("+") || part.equals("-")) {
                continue;
            }

            // If the part involves multiplication or division, handle it separately
            if (part.contains("*") || part.contains("/")) {
                parsedTerms.add(parseMultiplicativeExpression(part));
            } else {
                // Otherwise, parse it as a single term (e.g., "x^2" or "2y")
                parsedTerms.add(parseSingleTerm(part));
            }
        }
        return parsedTerms;
    }

    // Helper method to parse multiplicative (multiplication or division) expressions
    private static AlgebraicTerm parseMultiplicativeExpression(String expression) {
        // Split the expression into individual factors, separating by '*' or '/'
        String[] multiplicationDivisionFactors = expression.split("(?=[*/])|(?<=[*/])");

        // Start with the first term (factor) in the expression
        AlgebraicTerm intermediateTerm = parseSingleTerm(multiplicationDivisionFactors[0]);

        // Process the remaining factors (either multiplication or division)
        for (int factorIndex = 1; factorIndex < multiplicationDivisionFactors.length; factorIndex++) {
            String operator = multiplicationDivisionFactors[factorIndex].trim();  // Trim spaces around the operator
            if (operator.equals("*")) {
                // If the operator is multiplication, multiply the current result by the next term
                intermediateTerm = AlgebraicTerm.multiplyTerms(intermediateTerm, parseSingleTerm(multiplicationDivisionFactors[factorIndex + 1]));
                factorIndex++;  // Skip the next term since it has already been processed
            } else if (operator.equals("/")) {
                // If the operator is division, divide the current result by the next term
                intermediateTerm = AlgebraicTerm.divideTerms(intermediateTerm, parseSingleTerm(multiplicationDivisionFactors[factorIndex + 1]));
                factorIndex++;  // Skip the next term since it has already been processed
            }
        }

        return intermediateTerm;  // Return the final result of the multiplication or division
    }

    // Helper method to parse a single term, such as "2x^2", "x^3", or constants like "5"
    private static AlgebraicTerm parseSingleTerm(String term) {
        // Regular expression to match terms like "2x^2y", "x^3", "2xy^2", or constants like "5.0"
        String termPattern = "^([+-]?\\d*\\.?\\d+)?([a-zA-Z]+)(\\^\\d+)?$";
        Pattern termRegexPattern = Pattern.compile(termPattern);
        Matcher termMatcher = termRegexPattern.matcher(term);

        // If the term matches the expected format, parse it
        if (termMatcher.matches()) {
            // Get the coefficient part, if present; default to 1 or -1 based on the sign
            String coefficientString = termMatcher.group(1);
            double termCoefficient = (coefficientString == null || coefficientString.isEmpty()) ? 1.0 : Double.parseDouble(coefficientString);
            if (coefficientString != null && coefficientString.equals("-")) {
                termCoefficient = -1.0; // Handle negative sign
            }

            // Get the variable part (e.g., "x", "xy")
            String variablePart = termMatcher.group(2);
            Map<String, Integer> variableExponentMap = new HashMap<>();

            // Parse the variables and set their exponents (default exponent is 1)
            for (int i = 0; i < variablePart.length(); i++) {
                String variableName = String.valueOf(variablePart.charAt(i));
                variableExponentMap.put(variableName, 1);  // Each variable has an exponent of 1 by default
            }

            // Handle the exponent part (e.g., ^2 for x^2, y^3)
            String exponentPart = termMatcher.group(3);
            if (exponentPart != null) {
                int exponentValue = Integer.parseInt(exponentPart.substring(1)); // Remove the '^' symbol
                variableExponentMap.replaceAll((n, v) -> exponentValue); // Set the exponent for each variable
            }

            return new AlgebraicTerm(termCoefficient, variableExponentMap); // Return the parsed term
        }
        // If the term doesn't match the expected format, throw an error
        throw new IllegalArgumentException("Invalid term format: " + term);
    }

    // Method to simplify the algebraic expression by combining like terms and ordering them
    public static String simplifyAlgebraicExpression(String expression) {
        // Parse the expression into individual terms
        List<AlgebraicTerm> parsedTerms = parseAlgebraicExpression(expression);

        // Map to store terms grouped by their variable exponents
        Map<Map<String, Integer>, AlgebraicTerm> groupedTerms = new HashMap<>();

        for (AlgebraicTerm term : parsedTerms) {
            Map<String, Integer> termVariableExponents = term.variableExponents;
            // If the group for these variables doesn't exist, create it and add the term
            if (!groupedTerms.containsKey(termVariableExponents)) {
                groupedTerms.put(termVariableExponents, term);
            } else {
                // Combine like terms with the same variable exponents
                groupedTerms.get(termVariableExponents).combineLikeTerm(term);
            }
        }

        // Build the simplified expression by concatenating all the terms
        StringBuilder simplifiedExpressionBuilder = new StringBuilder();
        for (AlgebraicTerm term : groupedTerms.values()) {
            // Add a '+' sign if it's not the first term and the coefficient is positive
            if (!simplifiedExpressionBuilder.isEmpty() && term.termCoefficient > 0) {
                simplifiedExpressionBuilder.append("+");
            }
            simplifiedExpressionBuilder.append(term.toString()); // Append the string representation of the term
        }

        return simplifiedExpressionBuilder.toString(); // Return the final simplified expression
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        // Example of simplifying an algebraic expression with multiple divisions and multiplications
        String expression = "";
        System.out.println("Original Expression: " + expression);


        while (expression.contains("(") && expression.contains(")")){

            // Stack to keep track of the indices of opening parentheses '('
            Stack<Integer> openingParenthesesStack = new Stack<>();

            // Variables to track the position of the deepest nested parentheses
            int maxNestingLevel = -1;  // The current maximum nesting level
            int centerStartIndex = -1; // The factorPrompt index of the most deeply nested pair
            int centerEndIndex = -1;  // The end index of the most deeply nested pair

            // Traverse the string character by character
            for (int characterPosition = 0; characterPosition < expression.length(); characterPosition++) {
                char currentChar = expression.charAt(characterPosition);

                // Check if the current character is an opening parenthesis '('
                if (currentChar == '(') {
                    // Push the index of the opening parenthesis onto the stack
                    openingParenthesesStack.push(characterPosition);
                }
                // Check if the current character is a closing parenthesis ')'
                else if (currentChar == ')') {
                    // Ensure that the stack is not empty (there must be a matching opening parenthesis)
                    if (!openingParenthesesStack.isEmpty()) {
                        // Pop the index of the matching opening parenthesis from the stack
                        int matchingOpeningIndex = openingParenthesesStack.pop();

                        // Calculate the current depth (nesting level) of parentheses
                        int currentNestingLevel = openingParenthesesStack.size();

                        // If this nesting level is deeper than any previously encountered,
                        // we update the center-most pair of parentheses
                        if (currentNestingLevel > maxNestingLevel) {
                            maxNestingLevel = currentNestingLevel;
                            centerStartIndex = matchingOpeningIndex;
                            centerEndIndex = characterPosition;
                        }
                    }
                }
            }

            //get the sum inside the innermost brackets
            String mostInsideBrackets = expression.substring(centerStartIndex, centerEndIndex + 1);
            String mostInsideBracketSolved = simplifyAlgebraicExpression(mostInsideBrackets.replaceAll("\\(","").replaceAll("\\)",""));

            // Create a new string with the center-most parentheses replaced
                         //part of expression before bracket         //solved inside bracket   //part of expression after the bracket
            expression = expression.substring(0, centerStartIndex) + mostInsideBracketSolved + expression.substring(centerEndIndex + 1);
            System.out.println(expression);
        }

        // Simplify the algebraic expression and print the result
        String simplifiedExpression = simplifyAlgebraicExpression(expression);
        System.out.println("Simplified Expression: " + simplifiedExpression);
    }
}
