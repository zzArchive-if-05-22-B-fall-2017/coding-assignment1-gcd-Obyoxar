//GCD-Calc by Erik Mayrhofer 3BHIF 21.9.17
package gcd;

import java.util.ArrayList;

/**
 * Calculates a GCD with Euclid's Algorithm and with PrimeFactors.
 * Note: This program assumes that zero is divisible by any number, so
 * 0 % x = 0 for every x != 0
 * This program also assumes that zero is a valid result for the 
 * GCD-Calculation for the case if both operands are 0
 * @author obyoxar
 */
public class Gcd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean error = false;
        for(int i = 0; i < 500 && ! error; i++){
            for(int j = 0; j < i && !error; j++){
                if(!compareGCD(i, j)){
                    System.out.printf("Error Encountered while calculating GCD %d %d", i, j);
                    error = true;
                }
            }
        }
    }
    
    /**
     * Calls both algorithms (gcdPrimeFactors, gcdEuclidean) to calculate the 
     * gcd of a and b, and compares the results. It will output any calculation
     * with an interesting result. (GCD is bigger than 2)
     * @param a
     * @param b
     * @return both algorithms output the same number.
     */
    public static boolean compareGCD(int a, int b){
        int primeFactors = gcdPrimeFactors(a,b);
        int euclidean = gcdEuclidean(a, b);
        if(primeFactors > 2){
            System.out.printf("gcd(%3d,%3d) = primefactors: %d, euclidean: %d\n", a, b, primeFactors, euclidean);
        }
        return primeFactors == euclidean;
    }
    
    /**
     * Calculates the GCD using prime-factors.
     * @param a
     * @param b
     * @return GCD(a,b)
     */
    public static int gcdPrimeFactors(int a, int b){
        if(b == 0) return a;
        ArrayList<Integer> discoveredPrimes = new ArrayList<>();
        
        int currentPrime = 2;
        int gcd = 1;
        
        while(a > 1 && b > 1){
            
            //Pull this primefactor out of both numbers by dividing them as
            //often as possible
            while(a % currentPrime == 0){
                a /= currentPrime;
                if(b % currentPrime == 0){
                    b /= currentPrime;
                    
                    //A and B are divisible by currentPrime, so it is part of gcd
                    gcd *= currentPrime;
                }
            }
            while(b % currentPrime == 0) b /= currentPrime;
            
            //This Primefactor is used up, get next one!;
            boolean needAnotherIteration = true;
            while(needAnotherIteration){
                currentPrime += currentPrime == 2 ? 1 : 2; //Since the start value is 2
                
                needAnotherIteration = false;
                //Check current Prime for primality
                for(int i : discoveredPrimes){
                    if(currentPrime % i == 0){
                        //CurrentPrime is not a prime for being divisible by a previous one
                        needAnotherIteration = true;
                    }
                }
                discoveredPrimes.add(currentPrime);
            }
        }
        
        return gcd;
    }
    
    /**
     * Euclid's algorithm for calculating the GCD
     * @param a First number, has to be bigger than b
     * @param b Second Number
     * @return GCD(a,b)
     */
    public static int gcdEuclidean(int a, int b){
        if(b == 0) return a;
        return gcdEuclidean(b, a%b);
    }
    
}
