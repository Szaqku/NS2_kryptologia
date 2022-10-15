package pl.szaqku.lab1;

import junit.framework.Assert;
import junit.framework.TestCase;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class OraclePaddingAttackTest extends TestCase {

    public void testDecipherLastByteAsSinglePadding() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String text = "This simple sentence is forty-seven bytes long.";
        var aesCipherText = AesUtil.getEncryptedText(text);
        var attack = new OraclePaddingAttack(new AesCipher(aesCipherText, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES));

        var lastByteIndex = 47;
        Assert.assertEquals(
            0x01,
            attack.decipherByte(lastByteIndex).getRawPlainText().charAt(lastByteIndex)
        );
    }
    public void testDecipherLastTwoBytesAsPadding() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String text = "This simple sentence is forty-seven bytes long";
        var aesCipherText = AesUtil.getEncryptedText(text);
        var attack = new OraclePaddingAttack(new AesCipher(aesCipherText, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES));

        var lastByteIndex = 47;
        Assert.assertEquals(
            0x02,
            attack.decipherByte(lastByteIndex).getRawPlainText().charAt(lastByteIndex)
        );
        lastByteIndex -= 1;
        Assert.assertEquals(
            0x02,
            attack.decipherByte(lastByteIndex).getRawPlainText().charAt(lastByteIndex)
        );
    }

    public void testDecipherLastByteInMiddleBlock() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String text = "This simple sentence is forty-seven bytes long.";
        var aesCipherText = AesUtil.getEncryptedText(text);
        var attack = new OraclePaddingAttack(new AesCipher(aesCipherText, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES));

        var byteIndex = 31;
        Assert.assertEquals(
                text.charAt(byteIndex),
                attack.decipherByte(byteIndex).getRawPlainText().charAt(byteIndex)
        );
    }

    public void testDecipherMiddleBlock() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String text = "This simple sentence is forty-seven bytes long..........";
        var aesCipherText = AesUtil.getEncryptedText(text);
        var attack = new OraclePaddingAttack(new AesCipher(aesCipherText, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES));

        attack.decipherBlock(2);
        Assert.assertEquals(
            "ven bytes long.",
            attack.getRawPlainText().substring(32, 47)
        );

        attack.decipherBlock(1);
        Assert.assertEquals(
            "ence is forty-se",
            attack.getRawPlainText().substring(16, 32)
        );
    }

    public void testDecipherLastBlock() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String text = "This simple sentence is forty-seven bytes long..........";
        var aesCipherText = AesUtil.getEncryptedText(text);
        var attack = new OraclePaddingAttack(new AesCipher(aesCipherText, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES));

        attack.decipherBlock(3);
        System.out.println(attack.getPlainText());
        Assert.assertTrue(
            attack.getPlainText().endsWith("........")
        );
    }

    public void testDecipherLongText() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String text = "This simple sentence is forty-seven bytes long..........";
        var aesCipherText = AesUtil.getEncryptedText(text);
        var attack = new OraclePaddingAttack(new AesCipher(aesCipherText, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES));

        attack.decipher();
        Assert.assertEquals(
            text.substring(16),
            attack.getPlainText()
        );
    }

    public void testDecipherVeryLongText() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String text = "This simple sentence is forty-seven bytes long...................................................................";
        var aesCipherText = AesUtil.getEncryptedText(text);
        var attack = new OraclePaddingAttack(new AesCipher(aesCipherText, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES));

        attack.decipher();
        Assert.assertEquals(
            text.substring(16),
            attack.getPlainText()
        );
    }

    public void testDecipher() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String text = "This simple sentence is forty-seven bytes long.";
        var aesCipherText = AesUtil.getEncryptedText(text);
        var attack = new OraclePaddingAttack(new AesCipher(aesCipherText, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES));

        attack.decipher();
        Assert.assertEquals(
                "ence is forty-seven bytes long.",
                attack.getRawPlainText().substring(16, 47)
        );

        String text2 = "This simple sentence is forty-seven bytes long..";
        var aesCipherText2 = AesUtil.getEncryptedText(text2);
        var attack2 = new OraclePaddingAttack(new AesCipher(aesCipherText2, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES));

        attack2.decipher();
        Assert.assertEquals(
                "ence is forty-seven bytes long..",
                attack2.getPlainText()
        );
    }
}