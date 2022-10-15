package pl.szaqku.lab1;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AesUtil {

    public static final byte[] KEY_BASE = "0000111122223333".getBytes(StandardCharsets.UTF_8);
    public static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    public static final String KEY_AES = "AES";
    public static final byte[] IV_INIT_ARRAY = "aaaabbbbccccdddd".getBytes(StandardCharsets.UTF_8);

    /**
     * Just a reference based on http://docs.oracle.com/javase/6/docs/technotes/guides/security/StandardNames.html#Cipher
     */
    public static final int AES_DEFAULT_BLOCK_SIZE_BITS = 128;
    public static final int AES_DEFAULT_BLOCK_SIZE_BYTES = 128 / 8;

    public static byte[] getEncryptedText(String text) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        SecretKey key = generateKey();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV_INIT_ARRAY);

        return encrypt(ALGORITHM, text, key, ivParameterSpec);
    }

    public static SecretKey generateKey() {
        return new SecretKeySpec(KEY_BASE, KEY_AES);
    }

    public static byte[] encrypt(String algorithm, String input, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(input.getBytes());
    }

    public static String decrypt(String algorithm, byte[] cipherText, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText);
    }

    public static String decrypt(byte[] cipherText) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return decrypt(ALGORITHM, cipherText, generateKey(), new IvParameterSpec(IV_INIT_ARRAY));
    }
}
