package pl.szaqku.lab4;

import static pl.szaqku.lab4.Helper.fromBoolean;
import static pl.szaqku.lab4.Helper.toBoolean;

public class StopAndGoGenerator implements BitGenerator {

    private final LFSR lfsr1;
    private final LFSR lfsr2;
    private final LFSR lfsr3;

    private char lastBit;
    private final boolean clockHigh = true;

    public StopAndGoGenerator(LFSR lfsr1, LFSR lfsr2, LFSR lfsr3) {
        this.lfsr1 = lfsr1;
        this.lfsr2 = lfsr2;
        this.lfsr3 = lfsr3;
    }
    @Override
    public char nextBit() {
        var x1 = lfsr1.nextBit();
        var n1 = !toBoolean(x1);

        var a1 = Boolean.logicalAnd(clockHigh, toBoolean(x1));
        var a2 = Boolean.logicalAnd(n1, clockHigh);

        var x2 = a1 ? lfsr2.nextBit() : lfsr2.lastBit();
        var x3 = a2 ? lfsr3.nextBit() : lfsr3.lastBit();

        lastBit = fromBoolean(Boolean.logicalXor(toBoolean(x2), toBoolean(x3)));
        return lastBit;
    }

    @Override
    public char lastBit() {
        return lastBit;
    }

}
