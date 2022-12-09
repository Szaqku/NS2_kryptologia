package pl.szaqku.lab4.test;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PokerTest implements Predicate<String> {

    public boolean test(String input) {
        assert input.length() % 4 == 0;

        var packFreq = IntStream.range(0, input.length() / 4)
            .mapToObj(i -> input.substring(i * 4, (i * 4) + 4))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        ;
        var fiSum = packFreq.values()
            .stream().reduce(0L, (sum, f1) -> sum + (int) Math.pow(f1.intValue(), 2));

        var X = ((16 / 5000.0) * (fiSum)) - 5000;

        return 2.16d < X && X < 46.17d;
    }
}
