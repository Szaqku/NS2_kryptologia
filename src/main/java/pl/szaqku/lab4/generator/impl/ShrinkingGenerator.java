package pl.szaqku.lab4.generator.impl;

import pl.szaqku.lab4.generator.BitGenerator;

import static pl.szaqku.lab4.Helper.toBoolean;

public class ShrinkingGenerator implements BitGenerator {
    private final LFSR lfsr1;
    private final LFSR lfsr2;

    private char lastBit;

    public ShrinkingGenerator(LFSR lfsr1, LFSR lfsr2) {
        this.lfsr1 = lfsr1;
        this.lfsr2 = lfsr2;
    }

    @Override
    public char nextBit() {
        var x1 = lfsr1.nextBit();
        var x2 = lfsr2.nextBit();

        if (toBoolean(x1)) {
            lastBit = x2;
            return lastBit;
        }

        return nextBit();
    }

    @Override
    public char lastBit() {
        return lastBit;
    }
}
