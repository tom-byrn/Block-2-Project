package algebra;

import java.util.*;
import java.util.regex.*;

public class Algebra {

    // Helper class to represent a single term in an algebraic expression
    static class Term {
        int coefficient;  // The coefficient of the term (e.g., 2 in 2x^2)
        String variable;  // The variable part of the term (e.g., x in 2x^2)
        int power;  // The exponent of the variable (e.g., 2 in x^2)
        String trigonometricFunction;  // The trigonometric function (e.g., sin, cos, tan)
        boolean isTrigonometric;  // Flag to indicate if this is a trigonometric function term

        // Constructor to create a new Term with a coefficient, variable, power, and trigonometric function
        Term(int coefficient, String variable, int power, String trigonometricFunction) {
            this.coefficient = coefficient;
            this.variable = variable;
            this.power = power;
            this.trigonometricFunction = trigonometricFunction;
            this.isTrigonometric = trigonometricFunction != null;
        }

        // Combine two terms if they have the same variable and power
        public void combine(Term otherTerm) {
            if (this.variable.equals(otherTerm.variable) && this.power == otherTerm.power) {
                this.coefficient += otherTerm.coefficient;  // Add their coefficients
            }
        }

        // Multiply two terms and return a new term with the resulting coefficient and power
        public static Term multiply(Term term1, Term term2) {
            // Ensure both terms have the same variable before multiplying
            if (!term1.variable.equals(term2.variable)) {
                throw new IllegalArgumentException("Cannot multiply terms with different variables.");
            }
            int newCoefficient = term1.coefficient * term2.coefficient;
            int newPower = term1.power + term2.power;  // The powers are added when multiplying
            return new Term(newCoefficient, term1.variable, newPower, null);
        }

        // Convert the term to a string for displaying in a simplified expression
        @Override
        public String toString() {
            // If the coefficient is 0, the term doesn't appear in the expression
            if (coefficient == 0) return "";

            // Handle the case where coefficient is 1 (don't display it) or -1 (display "-")
            String coefficientString = (coefficient == 1 && !variable.isEmpty()) ? "" : String.valueOf(coefficient);

            // If it's a trigonometric function, format it
            if (isTrigonometric) {
                if (variable.isEmpty()) {
                    return coefficientString + trigonometricFunction + "(x)";
                } else {
                    return coefficientString + trigonometricFunction + "(" + variable + "^" + power + ")";
                }
            }

            // If there's no variable, return just the coefficient
            if (variable.isEmpty()) {
                return coefficientString;
            } else if (power == 1) {
                // If the power is 1, display as "x" instead of "x^1"
                return coefficientString + variable;
            } else {
                // Display in the form of "x^n" for powers greater than 1
                return coefficientString + variable + "^" + power;
            }
        }
    }

    // Method to parse the entire algebraic expression and return a list of terms
    public static List<Term> parseExpression(String expression) {
        List<Term> terms = new ArrayList<>();

        // Remove all spaces from the expression to simplify parsing
        expression = expression.replaceAll("\\s+", "");

        // Add a leading '+' to handle the case where the expression starts with a variable
        if (!expression.startsWith("+") && !expression.startsWith("-")) {
            expression = "+" + expression;
        }

        // Split the expression into parts based on the '+' and '-' signs, while preserving the signs
        String[] parts = expression.split("(?=[+-])|(?<=[+-])");

        // Iterate over each part and parse it as a term
        for (String part : parts) {
            part = part.trim();  // Remove leading and trailing spaces
            if (part.isEmpty()) continue;  // Skip empty parts

            // If the part is just a '+' or '-', skip it as it's just a sign for the next term
            if (part.equals("+") || part.equals("-")) {
                continue;
            }

            // Handle cases of trigonometric functions: sin, cos, or tan
            if (part.startsWith("sin") || part.startsWith("cos") || part.startsWith("tan")) {
                String trigonometricFunction = part.split("\\(")[0];
                String inside = part.split("\\(")[1].replaceAll("\\)", ""); // Get the expression inside parentheses
                Term term = parseSingleTerm(inside);
                term.trigonometricFunction = trigonometricFunction;
                term.isTrigonometric = true;
                terms.add(term);
            } else if (part.contains("*") || part.contains("/")) {
                // Handle multiplication/division
                String[] factors = part.split("[*/]");
                Term term1 = parseSingleTerm(factors[0]);
                Term term2 = parseSingleTerm(factors[1]);
                if (part.contains("*")) {
                    // Multiply the two terms and add the result to the list
                    terms.add(Term.multiply(term1, term2));
                } else if (part.contains("/")) {
                    // Handle division by calculating the new coefficient and power
                    int coefficient = term1.coefficient / term2.coefficient;
                    terms.add(new Term(coefficient, term1.variable, term1.power - term2.power, null));
                }
            } else {
                // If no multiplication or division, just parse the term normally
                terms.add(parseSingleTerm(part));
            }
        }
        return terms;
    }

