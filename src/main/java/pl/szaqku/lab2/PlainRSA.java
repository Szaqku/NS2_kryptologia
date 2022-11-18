package pl.szaqku.lab2;

import java.math.BigInteger;

import java.util.*;


public class PlainRSA {

    private BigInteger d;
    private BigInteger n;
    private BigInteger p;
    private BigInteger q;
    private BigInteger e;

    public PlainRSA(int byteLength) {
        this.p = BigInteger.probablePrime(byteLength, new Random());
        this.q = BigInteger.probablePrime(byteLength, new Random());

        this.n = p.multiply(q);

        var phi = (p.add(BigInteger.valueOf(-1))).multiply(q.add(BigInteger.valueOf(-1)));

        e = BigInteger.probablePrime(byteLength, new Random());

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);
    }

    public byte[] encrypt(String message) {
        return encrypt(new BigInteger(message.getBytes()), e, n).toByteArray();
    }
    public String decrypt(byte[] bytes) {
        return byteToString(decrypt(new BigInteger(bytes), d, n).toByteArray());
    }

    private static String byteToString(byte[] cipher) {
        StringBuilder temp = new StringBuilder();
        for (byte b : cipher)
        {
            temp.append(Character.toString(b));
        }
        return temp.toString();
    }
    public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
        return message.modPow(e, n);
    }

    public static BigInteger decrypt(BigInteger message, BigInteger d, BigInteger n) {
        return message.modPow(d, n);
    }

    @Override
    public String toString() {
        return "RSA{" +
                "d=" + d +
                ", n=" + n +
                ", p=" + p +
                ", q=" + q +
                ", e=" + e +
                '}';
    }
}
