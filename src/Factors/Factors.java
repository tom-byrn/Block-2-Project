package Factors;

import java.util.ArrayList;
import java.util.List;

public class Factors {

    private long n; // Instance variable for the number
    private List<Long> factorList; // List to store factors

    // Constructor to initialize the number and compute factors
    public Factors(long n) {
        this.n = n;
        this.factorList = new ArrayList<>();
        calculateFactors();
    }

    //Default constructor
    public Factors(){

    }

    // Method to calculate factors
    private void calculateFactors() {
        for (long i = n - 1; i > 1; i--) {
            if (n % i == 0) {
                factorList.add(i);
            }
        }
    }

    // Method to return factors as a formatted string
    public String getFactors() {
        if (factorList.isEmpty()) {
            return n + " is a prime number!";
        } else {
            StringBuilder result = new StringBuilder();
            result.append(n).append(" has ").append(factorList.size() + 2).append(" factors! They are ").append(n).append(", ");
            for (int i = 0; i < factorList.size(); i++) {
                result.append(factorList.get(i));
                if (i < factorList.size() - 1) {
                    result.append(", ");
                }
            }
            result.append(", and 1");
            return result.toString();
        }
    }

    //Return the number of factors
    public int factorNum(){
        return factorList.size();
    }
}
