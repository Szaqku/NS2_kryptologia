package pl.szaqku.lab4.test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class RunsTestTest extends TestCase {

    public void testGetRunsFrequency() {
        var string =
            "1" + "0" + "1" + "0" + "1" + "0" +
            "11" + "00" +
            "111" + "000" +
            "11111111111";

        var result = RunsTest.getRunsFrequency(string);

        Assert.assertEquals("There are 4 unique lengths of substrings",4, result.size());
        Assert.assertEquals("Single 1 or 0 bit occurred 6 times in total",6, result.get(1).intValue());
        Assert.assertEquals("Double 1 or 0 bit occurred 2 times in total",2, result.get(2).intValue());
        Assert.assertEquals("Triple 1 or 0 bit occurred 2 times in total",2, result.get(3).intValue());
        Assert.assertEquals("Triple 1 or 0 bit occurred 2 times in total",1, result.get(11).intValue());
    }
}