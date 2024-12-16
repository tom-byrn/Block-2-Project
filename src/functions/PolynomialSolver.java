package functions;

import static menu.Colours.*;

public class PolynomialSolver {

    public double a;
    public double b;
    public double c;
    public double d;
    public double e;
    public String[] roots;

    public PolynomialSolver(){}

    public PolynomialSolver(double a, double b){
        this.a = a;
        this.b = b;
    }

    public PolynomialSolver(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public PolynomialSolver(double a, double b, double c, double d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public PolynomialSolver(double a, double b, double c, double d, double e){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }

    //Basic formula to find a single root
    public String[] linearPolynomial(){
        roots = new String[1];
        double root = - b / a;
        roots[0] = String.valueOf(root);
        return roots;
    }

    //The minus b formula to find roots of a quadratic
    public String[] quadraticPolynomial(){

        if(a == 0){ //Handles situations where the user inputs the wrong type of polynomial
            PolynomialSolver ps = new PolynomialSolver(b, c);
            return linearPolynomial();
        }

        roots = new String[2];
        double discriminant = (b*b - 4*a*c);
        if(discriminant < 0){
            //Complex roots:
            discriminant = -discriminant;
            roots[0] = String.format("%f + %fi", -b, Math.sqrt(discriminant));
            roots[1] = String.format("%f - %fi", -b, Math.sqrt(discriminant));
        } else {
            //Real roots:
            roots[0] = String.valueOf(((-b + Math.sqrt(discriminant)) / 2*a));
            roots[1] = String.valueOf(((-b - Math.sqrt(discriminant)) / 2*a));
        }
        return roots;
    }

    //Cardano's method for finding the roots of a cubic equation https://brilliant.org/wiki/cardano-method/
    public String[] cubicPolynomial() {

        if(a == 0){
            PolynomialSolver ps = new PolynomialSolver(b, c, d);
            return quadraticPolynomial();
        }

        roots = new String[3];

        // Normalize the polynomial
        if (a != 1) {
            b /= a;
            c /= a;
            d /= a;
            a = 1;
        }

        // Compute p and q
        double p = (3 * a * c - b * b) / (3 * a * a);
        double q = (2 * b * b * b - 9 * a * b * c + 27 * a * a * d) / (27 * a * a * a);

        // Compute the discriminant
        double discriminant = Math.pow(q / 2, 2) + Math.pow(p / 3, 3);

        // Shift for depressed cubic
        double shift = -b / (3 * a);

        if (discriminant > 0) {
            // One real root, two complex conjugate roots
            double u = Math.cbrt(-q / 2 + Math.sqrt(discriminant));
            double v = Math.cbrt(-q / 2 - Math.sqrt(discriminant));

            double realRoot = u + v + shift;
            double imaginaryPart = Math.sqrt(3) * Math.abs(u - v) / 2;

            roots[0] = String.format("%.6f", realRoot);
            roots[1] = String.format("%.6f + %.6fi", -(u + v) / 2 + shift, imaginaryPart);
            roots[2] = String.format("%.6f - %.6fi", -(u + v) / 2 + shift, imaginaryPart);
        } else if (Math.abs(discriminant) < 1e-6) {
            // All roots real, at least two are equal
            double u = Math.cbrt(-q / 2);
            double doubleRoot = 2 * u + shift;
            double singleRoot = -u + shift;

            roots[0] = String.format("%.6f", doubleRoot);
            roots[1] = String.format("%.6f", doubleRoot);
            roots[2] = String.format("%.6f", singleRoot);
        } else {
            // All roots real and distinct
            double r = Math.sqrt(-Math.pow(p / 3, 3));
            double phi = Math.acos(-q / (2 * r));
            double root1 = 2 * Math.cbrt(r) * Math.cos(phi / 3) + shift;
            double root2 = 2 * Math.cbrt(r) * Math.cos((phi + 2 * Math.PI) / 3) + shift;
            double root3 = 2 * Math.cbrt(r) * Math.cos((phi + 4 * Math.PI) / 3) + shift;

            roots[0] = String.format("%.6f", root1);
            roots[1] = String.format("%.6f", root2);
            roots[2] = String.format("%.6f", root3);
        }

        return roots;
    }


    //Ferrari's method for solving fourth degree polynomials https://encyclopediaofmath.org/wiki/Ferrari_method
    public String[] quarticPolynomial() {

        if (a == 0) {
            PolynomialSolver ps = new PolynomialSolver(b, c, d, e);
            return ps.cubicPolynomial();
        }

        roots = new String[4];

        // Normalize the polynomial
        if (a != 1) {
            b /= a;
            c /= a;
            d /= a;
            e /= a;
        }

        // Step 1: Depress the quartic equation
        double alpha = -3 * Math.pow(b, 2) / 8 + c;
        double beta = Math.pow(b, 3) / 8 - (b * c) / 2 + d;
        double gamma = -3 * Math.pow(b, 4) / 256 + (Math.pow(b, 2) * c) / 16 - (b * d) / 4 + e;

        // Step 2: Solve the resolvent cubic
        double aRes = 1;
        double bRes = 2 * alpha;
        double cRes = alpha * alpha - 4 * gamma;
        double dRes = -beta * beta;

        PolynomialSolver cubicSolver = new PolynomialSolver(aRes, bRes, cRes, dRes);
        String[] cubicRoots = cubicSolver.cubicPolynomial();

        // Correctly choose the largest real root of the cubic
        double y = Double.NEGATIVE_INFINITY;
        for (String root : cubicRoots) {
            if (!root.contains("i")) {
                double realRoot = Double.parseDouble(root);
                if (realRoot > y) {
                    y = realRoot;
                }
            }
        }

        if (y == Double.NEGATIVE_INFINITY) {
            throw new ArithmeticException("No real root found in the resolvent cubic.");
        }

        // Step 3: Solve the two quadratic equations
        double r1 = Math.sqrt(y);
        if (Double.isNaN(r1)) {
            throw new ArithmeticException("Invalid square root for y: " + y);
        }

        double discriminant1 = 2 * alpha + 2 * y - beta / r1;
        double discriminant2 = 2 * alpha + 2 * y + beta / r1;

        double r2 = discriminant1 >= 0 ? Math.sqrt(discriminant1) : Double.NaN;
        double r3 = discriminant2 >= 0 ? Math.sqrt(discriminant2) : Double.NaN;

        roots[0] = String.format("%.6f", -b / 4 + r1 / 2 + (Double.isNaN(r2) ? 0 : r2 / 2));
        roots[1] = String.format("%.6f", -b / 4 + r1 / 2 - (Double.isNaN(r2) ? 0 : r2 / 2));
        roots[2] = String.format("%.6f", -b / 4 - r1 / 2 + (Double.isNaN(r3) ? 0 : r3 / 2));
        roots[3] = String.format("%.6f", -b / 4 - r1 / 2 - (Double.isNaN(r3) ? 0 : r3 / 2));

        return roots;
    }



    private String formatRoots(int boxWidth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roots.length; i++) {
            String rootLine = String.format("Root %d: %s", i + 1, roots[i]);
            sb.append(formatLine(rootLine, boxWidth));
        }
        return sb.toString();
    }

    private String formatLine(String content, int boxWidth) {
        int paddingSize = boxWidth - content.length() - 4; // Account for borders and spaces
        int paddingLeft = paddingSize / 2;
        int paddingRight = paddingSize - paddingLeft;
        return CYAN + "║" + " ".repeat(paddingLeft) + WHITE + content + " ".repeat(paddingRight) + CYAN + "    ║\n" + RESET;
    }

    public String toString(double a, double b, double c, double d, double e) {
        int boxWidth = 66; // Adjust this for wider equations if needed
        StringBuilder sb = new StringBuilder(CYAN + "╔" + "═".repeat(boxWidth) + "╗\n" + RESET);
        String equation = String.format("Roots of %.2fx^4 + %.2fx^3 + %.2fx^2 + %.2fx + %.2f", a, b, c, d, e);
        sb.append(formatLine(equation, boxWidth));
        sb.append(CYAN + "╠" + "═".repeat(boxWidth) + "╣\n" + RESET);
        sb.append(formatRoots(boxWidth));
        sb.append(CYAN + "╚" + "═".repeat(boxWidth) + "╝\n" + RESET);
        return sb.toString();
    }

    public String toString(double a, double b, double c, double d) {
        int boxWidth = 66; // Adjust this for wider equations if needed
        StringBuilder sb = new StringBuilder(CYAN + "╔" + "═".repeat(boxWidth) + "╗\n" + RESET);
        String equation = String.format("Roots of %.2fx^3 + %.2fx^2 + %.2fx + %.2f", a, b, c, d);
        sb.append(formatLine(equation, boxWidth));
        sb.append(CYAN + "╠" + "═".repeat(boxWidth) + "╣\n" + RESET);
        sb.append(formatRoots(boxWidth));
        sb.append(CYAN + "╚" + "═".repeat(boxWidth) + "╝\n" + RESET);
        return sb.toString();
    }

    public String toString(double a, double b, double c) {
        int boxWidth = 66; // Adjust this for wider equations if needed
        StringBuilder sb = new StringBuilder(CYAN + "╔" + "═".repeat(boxWidth) + "╗\n" + RESET);
        String equation = String.format("Roots of %.2fx^2 + %.2fx + %.2f", a, b, c);
        sb.append(formatLine(equation, boxWidth));
        sb.append(CYAN + "╠" + "═".repeat(boxWidth) + "╣\n" + RESET);
        sb.append(formatRoots(boxWidth));
        sb.append(CYAN + "╚" + "═".repeat(boxWidth) + "╝\n" + RESET);
        return sb.toString();
    }

    public String toString(double a, double b) {
        int boxWidth = 66; // Adjust this for wider equations if needed
        StringBuilder sb = new StringBuilder(CYAN + "╔" + "═".repeat(boxWidth) + "╗\n" + RESET);
        String equation = String.format("Roots of %.2fx + %.2f", a, b);
        sb.append(formatLine(equation, boxWidth));
        sb.append(CYAN + "╠" + "═".repeat(boxWidth) + "╣\n" + RESET);
        sb.append(formatRoots(boxWidth));
        sb.append(CYAN + "╚" + "═".repeat(boxWidth) + "╝\n" + RESET);
        return sb.toString();
    }

}
