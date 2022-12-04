package pl.szaqku.lab4;


//import lombok.extern.slf4j.Slf4j;

import static pl.szaqku.lab4.Helper.fromBoolean;
import static pl.szaqku.lab4.Helper.toBoolean;

//@Slf4j
public class GeffGenerator implements BitGenerator {

    private final LFSR lfsr1;
    private final LFSR lfsr2;
    private final LFSR lfsr3;

    private char lastBit;

    public GeffGenerator(LFSR lfsr1, LFSR lfsr2, LFSR lfsr3) {
        this.lfsr1 = lfsr1;
        this.lfsr2 = lfsr2;
        this.lfsr3 = lfsr3;
    }
    @Override
    public char nextBit() {
        var x1 = lfsr1.nextBit();
        var x2 = lfsr2.nextBit();
        var x3 = lfsr3.nextBit();

        var a1 = Boolean.logicalAnd(toBoolean(x1), toBoolean(x2));
        var n1 = !toBoolean(x2);
        var a2 = Boolean.logicalAnd(n1, toBoolean(x3));

        lastBit = fromBoolean(Boolean.logicalXor(a1, a2));
        return lastBit;
    }

    @Override
    public char lastBit() {
        return lastBit;
    }

}
