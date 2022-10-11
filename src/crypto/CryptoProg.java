/**
 * 
 */
package crypto;

/**
 * @author Hans
 *
 */
public class CryptoProg {

	public static String normalizeText(String inText) {
		return inText.replaceAll("[^a-zA-Z]", "").toUpperCase();
	}

	public static String caesarify(String encrypt, int shift) {
		var alphabet = shiftAlphabet(shift);
		String result = "";
		for (var charatPos : encrypt.toCharArray())
			result += alphabet.charAt(charatPos - 'A');
		return result;
	}

	private static String shiftAlphabet(int shift) {
		int start;
		if (shift < 0)
			start = shift + 1 + 'Z';
		else
			start = shift + 'A';
		String result = "";
		char currChar = (char) start;
		for (; currChar <= 'Z'; ++currChar)
			result = result + currChar;
		if (result.length() < 26)
			for (currChar = 'A'; result.length() < 26; ++currChar)
				result = result + currChar;
		return result;
	}

	public static String groupify(String msg, int chunk) {
		int spaceNo = 0;
		String result = "";
		for (int i = 0; i < msg.length(); i++) {
			if ((i > 0) && (i % chunk == 0)) {
				result += ' ';
				spaceNo = 0;
			}
			result += msg.charAt(i);
			spaceNo++;
		}
		for (int j = spaceNo; j < chunk; j++)
			result += 'x';
		return result;
	}

	public static String encryptString(String encrypted, int shift, int chunk) {
		var result = normalizeText(encrypted);
		result = caesarify(result, shift);
		return groupify(result, chunk);
	}

	public static String ungroupify(String encrypted) {
		return encrypted.replaceAll("[^A-Z]", "");
	}

	public static String decryptString(String encrypted, int shift) {
		var result = ungroupify(encrypted);
		return caesarify(result, -shift);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String hej = "Th:is i;s so,me \"re'aly\" greatt. (Text)!?";
		String re = normalizeText(hej);
		System.out.println("In text :" + hej);
		System.out.println("Result :" + re);
		String cae = caesarify(re, 2);
		System.out.println("cae :" + cae);
		var gr = groupify(cae, 4);
		System.out.println("Grupp :" + gr);
		var uni = ungroupify(gr);
		System.out.println(uni);
		var cyphertext = encryptString("Who will win the election?", 5, 3);
		System.out.println("Encrypt :" + cyphertext);
		var plaintext = decryptString(cyphertext, 5);
		System.out.println("Plain : " + plaintext);
	}
}