package pl.szaqku.lab3;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ExerciseTests extends TestCase {

    public void testEx51() {
        BigInteger M = new BigInteger("11");
        BigInteger p = new BigInteger("13");
        BigInteger n = new BigInteger("5");
        BigInteger m = new BigInteger("3");

        List<BigInteger> aVector = List.of(
            new BigInteger("8"),
            new BigInteger("7")
        );

        List<BigInteger> shares = new ArrayList<>(aVector.size());
        for (int xi = 1; xi <= n.intValue(); xi++) {
            shares.add(calculateW(BigInteger.valueOf(xi), aVector, p, M));
        }

        List<BigInteger> expectedShares = List.of(
                new BigInteger("0"),
                new BigInteger("3"),
                new BigInteger("7"),
                new BigInteger("12"),
                new BigInteger("5")
        );

        Assert.assertEquals(expectedShares, shares);

        var mVec = List.of(
            expectedShares.get(1), // m2
            expectedShares.get(2), // m3
            expectedShares.get(4)  // m5
        );

        var xVec = IntStream.rangeClosed(1, n.intValue()).mapToObj(BigInteger::valueOf).toList();
        var Wx = BigInteger.ZERO;
        var x = new BigInteger("0");

        for (int s = 0; s < mVec.size(); s++) {
            var mis = mVec.get(s);
            BigInteger inner = BigInteger.ONE;
            for (int j = 0; j < s; j++) {
                var xij = xVec.get(j);
                var xis = xVec.get(s);
                var innerTop = x.add(xij.negate());
                var innerBottom = xis.add(xij.negate());
                inner = inner.multiply(
                    innerTop.divide(innerBottom).mod(p)
                );
            }
            Wx = mis.multiply(inner);
        }
    }

    private static BigInteger calculateW(BigInteger x, List<BigInteger> aVector, BigInteger p, BigInteger M) {
        BigInteger out = BigInteger.ZERO;
        for (int pow = aVector.size(); pow > 0; pow--) {
            var shareIndex = pow - 1;
            var a = aVector.get(shareIndex);
            out = out.add( a.multiply( x.pow(pow) ) );
        }
        out = out.add(M);
        return out.mod(p);
    }

    //        List<BigInteger> expectedShares = List.of(
    //                new BigInteger("20"),
    //                new BigInteger("10"),
    //                new BigInteger("26"),
    //                new BigInteger("27"),
    //                new BigInteger("6"),
    //                new BigInteger("21"),
    //                new BigInteger("9"),
    //                new BigInteger("3")
    //        );
}