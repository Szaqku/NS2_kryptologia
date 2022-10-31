package pl.szaqku.lab2;

import java.math.BigInteger;
import java.util.Arrays;

public class NumberUtil {

    public static BigInteger getPrime(int lowerBound, int upperBound, BigInteger... skipList) {
        assert lowerBound > 1;
        assert upperBound < Integer.MAX_VALUE;

        for (int i = lowerBound; i <= upperBound; i++) {
            if (isPrime(i) == 1 && !Arrays.asList(skipList).contains(BigInteger.valueOf(i))) {
                return BigInteger.valueOf(i);
            }
        }
        throw new IllegalStateException();
    }
    public static int isPrime(int n) {
        // for loop from 2 to n
        for (int i = 2; i <= Math.sqrt(n); i++) {
            // if the number is divisible return -1
            if (n % i == 0) {
                return -1;
            }
        }
        // return 1 if number is prime
        return 1;
    }
}
