package pl.szaqku.lab3;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static pl.szaqku.lab3.Tests.*;

@State(Scope.Benchmark)
@Threads(1)
public class L3BenchmarkTest extends TestCase {

    @Param({"128", "512", "1024"})
    Integer pLength;

    @Param({"2", "16", "64"})
    Integer m;

    @org.openjdk.jmh.annotations.Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(warmups = 0, value = 1)
    @Warmup(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
    @Measurement(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 3)
    public void benchmark() {
        BigInteger M = BigInteger.probablePrime(pLength, new Random());
        BigInteger p = BigInteger.probablePrime(M.bitLength()+1, new Random());
        BigInteger n = new BigInteger("128");
        BigInteger m = new BigInteger(String.valueOf(this.m));

        List<BigInteger> aVector = getRandomVectorA(m);

//        System.out.println("Secret: "+ M.toString());
//        System.out.println("A vector:");
//        for (int i = 0; i < aVector.size(); i++) {
//            System.out.println(i+", "+aVector.get(i).toString());
//        }

        List<BigInteger> shares = calculateShares(M, p, n, aVector);
//        System.out.println("Shares:");

        Set<AbstractMap.SimpleEntry<BigInteger, BigInteger>> mVec = new HashSet<>();
        for (int i = 0; i < shares.size(); i++) {
//            System.out.println(i+", "+shares.get(i).toString());
            mVec.add(new AbstractMap.SimpleEntry<>(new BigInteger(String.valueOf((i+1))), shares.get(i)));
        }

        for (int i = 0; i < m.intValue(); i++) {
            mVec.add(new AbstractMap.SimpleEntry<>(new BigInteger(String.valueOf((i+1))), shares.get(i)));
        }

        var x = new BigInteger("0");
        BigInteger Wx = calculateW(p, mVec, x);

//        System.out.println("Secret:");
//        System.out.println(Wx);

        Assert.assertEquals(M, Wx);
    }

    public void testRunBenchmark() throws RunnerException, IOException {
        org.openjdk.jmh.Main.main(new String[]{});
    }
}
