package pl.szaqku.lab2;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestResult;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.Random;

public class ExerciseUtils extends TestCase {

//    public void testGetPrime() {
//        System.out.println(BigInteger.probablePrime(4096, new Random()));
//    }

    public void testEx1() {
        var rsa = new PlainRSA(64);

        var s = rsa.encrypt("ABC");
        var m = new BigInteger(s).modPow(rsa.getE(), rsa.getN());
    }
    public void testEx2() {
        var rsa = new PlainRSA(64);

        var m1 = "ABC";
        var m2 = "BCA";

        var m = new BigInteger(m1.getBytes())
                .multiply(new BigInteger(m2.getBytes()))
                .mod(rsa.getN());

        var s1 = rsa.encrypt(m1);
        var s2 = rsa.encrypt(m2);

        var s = new BigInteger(s1)
                .multiply(new BigInteger(s2))
                .mod(rsa.getN());

        Assert.assertTrue(rsa.verify(m, s.toByteArray()));
    }

    public void testEx3() throws UnsupportedEncodingException {
        var s = new BigInteger("2829246759667430901779973875");

        var e = new BigInteger("3");
        var N = new BigInteger(
                "7486374846663627918089811394557316880016731434900733973466" +
                "4557033677222985045895878321130196223760783214379338040678" +
                "2339080107477732640032376205901411740283301540121395970682" +
                "3612154294544242607436701783834990586691512046997836198600" +
                "2240362282392181726265023378796284600697013635003150020012" +
                "763665368297013349");

        var m = floorOfNthRoot(s, e.intValue());

        var hexOut = HexFormat.of().formatHex(m.toByteArray());
        var out = fromHex(hexOut);

        Assert.assertEquals("TMEK", out);
    }

    public void testZad4() throws NoSuchAlgorithmException {
        var rsa = new PlainRSA(64);

    }

    public String fromHex(String hex) throws UnsupportedEncodingException {
        hex = hex.replaceAll("^(00)+", "");
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return new String(bytes);
    }
    public static BigInteger floorOfNthRoot(BigInteger x, int n) {
        int sign = x.signum();
        if (n <= 0 || (sign < 0))
            throw new IllegalArgumentException();
        if (sign == 0)
            return BigInteger.ZERO;
        if (n == 1)
            return x;
        BigInteger a;
        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger bigNMinusOne = BigInteger.valueOf(n - 1);
        BigInteger b = BigInteger.ZERO.setBit(1 + x.bitLength() / n);
        do {
            a = b;
            b = a.multiply(bigNMinusOne).add(x.divide(a.pow(n - 1))).divide(bigN);
        } while (b.compareTo(a) == -1);
        return a;
    }

}