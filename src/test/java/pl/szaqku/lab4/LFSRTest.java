package pl.szaqku.lab4;

import junit.framework.Assert;
import junit.framework.TestCase;

public class LFSRTest extends TestCase {

    public void testNextBit() {
        var lfsr = new LFSR("111", "101");

        Assert.assertEquals('0', lfsr.nextBit());
        Assert.assertEquals( "110", lfsr.getRegister());

        Assert.assertEquals('1', lfsr.nextBit());
        Assert.assertEquals( "101", lfsr.getRegister());

        Assert.assertEquals('0', lfsr.nextBit());
        Assert.assertEquals( "010", lfsr.getRegister());

        Assert.assertEquals('0', lfsr.nextBit());
        Assert.assertEquals( "100", lfsr.getRegister());

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
}