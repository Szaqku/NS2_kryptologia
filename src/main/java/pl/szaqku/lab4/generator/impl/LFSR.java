package pl.szaqku.lab4.generator.impl;


//import lombok.extern.slf4j.Slf4j;

import pl.szaqku.lab4.generator.BitGenerator;

import java.security.SecureRandom;
import java.util.stream.Collectors;

import static pl.szaqku.lab4.Helper.fromBoolean;
import static pl.szaqku.lab4.Helper.toBoolean;

//@Slf4j
public class LFSR implements BitGenerator {

    private final String randomPattern;
    private String register;

    public LFSR(String register, String randomPattern) {
        this.register = register;
        this.randomPattern = randomPattern;
    }

    public LFSR(String register) {
        this.register = register;
        this.randomPattern = generateRandomNLengthValue(register.length());
    }

    public LFSR(int n) {
        this.register = generateRandomNLengthValue(n);
        this.randomPattern = generateRandomNLengthValue(n);
    }

    private static String generateRandomNLengthValue(int n) {
        return new SecureRandom().ints(n, 0, 2)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return "LFSR{" +
                "randomPattern=" + randomPattern +
                ", register=" + register +
                '}';
    }

    public char nextBit() {
        var firstBitIndex = getFirstBitIndexToUse();
        var result = toBoolean(register.charAt(firstBitIndex));
        for (int i = firstBitIndex + 1 ; i < register.length() ; i++ ) {
            if (randomPattern.charAt(i) == '1') {
//                log.debug("Taking {} bit: '{}'", i, randomPattern.charAt(i));
                result = Boolean.logicalXor(result, toBoolean(register.charAt(i)));
            }
        }

//        System.out.println("PRE : "+register+" p: ("+randomPattern+")");
        shiftRegisterAndAddNewBit(result);
//        System.out.println("POST: "+register);

        return fromBoolean(result);
    }

    @Override
    public char lastBit() {
        return register.charAt(register.length()-1);
    }

    private void shiftRegisterAndAddNewBit(boolean result) {
        register = register.substring(1) + fromBoolean(result);
    }

    private int getFirstBitIndexToUse() {
        for (int i = 0 ; i < register.length() ; i++ ) {
            if (randomPattern.charAt(i) == '1') {
                return i;
            }
        }

        // will never happen
        assert false;
        return register.charAt(0);
    }

    public String getRandomPattern() {
        return randomPattern;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }
}
