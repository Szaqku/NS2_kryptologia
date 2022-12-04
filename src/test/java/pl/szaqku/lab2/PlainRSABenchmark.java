package pl.szaqku.lab2;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;
import pl.szaqku.lab1.AesCipher;
import pl.szaqku.lab1.AesUtil;
import pl.szaqku.lab1.OraclePaddingAttack;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

//@State(Scope.Benchmark)
//@Threads(1)
public class PlainRSABenchmark extends TestCase {
//
//    @Param({"2048", "3072", "4096", "7680"})
//    Integer byteLength;
//
//    @Benchmark
//    @BenchmarkMode(Mode.AverageTime)
//    @OutputTimeUnit(TimeUnit.SECONDS)
//    @Fork(warmups = 0, value = 1)
//    @Warmup(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
//    @Measurement(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 3)
//    public void benchmarkRSA() {
//        var plain = "Testowy string";
//        var rsaInstance = new PlainRSA(byteLength);
//        var encrypted = rsaInstance.encrypt(plain);
//        var decrypted = rsaInstance.decrypt(encrypted);
//        Assert.assertEquals(plain, decrypted);
//    }
//
//    public void testRunBenchmark() throws RunnerException, IOException {
//        org.openjdk.jmh.Main.main(new String[]{});
//    }
}
