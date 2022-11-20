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
     * <p>
     * Zadanie 3.2
     * a. Wiadomość o jakiej maksymalnie długości można podpisać za pomocą tego algorytmu?
     * RSA is only able to encrypt data to a maximum amount equal to your key size (2048 bits = 256 bytes),
     * minus any padding and header data (11 bytes for PKCS#1 v1.5 padding).
     * <p>
     * https://mbed-tls.readthedocs.io/en/latest/kb/cryptography/rsa-encryption-maximum-data-size/
     * b. Sprawdź czas wykonania operacji podpisywania i weryfikacji podpisu dla różnych wartości modułu, tj. dla 2048, 3072, 4096, 7680 bitów.
     * 2048 -   1.167s
     * 3072 -   4.047s
     * 4096 -  11,572s
     * 7680 - 126,263s
     * <p>
     * PlainRSABenchmark.java
     * c. Umieść w pliku z odpowiedziami przykładową liczbę składającą się z 4096 bitów, jak duża jest to liczba?
     * 1014364510935882129105374612697006526272360583881973820439034765113909660504570335035530284256718846589138979744442
     * 3603980097756270309436499369487103470742668746950877534829050925393743728163543497427700184994153223491349976828269
     * 7391441490085454175561320196571721463898855171962821455509829300158307882379090817953414613054722842439853627167558
     * 1691454394796897673228992822107616411049231179372693740280247873456352152161405029870045649518486132794721066607472
     * 0525668570911533687569959869475561076715200003973012157653216930850770327126843964828368341430748812579712376551919
     * 5753146809593725618597301087053080281221862613780465673213429321310043554438939340763761962172424597821290281495348
     * 5880971057512295489289175563238424765582318754554351396952452093137126089343701771946058125752562367950987668905564
     * 6952849538953926333301918379138686736476516129283018044536477382276337847916196339094640157851010118446795842395524
     * 9835558934172061407696483136466890883735875492837643355229610520871750134891195741209865178433599100619701150916549
     * 3232559896907275090894822406565263146869341572621679765706764912973511283305526619703671705508832220788403928073035
     * 505468850931280429365758062255823700595809977963719019467561344505204240199429859569
     * <p>
     * d. Jak ustala się wartość klucza publicznego?
     * Wartość stanowi para liczb: {e,n}
     * <p>
     * Zadanie 4.2
     * a. wydłuża działanie całego procesu
     * b. tak
     * Zadanie 4.3
     * a. System jest bezpieczny, jeśli atakujący nie jest w stanie
     * wygenerować nowej (innej, ot tych które przejął)
     * wiadomości m’, którą można prawidłowo zweryfikować
     * systemem sprawdzania podpisu elektronicznego
     * c. e musi spełniać warunek (1 < e < φ(n)) i musi być liczbą względnie pierwszą z φ(n).
     * d. 256
     * e. stała funkcja hashująca
     * f. w celu odtwozrenia klucza prywatnego
     * g. padding Attack, factorization Attack
     * h. Może
     */

    public static void main(String[] args) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

    }
}