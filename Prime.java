package algorithmersa;

import java.util.ArrayList;

public class Prime {

    /* Asking n and returns if n is prime or not */
    public static boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    /* Asking n and returns primes less than n */
    public static ArrayList<Long> getListOfPrimesUntilN(long n) {
        ArrayList<Long> primes = new ArrayList<>();
        for (long i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }
}
