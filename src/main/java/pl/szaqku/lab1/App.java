package pl.szaqku.lab1;

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
import java.util.HexFormat;

public class App {
    /**
     * Zad 4
     * a) Jaki jest czas wykonania ataku dla szyfrogramu składającego się z 5 bloków? Podaj również dane techniczne komputera na którym przeprowadzono test. Test wykonaj 3-krotnie i podaj uśrednione wyniki.
     * OS: Microsoft Windows 10 Professional
     * RAM: 2x8 DDR4 3600MHz C17
     * CPU: AMD Ryzen 5 3600 6-Core 3.59 GHz
     * średnia: 0,122s
     * benchmark: src/test/java/pl/szaqku/lab1/OraclePaddingAttackBenchmark.java
     *
     * b) Kiedy możliwy jest odczyt również pierwszego bloku?
     * Kiedy znamy wektor startowy.
     * Lab 01-02 v1_6 - CBC Padding Oracle Attack Demo.pdf
     *
     * c) Jaki błąd przy implementacji należy popełnić, aby atak był możliwy?
     * Np. nie maskować w żaden sposób błędów wewnętrznych aplikacji
     * Lab 01-02 v1_6 - CBC Padding Oracle Attack Demo.pdf
     *
     * d) W jakich środowiskach zaimplementowano ten atak? (wymień przynajmniej 3)
     * Internet browsers, websites, web frameworks
     * https://knowledge-base.secureflag.com/vulnerabilities/broken_cryptography/padding_oracle_vulnerability.html#impact
     *
     * e) Czy atak działa tylko dla algorytmu AES? Odpowiedź uzasadnij.
     * Jeżeli w aplikacji będziemy w stanie wpływać na dane,
     * które będą deszyfrowane oraz aplikacja w wyraźny sposób zasygnalizuje błąd paddingu,
     * będziemy w stanie przeprowadzić atak Padding Oracle.
     * https://sekurak.pl/czym-jest-padding-oracle-atak-i-ochrona/
     *
     * f) Ile razy maksymalnie należy odpytać wyrocznię w celu odczytania jednego bloku?
     * 16x256
     * Lab 01-02 v1_6 - CBC Padding Oracle Attack Demo.pdf
     *
     * g) Czy w przypadku zastosowania innych schematów padding’u atak będzie działał? Odpowiedź uzasadnij.
     * Tak, np OAEP też może być podatny na ten atak.
     * "[...]. Padding oracle attacks are mostly associated with CBC mode decryption used within block ciphers.
     *  Padding modes for asymmetric algorithms such as OAEP may also be vulnerable to padding oracle attacks. [...]"
     * https://en.wikipedia.org/wiki/Padding_oracle_attack
     */

    public static void main(String[] args) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        System.out.println("Hello there, what would you like to cipher Sir ?");
        var input = System.in.readAllBytes();
        System.out.println("Plain:"+HexFormat.of().withUpperCase().formatHex(input));

        var cipher = AesUtil.getEncryptedText(new String(input));
        System.out.println("Cipher: "+HexFormat.of().withUpperCase().formatHex(cipher));

        var attack = new OraclePaddingAttack(new AesCipher(cipher, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES));
        attack.decipher();
        System.out.println(attack.getPlainText());
    }
}
