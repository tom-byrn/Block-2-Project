package Factors;

import java.util.ArrayList;

public class PrimeFinder {

    int n;
    long nthPrime;

    public PrimeFinder(int n){
        this.n = n;
    }

    public PrimeFinder(){}

    //This class finds the Nth prime number through a few steps
    //It firstly gives an upper bound estimate for the

    // Method to find the nth prime
    public long findNthPrime() {

        try {
            if (n < 1) {
                throw new IllegalArgumentException("n must be greater than 0");
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage() + "\n");
            FactorManager.primePrompt();
        }

        // Use a rough estimate for the upper bound of nth prime
        long limit = (n < 6) ? 15 : (long) (n * Math.log(n) + n * Math.log(Math.log(n)));

        // Generate primes using the Sieve of Eratosthenes
        boolean[] isPrime = new boolean[(int) (limit + 1)];
        ArrayList<Long> primes = new ArrayList<>();

        // Initialize the sieve
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }

        // Sieve of Eratosthenes
        for (int p = 2; p * p <= limit; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= limit; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        // Collect primes
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add((long) i);
            }
        }

        // Return the nth prime
        if (primes.size() >= n) {
            nthPrime = primes.get(n - 1);
            return nthPrime;
        } else {
            throw new RuntimeException("Estimated limit too low, increase limit");
        }

    }


    public long getNthPrime() {
        return nthPrime;
    }
}
