package pl.szaqku.lab4.generator;

public interface BitGenerator {
    public char nextBit();

    public default String nextBits(int n) {
        var register = new StringBuilder();
        for (int i = 0; i < n; i++) {
            register.append(nextBit());
        }
        return register.toString();
    }

    public char lastBit();
}
