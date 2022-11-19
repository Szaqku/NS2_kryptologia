package pl.szaqku.lab2;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class PlainRSATest extends TestCase {

    public void testGetPrime() {
        var prime = NumberUtil.getPrime(3, 100, BigInteger.valueOf(3));

        Assert.assertEquals(BigInteger.valueOf(5), prime);
    }

    public void testPowMod() {
//        23895^15 % 14189 = 344
        var result = BigInteger.valueOf(23895L).modPow(BigInteger.valueOf(15), BigInteger.valueOf(14189L));
        Assert.assertEquals(result, BigInteger.valueOf(344L));
    }

    public void testRSA() {
        var plain = "Testowy string";
        var rsaInstance = new PlainRSA(256);
        var encrypted = rsaInstance.encrypt(plain);
        var decrypted = rsaInstance.decrypt(encrypted);

        System.out.println(rsaInstance.toString());

        Assert.assertTrue(rsaInstance.verify(plain, encrypted));
        Assert.assertEquals(plain, decrypted);
    }

    /*
    new BigInteger(
        MessageDigest.getInstance("SHA-256").digest(message.getBytes())
    )
    .equals(
        new BigInteger(decryptBytes(
            encrypt(new BigInteger(
                MessageDigest.getInstance("SHA-256").digest(
                    message.getBytes()
                )
            ).toByteArray())
        ))
    )
     */
    public void testSignVerify() throws NoSuchAlgorithmException {
        var plain = "Testowy string";
        var rsaInstance = new PlainRSA(1024);

        var sig = rsaInstance.sign(plain);

        System.out.println(HexFormat.of().formatHex(sig));
        Assert.assertTrue(rsaInstance.verifySignature(plain, sig));
    }

}