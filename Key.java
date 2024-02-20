package algorithmersa;

import java.util.ArrayList;

public class Key {

	public static ArrayList<Long> getPrimeDecomposition(long n) {
		ArrayList<Long> primeFactorisation = new ArrayList<>();
		for (long i = 2; i * i <= n; i++) {
			while (n % i == 0) {
				primeFactorisation.add(i);
				n /= i;
			}
		}
		if (n > 1) {
			primeFactorisation.add(n);
		}
		return primeFactorisation;
	}

	/* Euler's function of n */
	public long getPhi(long n) {
		ArrayList<Long> primeFactorisation = getPrimeDecomposition(n);
		return (primeFactorisation.get(0) - 1) * (primeFactorisation.get(1) - 1);
	}

	/* Returns the GCD of 2 numbers */
	public long gcd(long a, long b) {
		while (b != 0) {
			long temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	/* Returns e such that e and phi(n) are primes together */
	public long getEncryptionExponent(long n) {
		ArrayList<Long> listDivisorOfPhi = new ArrayList<Long>();
		long phi = getPhi(n);
		for (long i = 2; i < phi; i++) {
			if (phi % i == 0) {
				listDivisorOfPhi.add(i);
			}
		}
		for (long e = 2; e < phi; e++) {
			if (gcd(e, phi) == 1) {
				return e;
			}
		}
		return -1;
	}

	/* Create public key for RSA algorithm */
	public ArrayList<Long> getPublicKey(long n) {
		ArrayList<Long> publicKey = new ArrayList<>();
		publicKey.add(getEncryptionExponent(n));
		publicKey.add(n);
		return publicKey;
	}

	/* Create private key for RSA algorithm */
	public ArrayList<Long> getPrivateKey(long n) {
		ArrayList<Long> privateKey = new ArrayList<>();
		long phi = getPhi(n);
		long e = getEncryptionExponent(n);
		for (long d = 2; d < n; d++) {
			if (e * d % phi == 1) {
				privateKey.add(d);
				privateKey.add(n);
				return privateKey;
			}
		}
		return null;
	}

	public static long findDecryptionExponent(long e, long phiN) {
		long d = 1;
		while ((d * e) % phiN != 1) {
			d++;
		}
		return d;
	}

	public ArrayList<Long> getPrivateKeyWithPublicKey(ArrayList<Long> publicKey) {
		ArrayList<Long> privateKey = new ArrayList<>();
		long phi = getPhi(publicKey.get(1));
		long e = publicKey.get(0);
		long d = 1;
		while ((d * e) % phi != 1) {
			d++;
		}
		privateKey.add(d);
		privateKey.add(publicKey.get(1));
		return privateKey;
	}
}
