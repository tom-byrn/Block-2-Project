package functions;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class FunctionsManager {

    public static void start(){
        System.out.println("Functions function chosen");

        Scanner input = new Scanner(System.in);

        List<String> substitutedExpressions = new ArrayList<>();

        System.out.println("Enter a function (e.g., x * x):");
        String functionInput = input.nextLine();

        System.out.println("Enter the start of the range:");
        int startRange = input.nextInt();

        System.out.println("Enter the end of the range:");
        int endRange = input.nextInt();

        String substitution = "";
        String substitutedExpression = "";
        for (int i = startRange; i <= endRange; i++){
            substitution = "(" + i + ")";
            substitutedExpression = functionInput.replace("x", substitution);
            System.out.println(substitutedExpression);
            substitutedExpressions.add(substitutedExpression);
            }
        }
    }


class test{
    public static void main(String[] args) {
        functions.FunctionsManager.start();
    }
}