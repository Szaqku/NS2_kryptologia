package pl.szaqku;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HexFormat;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testAES() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla varius justo eu nisl pharetra pharetra. Nam non eleifend nulla, ut interdum erat. Nulla aliquam ex in augue tincidunt, sed aliquam enim malesuada. Suspendisse potenti. Sed id lobortis orci odio.";
        SecretKey key = App.generateKey(128);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[16]);
        String algorithm = "AES/CBC/PKCS5Padding";

        System.out.println("Init: " + input);
        String cipherText = App.encrypt(algorithm, input, key, ivParameterSpec);
        System.out.println("Encrypted: B64: " + cipherText);
        System.out.println("Encrypted: Hex: " + HexFormat.of().formatHex(Base64.getDecoder().decode(cipherText)));
        String plainText = App.decrypt(algorithm, cipherText, key, ivParameterSpec);
        System.out.println("Decrypted: " + plainText);

        assertEquals(input, plainText);
    }

    public void testZad1() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        var blockSize = 128;
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Nulla varius justo eu nisl pharetra pharetra. Nam non eleifend nulla, ut interdum erat. " +
                "Nulla aliquam ex in augue tincidunt, sed aliquam enim malesuada. Suspendisse potenti. " +
                "Sed id lobortis orci odio.";
        SecretKey key = App.generateKey(blockSize);
        var initArray = new byte[16];
        System.out.println(HexFormat.of().formatHex(initArray));
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initArray);
        String algorithm = "AES/CBC/PKCS7Padding";

        System.out.println("Init: " + input);
        String cipherText64 = App.encrypt(algorithm, input, key, ivParameterSpec);
        byte[] encryptedText = Base64.getDecoder().decode(cipherText64);

        var decryptLastDigit = App.decipherSign(initArray[initArray.length-1], encryptedText[encryptedText.length - 1]);

        System.out.println(HexFormat.of().formatHex(new byte[] {decryptLastDigit}));

    }
}
