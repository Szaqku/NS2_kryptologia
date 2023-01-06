package pl.szaqku.lab4;

import pl.szaqku.lab4.generator.*;
import pl.szaqku.lab4.generator.impl.GeffGenerator;
import pl.szaqku.lab4.generator.impl.LFSR;
import pl.szaqku.lab4.generator.impl.ShrinkingGenerator;
import pl.szaqku.lab4.generator.impl.StopAndGoGenerator;

public class GeneratorStrategy {

    public static <T extends BitGenerator> String generate(int lsfrLength, int bitsToGenerate, Class<T> clazz) {
        if (clazz.equals(GeffGenerator.class)) {
            LFSR lfsr1 = new LFSR(lsfrLength);
            LFSR lfsr2 = new LFSR(lsfrLength);
            LFSR lfsr3 = new LFSR(lsfrLength);
            return new GeffGenerator(lfsr1,lfsr2,lfsr3).nextBits(bitsToGenerate);
        } else if (clazz.equals(StopAndGoGenerator.class)) {
            LFSR lfsr1 = new LFSR(lsfrLength);
            LFSR lfsr2 = new LFSR(lsfrLength);
            LFSR lfsr3 = new LFSR(lsfrLength);
            return new StopAndGoGenerator(lfsr1,lfsr2,lfsr3).nextBits(bitsToGenerate);
        } else if (clazz.equals(ShrinkingGenerator.class)) {
            LFSR lfsr1 = new LFSR(lsfrLength);
            LFSR lfsr2 = new LFSR(lsfrLength);
            return new ShrinkingGenerator(lfsr1,lfsr2).nextBits(bitsToGenerate);
        } else {
            throw new RuntimeException("Illegal class exception");
        }
    }
}
