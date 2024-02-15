package algorithmersa;

import java.util.*;

public class Key {

	/* Giving n returns a prime decomposition for n such that n = p*q p,q primes */
	public ArrayList<Integer> getPrimeDecomposition(int n) {
		ArrayList<Integer> primeFactorisation = new ArrayList<Integer>();
		ArrayList<Integer> listOfPrimes = Prime.getListOfPrimesUntilN(n);
		for (int primeP : listOfPrimes) {
			for (int primeQ : listOfPrimes) {
				if (n == primeP * primeQ) {
					primeFactorisation.add(primeP);
					primeFactorisation.add(primeQ);
					return primeFactorisation;
				}
			}
		}
		return null;
	}

	/* Euler's function of n */
	public int getPhi(int n) {
		ArrayList<Integer> primeFactorisation = getPrimeDecomposition(n);
		return (primeFactorisation.get(0) - 1) * (primeFactorisation.get(1) - 1);
	}

	/* Returns the GCD of 2 numbers */
	public int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	/* Returns e such that e and phi(n) are primes together */
	public int getEncryptionExponent(int n) {
		ArrayList<Integer> listDivisorOfPhi = new ArrayList<Integer>();
		int phi = getPhi(n);
		for (int i = 2; i < phi; i++) {
			if (phi % i == 0) {
				listDivisorOfPhi.add(i);
			}
		}
		for (int e = 2; e < phi; e++) {
			if (gcd(e, phi) == 1) {
				return e;
			}
		}
		return -1;
	}

	/* Create public key for RSA algorithm */
	public ArrayList<Integer> getPublicKey(int n) {
		ArrayList<Integer> publicKey = new ArrayList<Integer>();
		publicKey.add(getEncryptionExponent(n));
		publicKey.add(n);
		return publicKey;
	}

	/* Create private key for RSA algorithm */
	public ArrayList<Integer> getPrivateKey(int n) {
		ArrayList<Integer> privateKey = new ArrayList<Integer>();
		int phi = getPhi(n);
		int e = getEncryptionExponent(n);
		for (int d = 2; d < n; d++) {
			if (e * d % phi == 1) {
				privateKey.add(d);
				privateKey.add(n);
				return privateKey;
			}
		}
		return null;
	}

	public static int findDecryptionExponent(int e, int phiN) {
		int d = 1;
		while ((d * e) % phiN != 1) {
			d++;
		}
		return d;
	}

	public ArrayList<Integer> getPrivateKeyWithPublicKey(ArrayList<Integer> publicKey) {
		ArrayList<Integer> privateKey = new ArrayList<Integer>();
		int phi = getPhi(publicKey.get(1));
		int e = publicKey.get(0);
		int d = 1;
		while ((d * e) % phi != 1) {
			d++;
		}
		privateKey.add(d);
		privateKey.add(publicKey.get(1));
		return privateKey;
	}

}