    // Helper method to parse a single term like "2x^2", "x^3", or "5"
    private static Term parseSingleTerm(String term) {
        // Regular expression to match terms like "2x^2", "x^3", or constants like "5"
        String regex = "([+-]?\\d*)([a-zA-Z])(\\^\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(term);

        // If the term matches the expected format, parse it
        if (matcher.matches()) {
            // Default coefficient is 1 if not specified, handle the optional sign (+ or -)
            int coefficient = matcher.group(1).isEmpty() ? 1 : Integer.parseInt(matcher.group(1));
            if (matcher.group(1) != null && matcher.group(1).equals("-")) {
                coefficient = -Math.abs(coefficient);  // Ensure negative coefficients are handled
            }

            String variable = matcher.group(2);  // The variable (e.g., "x")
            // If no power is specified, the default power is 1
            int power = matcher.group(3) == null ? 1 : Integer.parseInt(matcher.group(3).substring(1));

            return new Term(coefficient, variable, power, null);
        }
        // If the term doesn't match the expected format, throw an error
        throw new IllegalArgumentException("Invalid term: " + term);
    }

    // Method to simplify the algebraic expression by combining like terms and ordering them
    public static String simplifyExpression(String expression) {
        List<Term> terms = parseExpression(expression);

        // Use a map to group terms by their variable and power
        Map<String, Map<Integer, Term>> groupedTerms = new HashMap<>();
        for (Term term : terms) {
            // If it's a trigonometric function term, treat it separately
            if (term.isTrigonometric) {
                groupedTerms.putIfAbsent(term.trigonometricFunction, new HashMap<>());
                groupedTerms.get(term.trigonometricFunction).putIfAbsent(0, new Term(term.coefficient, "", 0, term.trigonometricFunction));
                groupedTerms.get(term.trigonometricFunction).get(0).coefficient += term.coefficient;
            } else {
                // Regular terms (not trigonometric)
                if (term.variable.isEmpty()) {
                    groupedTerms.putIfAbsent("", new HashMap<>());
                    groupedTerms.get("").putIfAbsent(0, new Term(0, "", 0, null));
                    groupedTerms.get("").get(0).coefficient += term.coefficient;
                } else {
                    groupedTerms.putIfAbsent(term.variable, new HashMap<>());
                    groupedTerms.get(term.variable).putIfAbsent(term.power, new Term(0, term.variable, term.power, null));
                    groupedTerms.get(term.variable).get(term.power).combine(term);
                }
            }
        }

        // Combine all terms and sort them by power and variable
        List<Term> combinedTerms = new ArrayList<>();
        for (Map.Entry<String, Map<Integer, Term>> entry : groupedTerms.entrySet()) {
            for (Term term : entry.getValue().values()) {
                if (term.coefficient != 0) {
                    combinedTerms.add(term);
                }
            }
        }

        // Sort terms first by power (descending), then by variable name (alphabetically)
        combinedTerms.sort((t1, t2) -> {
            if (t2.power != t1.power) {
                return Integer.compare(t2.power, t1.power);
            }
            return t1.variable.compareTo(t2.variable);
        });

        // Build the final simplified expression
        StringBuilder simplifiedExpression = new StringBuilder();
        for (Term term : combinedTerms) {
            if (simplifiedExpression.length() > 0 && term.coefficient > 0) {
                simplifiedExpression.append("+");
            }
            simplifiedExpression.append(term);
        }

        return simplifiedExpression.length() > 0 ? simplifiedExpression.toString() : "0";
    }

    // Main method to get user input and output the simplified expression
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an algebraic expression: ");
        String expression = scanner.nextLine();
        String simplifiedExpression = simplifyExpression(expression);
        System.out.println("Simplified Expression: " + simplifiedExpression);
    }
}
