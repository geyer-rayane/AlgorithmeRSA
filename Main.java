package algorithmersa;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		/* Question 1 a : generate public and private keys */
		Key key = new Key();
		ArrayList<Integer> publicKey = key.getPublicKey(187);
		ArrayList<Integer> privateKey = key.getPrivateKey(187);
		System.out.println("Public and private keys : " + publicKey + privateKey);

		/*
		 * Question 1 b : encrypt and decrypt a number like a message with RSA algorithm
		 */
		int testMessage = 125;
		int encryptedMessage = Algorithm.getEncryption(testMessage, publicKey);
		System.out.println("Encrypted message : " + encryptedMessage);
		int decryptedMessage = Algorithm.getDecryption(encryptedMessage, privateKey);
		System.out.println("Decrypted message : " + decryptedMessage);

		/*
		 * Question 2 a : [9197, 6284, 12836, 8709, 4584, 10239, 11553, 4584, 7008,
		 * 12523, 9862, 356, 5356, 1159, 10280, 12523, 7506, 6311] encrypted with
		 * (e=12413 ; n=13289)
		 */

		ArrayList<Integer> testList = new ArrayList<Integer>();
		List<Integer> numbers = List.of(9197, 6284, 12836, 8709, 4584, 10239, 11553, 4584, 7008, 12523, 9862, 356, 5356,
				1159, 10280, 12523, 7506, 6311);
		testList.addAll(numbers);

		ArrayList<Integer> publicKeyList = new ArrayList<Integer>();
		publicKeyList.add(12413);
		publicKeyList.add(13289);

		ArrayList<Integer> encryptedList = Algorithm.getEncryptionList(testList, publicKeyList);
		System.out.println("Encrypted list" + encryptedList);
		ArrayList<Integer> decryptedList = Algorithm.getDecryptionList(encryptedList, publicKeyList);
		System.out.println("Decrypted list" + decryptedList);

		/* Question 2 b Complexity quadratic O(n**2) */

		/*
		 * Question 2 Public key : (e=163119273;n=755918011); Encrypted Message :
		 * [671828605, 407505023, 288441355, 679172842, 180261802]
		 */

		ArrayList<Integer> testList2 = new ArrayList<Integer>();
		List<Integer> numbers2 = List.of(671828605, 407505023, 288441355, 679172842, 180261802);
		testList2.addAll(numbers2);

		ArrayList<Integer> publicKeyList2 = new ArrayList<Integer>();
		publicKeyList2.add(163119273);
		publicKeyList2.add(755918011);

		ArrayList<Integer> encryptedList2 = Algorithm.getEncryptionList(testList2, publicKeyList2);
		System.out.println("Encrypted list" + encryptedList2);
		ArrayList<Integer> decryptedList2 = Algorithm.getDecryptionList(encryptedList2, publicKeyList2);
		System.out.println("Decrypted list" + decryptedList2);

	}

}
