package pl.szaqku.lab4;

import pl.szaqku.lab4.generator.*;
import pl.szaqku.lab4.generator.impl.GeffGenerator;
import pl.szaqku.lab4.generator.impl.LFSR;
import pl.szaqku.lab4.generator.impl.ShrinkingGenerator;
import pl.szaqku.lab4.generator.impl.StopAndGoGenerator;

public class GeneratorStrategy {

    public static <T extends BitGenerator> String generate(int length, Class<T> clazz) {
        if (clazz.equals(GeffGenerator.class)) {
            LFSR lfsr1 = new LFSR(length);
            LFSR lfsr2 = new LFSR(length);
            LFSR lfsr3 = new LFSR(length);
            return new GeffGenerator(lfsr1,lfsr2,lfsr3).nextBits(length);
        } else if (clazz.equals(StopAndGoGenerator.class)) {
            LFSR lfsr1 = new LFSR(length);
            LFSR lfsr2 = new LFSR(length);
            LFSR lfsr3 = new LFSR(length);
            return new StopAndGoGenerator(lfsr1,lfsr2,lfsr3).nextBits(length);
        } else if (clazz.equals(ShrinkingGenerator.class)) {
            LFSR lfsr1 = new LFSR(length);
            LFSR lfsr2 = new LFSR(length);
            return new ShrinkingGenerator(lfsr1,lfsr2).nextBits(length);
        } else {
            throw new RuntimeException("Illegal class exception");
        }
    }
}
