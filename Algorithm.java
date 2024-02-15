package algorithmersa;

import java.util.ArrayList;

public class Algorithm {

	public static int modularExponentiation(int base, int exponent, int modulus) {
		if (modulus == 1)
			return 0;
		int result = 1;
		base = base % modulus;
		while (exponent > 0) {
			if (exponent % 2 == 1) {
				result = (result * base) % modulus;
			}
			exponent = exponent >> 1;
			base = (base * base) % modulus;
		}
		return result;
	}

	public static int getEncryption(int message, ArrayList<Integer> publicKey) {
		double encryptedMessage = modularExponentiation(message, publicKey.get(0), publicKey.get(1));
		return (int) encryptedMessage;
	}

	public static int getDecryption(int encryptedMessage, ArrayList<Integer> privateKey) {
		double decryptedMessage = modularExponentiation(encryptedMessage, privateKey.get(0), privateKey.get(1));
		return (int) decryptedMessage;

	}

	public static ArrayList<Integer> getEncryptionList(ArrayList<Integer> listMessage, ArrayList<Integer> publicKey) {
		ArrayList<Integer> encryptedList = new ArrayList<Integer>();
		for (int message : listMessage) {
			encryptedList.add(getEncryption(message, publicKey));
		}
		return encryptedList;
	}

	public static ArrayList<Integer> getDecryptionList(ArrayList<Integer> listMessage, ArrayList<Integer> publicKey) {
		ArrayList<Integer> decryptedList = new ArrayList<Integer>();
		Key key = new Key() ;
		ArrayList<Integer> privateKey = key.getPrivateKeyWithPublicKey(publicKey) ;
		for (int message : listMessage) {
			decryptedList.add(getDecryption(message, privateKey));
		}
		return decryptedList;
	}

}
