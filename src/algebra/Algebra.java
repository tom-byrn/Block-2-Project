package algebra;

import java.util.*;
import java.util.regex.*;

public class Algebra {

    // Helper class to represent a single algebraic term.
    // A "term" could be something like "3x^2", "-2x", "5", etc.
    static class AlgebraicTerm {
        double coefficient;  // The coefficient of the term, e.g., 2.5 in 2.5x^2
        String variable;  // The variable part of the term, e.g., 'x' in 2.5x^2
        int exponent;  // The exponent (or power) of the variable, e.g., 2 in x^2

        // Constructor to create a new term with a coefficient, variable, and exponent
        AlgebraicTerm(double coefficient, String variable, int exponent) {
            this.coefficient = coefficient;
            this.variable = variable;
            this.exponent = exponent;
        }

        // Method to combine two terms if they have the same variable and exponent
        public void combineLikeTerm(AlgebraicTerm otherTerm) {
            if (this.variable.equals(otherTerm.variable) && this.exponent == otherTerm.exponent) {
                // Combine the coefficients of like terms (e.g., "3x^2" and "2x^2" become "5x^2")
                this.coefficient += otherTerm.coefficient;
            }
        }

        // Method to multiply two terms and return a new term with the resulting coefficient and exponent
        public static AlgebraicTerm multiplyTerms(AlgebraicTerm term1, AlgebraicTerm term2) {
            // Ensure both terms have the same variable (e.g., can't multiply x^2 and y^3)
            if (!term1.variable.equals(term2.variable)) {
                throw new IllegalArgumentException("Cannot multiply terms with different variables.");
            }
            // Multiply the coefficients and add the exponents (power rules of multiplication)
            double newCoefficient = term1.coefficient * term2.coefficient;
            int newExponent = term1.exponent + term2.exponent;
            return new AlgebraicTerm(newCoefficient, term1.variable, newExponent); // Return the resulting term
        }

        // Method to convert the term into a string representation (used for output formatting)
        @Override
        public String toString() {
            // If the coefficient is 0, the term doesn't appear in the expression (e.g., "0x^2" is omitted)
            if (coefficient == 0) return "";

            // Handle the case where coefficient is 1 or -1 and avoid displaying the "1" in the output for simplicity
            String coefficientStr = (coefficient == 1 && !variable.isEmpty()) ? "" : String.valueOf(coefficient);
            if (coefficient == -1 && !variable.isEmpty()) {
                coefficientStr = "-";  // For -1, display only the negative sign
            }

            // If there's no variable, return just the coefficient (constant term like "5")
            if (variable.isEmpty()) {
                return coefficientStr;
            } else if (exponent == 1) {
                // If the exponent is 1, display the term as just "x" instead of "x^1"
                return coefficientStr + variable;
            } else {
                // For higher exponents, format as "x^n" (e.g., x^2)
                return coefficientStr + variable + "^" + exponent;
            }
        }
    }

    // Method to parse an algebraic expression into a list of individual terms.
    // Example: "2x^2 - 3x + 5" -> List of terms: [2x^2, -3x, 5]
    public static List<AlgebraicTerm> parseAlgebraicExpression(String expression) {
        List<AlgebraicTerm> termsList = new ArrayList<>();

        // Remove all spaces from the expression to simplify parsing
        expression = expression.replaceAll("\\s+", "");

        // Add a leading '+' to handle cases where the expression starts with a term without an explicit sign
        if (!expression.startsWith("+") && !expression.startsWith("-")) {
            expression = "+" + expression;
        }

        // Split the expression into parts based on the '+' and '-' signs, preserving the signs
        // This is needed to separate the terms while keeping the signs (+ or -) attached to the terms
        String[] parts = expression.split("(?=[+-])|(?<=[+-])");

        // Iterate over each part and parse it as a term
        for (String part : parts) {
            part = part.trim();  // Remove leading and trailing spaces from each part
            if (part.isEmpty()) continue;  // Skip empty parts (if any)

            // If the part is just a '+' or '-', skip it as it's only a sign for the next term
            if (part.equals("+") || part.equals("-")) {
                continue;
            }

            // Handle terms that involve multiplication or division (e.g., "2*x" or "3x^2/x")
            if (part.contains("*") || part.contains("/")) {
                // Split the term by the multiplication or division operator
                String[] factors = part.split("[*/]");
                AlgebraicTerm term1 = parseSingleTerm(factors[0]);  // Parse the first factor
                AlgebraicTerm term2 = parseSingleTerm(factors[1]);  // Parse the second factor
                if (part.contains("*")) {
                    // If it's multiplication, multiply the terms and add the result to the list
                    termsList.add(AlgebraicTerm.multiplyTerms(term1, term2));
                } else if (part.contains("/")) {
                    // If it's division, divide the coefficients and subtract the exponents
                    double coefficient = term1.coefficient / term2.coefficient;
                    termsList.add(new AlgebraicTerm(coefficient, term1.variable, term1.exponent - term2.exponent)); // Add the result
                }
            } else {
                // If it's a normal term without multiplication/division, parse it normally
                termsList.add(parseSingleTerm(part));
            }
        }
        return termsList;
    }

