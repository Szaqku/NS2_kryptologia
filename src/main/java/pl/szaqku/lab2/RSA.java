package pl.szaqku.lab2;

import java.math.BigInteger;

import java.util.*;


public class RSA {

    private BigInteger d;
    private BigInteger n;
    private BigInteger p;
    private BigInteger q;
    private BigInteger e;

    //Consider two prime numbers p and q.
    //Compute n = p*q
    //Compute ϕ(n) = (p – 1) * (q – 1)
    //Choose e such gcd(e , ϕ(n) ) = 1
    //Calculate d such e*d mod ϕ(n) = 1
    //Public Key {e,n} Private Key {d,n}
    //Cipher text C = Pe mod n where P = plaintext
    //For Decryption D = Dd mod n where D will refund the plaintext.

    public RSA() {
        this.p = BigInteger.probablePrime(128, new Random());
        this.q = BigInteger.probablePrime(128, new Random());

        this.n = p.multiply(q);

        var phi = (p.min(BigInteger.ONE)).multiply(q.min(BigInteger.ONE));

        e = BigInteger.probablePrime(128, new Random());

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
        String temp = "";
        for (byte b : cipher)
        {
            temp += Byte.toString(b);
        }
        return temp;
    }
    public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
        return message.modPow(e, n);
    }

    public static BigInteger decrypt(BigInteger message, BigInteger d, BigInteger n) {
        return message.modPow(d, n);
    }

    static BigInteger gcd(BigInteger e, BigInteger z) {
        if (e.equals(BigInteger.ZERO))
            return z;
        else
            return gcd(z.mod(e), e);
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
