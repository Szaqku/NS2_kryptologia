package pl.szaqku.lab2;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.math.BigInteger;
import java.util.Random;

public class ExerciseUtils extends TestCase {

    public void testGetPrime() {
        System.out.println(BigInteger.probablePrime(4096, new Random()));
    }

}