    // Helper method to parse a single term, such as "2x^2", "x^3", or constants like "5"
    private static AlgebraicTerm parseSingleTerm(String term) {
        // Regular expression to match terms like "2.5x^2", "x^3", or constants like "5.0"
        // The regular expression captures:
        // - [+-]? : optional sign for the term (+ or -)
        // - \d*\.?\d+ : coefficient, e.g., "2.5", "3", or ".5"
        // - ([a-zA-Z]) : the variable part, e.g., "x"
        // - (\^\\d+)? : optional exponent part, e.g., "^2"
        String regex = "([+-]?\\d*\\.?\\d+)([a-zA-Z])(\\^\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(term);

        // If the term matches the expected format (e.g., "2x^2"), parse it
        if (matcher.matches()) {
            // Default coefficient is 1 if not specified, handle the optional sign (+ or -)
            double coefficient = matcher.group(1).isEmpty() ? 1.0 : Double.parseDouble(matcher.group(1));
            if (matcher.group(1) != null && matcher.group(1).equals("-")) {
                coefficient = -Math.abs(coefficient);  // Ensure negative coefficients are handled correctly
            }

            String variable = matcher.group(2);  // The variable part (e.g., "x")
            // If no exponent is specified, the default exponent is 1
            int exponent = matcher.group(3) == null ? 1 : Integer.parseInt(matcher.group(3).substring(1)); // Extract the exponent

            // Return the parsed term
            return new AlgebraicTerm(coefficient, variable, exponent);
        }
        // If the term doesn't match the expected format, throw an error
        throw new IllegalArgumentException("Invalid term format: " + term);
    }

    // Method to simplify the algebraic expression by combining like terms and ordering them
    // Example: "3x^2 + 2x^2 - x + 5 + 2x" -> "5x^2 + x + 5"
    public static String simplifyAlgebraicExpression(String expression) {
        // Parse the expression into a list of terms
        List<AlgebraicTerm> termsList = parseAlgebraicExpression(expression);

        // Use a map to group terms by their variable and exponent
        // This helps to combine like terms (terms with the same variable and exponent)
        Map<String, Map<Integer, AlgebraicTerm>> groupedTerms = new HashMap<>();
        for (AlgebraicTerm term : termsList) {
            // For constant terms (terms with no variable), use an empty string as the key
            if (term.variable.isEmpty()) {
                groupedTerms.putIfAbsent("", new HashMap<>());
                groupedTerms.get("").putIfAbsent(0, new AlgebraicTerm(0, "", 0));  // Default to a zero term
                groupedTerms.get("").get(0).coefficient += term.coefficient; // Combine coefficients of constant terms
            } else {
                // Group terms by their variable and exponent (keyed by variable and exponent)
                groupedTerms.putIfAbsent(term.variable, new HashMap<>());
                groupedTerms.get(term.variable).putIfAbsent(term.exponent, new AlgebraicTerm(0, term.variable, term.exponent));
                groupedTerms.get(term.variable).get(term.exponent).combineLikeTerm(term); // Combine like terms
            }
        }

        // Combine all terms and prepare to sort them
        List<AlgebraicTerm> combinedTerms = new ArrayList<>();
        for (Map.Entry<String, Map<Integer, AlgebraicTerm>> entry : groupedTerms.entrySet()) {
            for (AlgebraicTerm term : entry.getValue().values()) {
                if (term.coefficient != 0) {
                    combinedTerms.add(term);  // Only add non-zero terms
                }
            }
        }

        // Sort terms first by exponent (descending order), then by variable name (alphabetically)
        combinedTerms.sort((t1, t2) -> {
            if (t2.exponent != t1.exponent) {
                return Integer.compare(t2.exponent, t1.exponent);  // Compare exponents in descending order
            }
            return t1.variable.compareTo(t2.variable);  // If exponents are the same, compare by variable name
        });

        // Build the final simplified expression as a string
        StringBuilder simplifiedExpression = new StringBuilder();
        for (AlgebraicTerm term : combinedTerms) {
            if (!simplifiedExpression.isEmpty() && term.coefficient > 0) {
                simplifiedExpression.append("+"); // Add a "+" sign before positive terms
            }
            simplifiedExpression.append(term); // Add the term itself
        }

        // Return the simplified expression or "0" if no terms remain
        return !simplifiedExpression.isEmpty() ? simplifiedExpression.toString() : "0";
    }

    // Main method to read input from the user and output the simplified expression
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an algebraic expression: ");
        String expression = scanner.nextLine();  // Read the user's input expression
        String simplifiedExpression = simplifyAlgebraicExpression(expression);  // Simplify the expression
        System.out.println("Simplified Expression: " + simplifiedExpression);  // Output the result
    }
}
