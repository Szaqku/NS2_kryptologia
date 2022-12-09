package pl.szaqku.lab4;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public void testGeff() {

    }

    public void testPokerTest() {
        var generators = Set.of(
            GeffGenerator.class,
            StopAndGoGenerator.class,
            ShrinkingGenerator.class
        );
        var tests = Set.of(
            new PokerTest()
        );

        for (var test : tests) {
            for (var generatorClass : generators) {
                var result = test.test(GeneratorStrategy.generate(2000, generatorClass));
                System.out.println(generatorClass.getName()+": "+result);
            }
        }
    }

}