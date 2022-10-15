package pl.szaqku.lab1;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Threads(1)
public class OraclePaddingAttackBenchmark extends TestCase {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(warmups = 0, value = 1)
    @Warmup(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 1)
    @Measurement(time = 1, timeUnit = TimeUnit.SECONDS, iterations = 3)
    public void checkOraclePaddingAttack() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ac mi a dolor.";
        var aesCipherText = AesUtil.getEncryptedText(text);
        var attack = new OraclePaddingAttack(new AesCipher(aesCipherText, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES));
        attack.decipher();
    }

    public void testRunBenchmark() throws RunnerException, IOException {
        org.openjdk.jmh.Main.main(new String[]{});
    }
}
