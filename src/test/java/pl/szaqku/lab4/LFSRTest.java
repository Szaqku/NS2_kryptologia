package pl.szaqku.lab4;

import junit.framework.Assert;
import junit.framework.TestCase;
import pl.szaqku.lab4.generator.impl.GeffGenerator;
import pl.szaqku.lab4.generator.impl.LFSR;
import pl.szaqku.lab4.generator.impl.ShrinkingGenerator;
import pl.szaqku.lab4.generator.impl.StopAndGoGenerator;
import pl.szaqku.lab4.test.LongRunsTest;
import pl.szaqku.lab4.test.PokerTest;

import java.util.Set;

public class LFSRTest extends TestCase {

    public void testNextBit() {
        var lfsr = new LFSR("111", "101");

        Assert.assertEquals('0', lfsr.nextBit());
        Assert.assertEquals("110", lfsr.getRegister());

        Assert.assertEquals('1', lfsr.nextBit());
        Assert.assertEquals("101", lfsr.getRegister());

        Assert.assertEquals('0', lfsr.nextBit());
        Assert.assertEquals("010", lfsr.getRegister());

        Assert.assertEquals('0', lfsr.nextBit());
        Assert.assertEquals("100", lfsr.getRegister());

        Assert.assertEquals('1', lfsr.nextBit());
        Assert.assertEquals("001", lfsr.getRegister());

        Assert.assertEquals('1', lfsr.nextBit());
        Assert.assertEquals("011", lfsr.getRegister());

        Assert.assertEquals('1', lfsr.nextBit());
        Assert.assertEquals("111", lfsr.getRegister());
    }

    public void testNextBits() {
        var lfsr = new LFSR("111", "101");
        lfsr.nextBits(7);
        Assert.assertEquals(lfsr.getRegister(), "111");
    }

    public void testRunTest() {
        var generators = Set.of(
            GeffGenerator.class,
            StopAndGoGenerator.class,
            ShrinkingGenerator.class
        );
        var tests = Set.of(
            new PokerTest(),
            new LongRunsTest(),
            new PokerTest()
        );

        for (var test : tests) {
            System.out.println(test.getClass().getSimpleName());
            for (var generatorClass : generators) {
                var result = test.test(GeneratorStrategy.generate(20000, generatorClass));
                System.out.println(generatorClass.getSimpleName()+": "+result);
                assertTrue(result);
            }
        }
    }

}