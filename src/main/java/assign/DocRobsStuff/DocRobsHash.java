package assign.DocRobsStuff;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DocRobsHash {
    public void HashUtils() {
    }

    public static String getCryptoHash(String input, String algorithm) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance(algorithm);
            byte[] inputDigest = msgDigest.digest(input.getBytes());
            BigInteger inputDigestBigInt = new BigInteger(1, inputDigest);

            String hashtext;
            for(hashtext = inputDigestBigInt.toString(16); hashtext.length() < 32; hashtext = "0" + hashtext) {
            }

            return hashtext;
        } catch (NoSuchAlgorithmException var6) {
            throw new RuntimeException(var6);
        }
    }
}

