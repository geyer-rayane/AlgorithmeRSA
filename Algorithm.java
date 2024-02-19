package algorithmRsa;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		/* Question 1 a : generate public and private keys */
		Key key = new Key();
		ArrayList<Long> publicKey = key.getPublicKey(187);
		ArrayList<Long> privateKey = key.getPrivateKey(187);
		System.out.println("Public and private keys : " + publicKey + privateKey);

		/*
		 * Question 1 b : encrypt and decrypt a number like a message with RSA algorithm
		 */
		long testMessage = 125;
		long encryptedMessage = Algorithm.getEncryption(testMessage, publicKey);
		System.out.println("Encrypted message : " + encryptedMessage);
		long decryptedMessage = Algorithm.getDecryption(encryptedMessage, privateKey);
		System.out.println("Decrypted message : " + decryptedMessage);

		/*
		 * Question 2 a : [9197, 6284, 12836, 8709, 4584, 10239, 11553, 4584, 7008,
		 * 12523, 9862, 356, 5356, 1159, 10280, 12523, 7506, 6311] encrypted with
		 * (e=12413 ; n=13289)
		 */

		ArrayList<Long> testList = new ArrayList<Long>();
		List<Long> numbers = List.of(9197L, 6284L, 12836L, 8709L, 4584L, 10239L, 11553L, 4584L, 7008L, 12523L, 9862L, 356L, 5356L,
		        1159L, 10280L, 12523L, 7506L, 6311L);
		testList = new ArrayList<>(numbers);


		ArrayList<Long> publicKeyList = new ArrayList<Long>();
		publicKeyList.add(12413L);
		publicKeyList.add(13289L);

		ArrayList<Long> encryptedList = Algorithm.getEncryptionList(testList, publicKeyList);
		System.out.println("Encrypted list" + encryptedList);
		ArrayList<Long> decryptedList = Algorithm.getDecryptionList(encryptedList, publicKeyList);
		System.out.println("Decrypted list" + decryptedList);

		/* Question 2 b Complexity quadratic O(n**2) */

		/*
		 * Question 2 Public key : (e=163119273;n=755918011); Encrypted Message :
		 * [671828605, 407505023, 288441355, 679172842, 180261802]
		 */

		ArrayList<Long> testList2 = new ArrayList<Long>();
		List<Long> numbers2 = List.of(671828605L, 407505023L, 288441355L, 679172842L, 180261802L);
		testList2 = new ArrayList<>(numbers2);

		ArrayList<Long> publicKeyList2 = new ArrayList<Long>();
		publicKeyList2.add(163119273L);
		publicKeyList2.add(755918011L);

		ArrayList<Long> encryptedList2 = Algorithm.getEncryptionList(testList2, publicKeyList2);
		System.out.println("Encrypted list" + encryptedList2);
		ArrayList<Long> decryptedList2 = Algorithm.getDecryptionList(encryptedList2, publicKeyList2);
		System.out.println("Decrypted list" + decryptedList2);

	}

}
