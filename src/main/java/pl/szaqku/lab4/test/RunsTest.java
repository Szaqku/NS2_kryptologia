package pl.szaqku.lab4.test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RunsTest implements Predicate<String> {

    public record Range(int rangeStart, int rangeEnd) {}

    public static Map<Integer, Range> allowedRanges = Map.of(
        1, new Range(2315, 2685),
        2, new Range(1114, 1386),
        3, new Range(527, 723),
        4, new Range(240, 384),
        5, new Range(103, 209),
        6, new Range(103, 209)
    );

    public static Range defaultRange = allowedRanges.get(6);

    public boolean test(String input) {
        Map<Integer, Long> freq = getRunsFrequency(input);

        for (var lengthFreqPair : freq.entrySet()) {
            var range = allowedRanges.getOrDefault(lengthFreqPair.getKey(), defaultRange);
            if (!(range.rangeStart < lengthFreqPair.getValue() && lengthFreqPair.getValue() < range.rangeEnd)) {
                return false;
            }
        }

        return true;
    }

    public static Map<Integer, Long> getRunsFrequency(String input) {
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
        subsetsLength.add(run);

        return subsetsLength
            .stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
