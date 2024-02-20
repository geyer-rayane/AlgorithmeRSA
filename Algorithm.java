package algorithmersa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class Algorithm {

	public static Map<Character, Integer> alphabet = Map.ofEntries(Map.entry('A', 0), Map.entry('B', 1),
			Map.entry('C', 2), Map.entry('D', 3), Map.entry('E', 4), Map.entry('F', 5), Map.entry('G', 6),
			Map.entry('H', 7), Map.entry('I', 8), Map.entry('J', 9), Map.entry('K', 10), Map.entry('L', 11),
			Map.entry('M', 12), Map.entry('N', 13), Map.entry('O', 14), Map.entry('P', 15), Map.entry('Q', 16),
			Map.entry('R', 17), Map.entry('S', 18), Map.entry('T', 19), Map.entry('U', 20), Map.entry('V', 21),
			Map.entry('W', 22), Map.entry('X', 23), Map.entry('Y', 24), Map.entry('Z', 25), Map.entry('_', 26),
			Map.entry('.', 27), Map.entry('?', 28), Map.entry('€', 29), Map.entry('0', 30), Map.entry('1', 31),
			Map.entry('2', 32), Map.entry('3', 33), Map.entry('4', 34), Map.entry('5', 35), Map.entry('6', 36),
			Map.entry('7', 37), Map.entry('8', 38), Map.entry('9', 39));

	public static Map<Integer, Character> alphabetReversed = Map.ofEntries(Map.entry(0, 'A'), Map.entry(1, 'B'),
			Map.entry(2, 'C'), Map.entry(3, 'D'), Map.entry(4, 'E'), Map.entry(5, 'F'), Map.entry(6, 'G'),
			Map.entry(7, 'H'), Map.entry(8, 'I'), Map.entry(9, 'J'), Map.entry(10, 'K'), Map.entry(11, 'L'),
			Map.entry(12, 'M'), Map.entry(13, 'N'), Map.entry(14, 'O'), Map.entry(15, 'P'), Map.entry(16, 'Q'),
			Map.entry(17, 'R'), Map.entry(18, 'S'), Map.entry(19, 'T'), Map.entry(20, 'U'), Map.entry(21, 'V'),
			Map.entry(22, 'W'), Map.entry(23, 'X'), Map.entry(24, 'Y'), Map.entry(25, 'Z'), Map.entry(26, '_'),
			Map.entry(27, '.'), Map.entry(28, '?'), Map.entry(29, '€'), Map.entry(30, '0'), Map.entry(31, '1'),
			Map.entry(32, '2'), Map.entry(33, '3'), Map.entry(34, '4'), Map.entry(35, '5'), Map.entry(36, '6'),
			Map.entry(37, '7'), Map.entry(38, '8'), Map.entry(39, '9'));

	public static long modularExponentiation(long base, long exponent, long modulus) {
		if (modulus == 1)
			return 0;
		long result = 1;
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

	public static long getEncryptionByModularExponentiation(long message, ArrayList<Long> publicKey) {
		return modularExponentiation(message, publicKey.get(0), publicKey.get(1));
	}

	/* Asking a crypted message and (d,n) and returns uncrypted message */
	public static long getDecryptionByModularExponentiation(long encryptedMessage, ArrayList<Long> privateKey) {
		return modularExponentiation(encryptedMessage, privateKey.get(0), privateKey.get(1));
	}

	public static ArrayList<Long> getEncryptionList(ArrayList<Long> listMessage, ArrayList<Long> publicKey) {
		ArrayList<Long> encryptedList = new ArrayList<Long>();
		for (long message : listMessage) {
			encryptedList.add(getEncryptionByModularExponentiation(message, publicKey));
		}
		return encryptedList;
	}

	public static ArrayList<Long> getDecryptionList(ArrayList<Long> listMessage, ArrayList<Long> privateKey) {
		ArrayList<Long> decryptedList = new ArrayList<Long>();
		for (long message : listMessage) {
			decryptedList.add(getDecryptionByModularExponentiation(message, privateKey));
		}
		return decryptedList;
	}

	public static double getNumberOfBloc(long n) {
		return Math.ceil((Math.log(n) / Math.log(40)));
	}

	public static ArrayList<String> getDecomposition(String message, long l) {
		double n = getNumberOfBloc(l);
		ArrayList<String> decomposedList = new ArrayList<>();
		for (int i = 0; i < message.length(); i += n) {
			double endIndex = Math.min(i + n, message.length());
			decomposedList.add(message.substring(i, (int) endIndex));
		}
		return decomposedList;
	}

	public static ArrayList<Long> getConversion(ArrayList<String> message, long n) {
		ArrayList<Long> listConverted = new ArrayList<>();
		for (String str : message) {
			long value = 0;
			int power = str.length() - 1;

			for (char c : str.toCharArray()) {
				int digit = alphabet.get(c);
				value += digit * (long) Math.pow(40, power);
				power--;
			}

			listConverted.add((long) Math.pow(value, 3)  % n);
		}

		return listConverted;
	}

	public static String getDecryption(long cryptedMessage, long n) {
		StringBuilder decryptedMessage = new StringBuilder();
		double lenBlock = getNumberOfBloc(n);
		long base = 40; // Base utilisée pour la conversion

		// Pour chaque bloc
		for (int i = 0; i < lenBlock; i++) {

			long currentBlock = cryptedMessage % (long) Math.pow(base, i + 1);
			if (currentBlock == 0) {
				decryptedMessage.insert(0, alphabetReversed.get((int) 0));

			}

			// Pour chaque coefficient
			while (currentBlock > 0) {
				long coefficient = currentBlock % base;
				decryptedMessage.insert(0, alphabetReversed.get((int) coefficient));
				currentBlock /= base;
			}

			// Décaler le message crypté vers la droite pour passer au bloc suivant
			cryptedMessage /= (long) Math.pow(base, i + 1);
		}

		return decryptedMessage.toString();
	}

	public static ArrayList<Long> getDecompositionBase40(long nombre) {
		ArrayList<Long> listeDecomposition = new ArrayList<>();

		while (nombre > 0) {
			long chiffre = nombre % 40;
			listeDecomposition.add(chiffre);
			nombre = nombre / 40;
		}
		Collections.reverse(listeDecomposition);
		return listeDecomposition;
	}

	public static String getConversionFromListToString(ArrayList<Long> listMessage, long n) {
	    StringBuilder originalMessage = new StringBuilder();

	    for (long message : listMessage) 
	    {
	    		ArrayList<Long> messageSplitBase40 = getDecompositionBase40(message) ;
	    		for (long i : messageSplitBase40)
	    		{
	    			originalMessage.append(alphabetReversed.get((int) i)) ;
	    		}
	    }

	return originalMessage.toString();
}

}
