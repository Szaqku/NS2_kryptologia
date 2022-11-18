package pl.szaqku.lab2;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.math.BigInteger;

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
        var rsaInstance = new PlainRSA(4096);
        var encrypted = rsaInstance.encrypt(plain);
        var decrypted = rsaInstance.decrypt(encrypted);

        System.out.println(rsaInstance.toString());

        Assert.assertEquals(plain, decrypted);
    }
}