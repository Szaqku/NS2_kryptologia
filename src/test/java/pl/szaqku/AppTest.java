package pl.szaqku;

import junit.framework.TestCase;
import pl.szaqku.lab1.AesUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    public static final String PLAIN_TEXT = "This simple sentence is forty-seven bytes long.";

    public static byte[] getCipheredText() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return AesUtil.getEncryptedText(PLAIN_TEXT);
    }

    public void testAES() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        var cipherText = getCipheredText();
        assertEquals(PLAIN_TEXT, AesUtil.decrypt(cipherText));
    }

}
