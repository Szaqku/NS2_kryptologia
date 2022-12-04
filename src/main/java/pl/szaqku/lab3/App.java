package pl.szaqku.lab3;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class App {
    /**
     * Benchmark                  (pByteLength)  (m)  Mode  Cnt  Score   Error  Units
     * L3BenchmarkTest.benchmark           128         2  avgt    3  0.116 ± 0.079   s/op
     * L3BenchmarkTest.benchmark           128        16  avgt    3  0.120 ± 0.086   s/op
     * L3BenchmarkTest.benchmark           128        64  avgt    3  0.121 ± 0.043   s/op
     * L3BenchmarkTest.benchmark           512         2  avgt    3  0.719 ± 0.117   s/op
     * L3BenchmarkTest.benchmark           512        16  avgt    3  1.479 ± 1.169   s/op
     * L3BenchmarkTest.benchmark           512        64  avgt    3  1.258 ± 1.566   s/op
     * L3BenchmarkTest.benchmark          1024         2  avgt    3  2.158 ± 4.245   s/op
     * L3BenchmarkTest.benchmark          1024        16  avgt    3  2.082 ± 0.379   s/op
     * L3BenchmarkTest.benchmark          1024        64  avgt    3  2.120 ± 0.534   s/op
     *
     * a ) Fakt że algorytm jest uznawany za teorio-inforamcyjnie, co sprawia iż przeciwnik dysponujący nieograniczoną mocą obliczeniową nie jest w stanie zobyć sekretu bez spełnienia warunków rekonstrukcji sekretu.
     * b ) Tak, do pewnego stopnia. Natomiast trzeba mieć na uwadzę że atak brutalny pozornie wymaga sprawdzenia p^m wariantów. W rzeczywistości wiedząc, że M < p wystarczy ~= p/2 prób.
     **/

    public static void main(String[] args) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

    }
}
