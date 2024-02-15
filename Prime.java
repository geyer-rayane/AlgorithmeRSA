package algorithmersa;

import java.util.ArrayList;

public class Prime {

	/* Asking n and returns if n is prime or not */
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    /* Asking n and returns primes less than n */
    public static ArrayList<Integer> getListOfPrimesUntilN(int n) {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

}
