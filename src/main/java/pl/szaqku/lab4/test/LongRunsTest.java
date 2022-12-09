package pl.szaqku.lab4.test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class LongRunsTest implements Predicate<String> {

    public boolean test(String input) {
        int longestRun = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i - 1) == input.charAt(i)) {
                longestRun++;
                if (longestRun > 26)
                    return false;
            } else {
                longestRun = 1;
            }
        }

        return true;
    }
}
