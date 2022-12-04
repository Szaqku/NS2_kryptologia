package pl.szaqku.lab3;

import junit.framework.Assert;
import junit.framework.TestCase;
import java.math.BigInteger;
import java.util.*;

public class Tests extends TestCase {
    public void testEx6_1() {
        BigInteger M = BigInteger.probablePrime(256, new Random());
        BigInteger p = BigInteger.probablePrime(257, new Random());
        BigInteger n = new BigInteger("8");
        BigInteger m = new BigInteger("2");

        List<BigInteger> aVector = getRandomVectorA(m);

        System.out.println("Secret: "+ M.toString());
        System.out.println("A vector:");
        for (int i = 0; i < aVector.size(); i++) {
            System.out.println(i+", "+aVector.get(i).toString());
        }

        List<BigInteger> shares = calculateShares(M, p, n, aVector);
        System.out.println("Shares:");

        Set<AbstractMap.SimpleEntry<BigInteger, BigInteger>> mVec = new HashSet<>();
        for (int i = 0; i < shares.size(); i++) {
            System.out.println(i+", "+shares.get(i).toString());
            mVec.add(new AbstractMap.SimpleEntry<>(new BigInteger(String.valueOf((i+1))), shares.get(i)));
        }

        for (int i = 0; i < m.intValue(); i++) {
            mVec.add(new AbstractMap.SimpleEntry<>(new BigInteger(String.valueOf((i+1))), shares.get(i)));
        }

        var x = new BigInteger("0");
        BigInteger Wx = calculateW(p, mVec, x);

        System.out.println("Secret:");
        System.out.println(Wx);

        Assert.assertEquals(M, Wx);
    }
    public void testEx5_2() {
        BigInteger M = new BigInteger("11");
        BigInteger p = new BigInteger("23");
        BigInteger n = new BigInteger("20");
        BigInteger m = new BigInteger("4");
        System.out.println("Secret: "+ M.toString());
        List<BigInteger> aVector = getRandomVectorA(m);
        System.out.println("A vector:");
        for (int i = 0; i < aVector.size(); i++) {
            System.out.println(i+", "+aVector.get(i).toString());
        }
        List<BigInteger> shares = calculateShares(M, p, n, aVector);
        System.out.println("Shares:");
        for (int i = 0; i < shares.size(); i++) {
            System.out.println(i+", "+shares.get(i).toString());
        }
        var mVec = Set.of(
                new AbstractMap.SimpleEntry<>(new BigInteger("2"), shares.get(1)),
                new AbstractMap.SimpleEntry<>(new BigInteger("3"), shares.get(2)),
                new AbstractMap.SimpleEntry<>(new BigInteger("5"), shares.get(4)),
                new AbstractMap.SimpleEntry<>(new BigInteger("11"), shares.get(10))
        );
        var x = new BigInteger("0");
        BigInteger Wx = calculateW(p, mVec, x);
        System.out.println("Secret:");
        System.out.println(Wx);
        Assert.assertEquals(M, Wx);
    }
    public void testEx5_1() {
        BigInteger M = new BigInteger("11");
        BigInteger p = new BigInteger("13");
        BigInteger n = new BigInteger("5");
        BigInteger m = new BigInteger("3");
        System.out.println("Secret: "+ M.toString());
        List<BigInteger> aVector = getRandomVectorA(m);
//        List<BigInteger> aVector = List.of(
//        new BigInteger("8"),
//                new BigInteger("7")
//        );
        List<BigInteger> shares = calculateShares(M, p, n, aVector);
        List<BigInteger> expectedShares = List.of(
                new BigInteger("0"),
                new BigInteger("3"),
                new BigInteger("7"),
                new BigInteger("12"),
                new BigInteger("5")
        );
        Assert.assertEquals(expectedShares, shares);
        var mVec = Set.of(
                new AbstractMap.SimpleEntry<>(new BigInteger("2"), shares.get(1)),
                new AbstractMap.SimpleEntry<>(new BigInteger("3"), shares.get(2)),
                new AbstractMap.SimpleEntry<>(new BigInteger("5"), shares.get(4))
        );
        var x = new BigInteger("0");
        BigInteger Wx = calculateW(p, mVec, x);
        System.out.println(Wx);
        Assert.assertEquals(M, Wx);
    }
    public static List<BigInteger> getRandomVectorA(BigInteger m) {
        var random = new Random();
        return random.ints(m.intValue() - 1, 0, Integer.MAX_VALUE)
                .mapToObj(BigInteger::valueOf)
                .toList();
    }
    public static List<BigInteger> calculateShares(BigInteger M, BigInteger p, BigInteger n, List<BigInteger> aVector) {
        List<BigInteger> shares = new ArrayList<>(aVector.size());
        for (int xi = 1; xi <= n.intValue(); xi++) {
            shares.add(calculateW(BigInteger.valueOf(xi), aVector, p, M));
        }
        return shares;
    }
    public static BigInteger calculateW(BigInteger p, Set<AbstractMap.SimpleEntry<BigInteger, BigInteger>> mVec, BigInteger x) {
        var Wx = BigInteger.ZERO;
        for (var share : mVec) {
            var mi = share.getValue();
            var xi = share.getKey();
            var otherShares = new HashSet<>(mVec);
            otherShares.remove(share);
            BigInteger inner = BigInteger.ONE;
            for (var otherShare : otherShares) {
                var xij = otherShare.getKey();
                var numerator = x.add(xij.negate());
                var denominator = xi.add(xij.negate());
                inner = inner.multiply(
                        numerator.multiply(
                                denominator.modInverse(p).mod(p)
                        )
                );
            }
            Wx = Wx.add(mi.multiply(inner));
        }
        Wx = Wx.mod(p);
        return Wx;
    }
    public static BigInteger calculateW(BigInteger x, List<BigInteger> aVector, BigInteger p, BigInteger M) {
        BigInteger out = BigInteger.ZERO;
        for (int pow = aVector.size(); pow > 0; pow--) {
            var shareIndex = pow - 1;
            var a = aVector.get(shareIndex);
            out = out.add( a.multiply( x.pow(pow) ) );
        }
        out = out.add(M);
        return out.mod(p);
    }
}