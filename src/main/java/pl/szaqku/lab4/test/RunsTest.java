package pl.szaqku.lab4.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RunsTest implements Predicate<String> {

    public boolean test(String input) {
        List<Integer> subsetsLength = new ArrayList<>();
        int run = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i - 1) == input.charAt(i)) {
                run++;
            } else {
                subsetsLength.add(run);
                run = 1;
            }
        }
        var freq = subsetsLength
            .stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (var lengthFreqPair : freq.entrySet()) {
            if (lengthFreqPair.getKey() == 1 && !(2315 < lengthFreqPair.getValue() && lengthFreqPair.getValue() < 2685)) {
                return false;
            }
            if (lengthFreqPair.getKey() == 2 && !(1114 < lengthFreqPair.getValue() && lengthFreqPair.getValue() < 1386)){
                return false;
            }
            if (lengthFreqPair.getKey() == 3 && !(527 < lengthFreqPair.getValue() && lengthFreqPair.getValue() < 723)){
                return false;
            }
            if (lengthFreqPair.getKey() == 4 && !(240 < lengthFreqPair.getValue() && lengthFreqPair.getValue() < 384)){
                return false;
            }
            if (lengthFreqPair.getKey() == 5 && !(103 < lengthFreqPair.getValue() && lengthFreqPair.getValue() < 209)){
                return false;
            }
            if (lengthFreqPair.getKey() >= 6 && !(103 < lengthFreqPair.getValue() && lengthFreqPair.getValue() < 209)){
                return false;
            }
        }

        return true;
    }
}
