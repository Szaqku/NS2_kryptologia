package pl.szaqku.lab2;

import java.math.BigInteger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class PlainRSA {

    private BigInteger d;
    private BigInteger n;
    private BigInteger p;
    private BigInteger q;
    private BigInteger e;

    public PlainRSA(int byteLength) {
        Random random = new Random();
        this.p = BigInteger.probablePrime(byteLength, random);
        this.q = BigInteger.probablePrime(byteLength, random);

        this.n = p.multiply(q);

        var phi = (p.add(BigInteger.valueOf(-1))).multiply(q.add(BigInteger.valueOf(-1)));

        e = BigInteger.probablePrime(byteLength, random);

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
    public boolean verify(String message, byte[] signature) {
        return Arrays.equals(encrypt(message), signature);
    }

    public boolean verify(BigInteger message, byte[] signature) {
        return Arrays.equals(encrypt(message, e, n).toByteArray(), signature);
    }

    public byte[] sign(String message) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
        return new BigInteger(encodedhash).modPow(d, n).toByteArray();
    }

    public boolean verifySignature(String message, byte[] sig) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
        var hashFromSig = new BigInteger(sig).modPow(e, n);

        return new BigInteger(hash).equals(hashFromSig);
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

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }
}
