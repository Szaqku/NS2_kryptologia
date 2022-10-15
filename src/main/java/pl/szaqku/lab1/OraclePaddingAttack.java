package pl.szaqku.lab1;

import java.util.stream.IntStream;

public class OraclePaddingAttack {
    private final StringBuilder plainTextBuilder;
    private final AesCipher aesCipher;

    private final byte[] iArray;

    private final int aesBlockSize;

    public OraclePaddingAttack(AesCipher aesCipher) {
        this(aesCipher, AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES);
    }

    public OraclePaddingAttack(AesCipher aesCipher, int aesBlockSize) {
        this.aesCipher = aesCipher;
        this.plainTextBuilder = new StringBuilder(aesCipher.getCipher().length);
        this.aesBlockSize = aesBlockSize;
        IntStream.range(0, aesCipher.getCipher().length)
            .forEach(i -> plainTextBuilder.append('#'));
        this.iArray = new byte[aesCipher.getCipher().length];
    }

    public OraclePaddingAttack decipherByte(int byteIndex) {
        assert byteIndex < aesCipher.getCipher().length;

        var byteIndexInBlock = byteIndex % aesBlockSize;
        var padding = aesBlockSize - byteIndexInBlock;
        var previousToLastBlockIndex = (byteIndex / aesBlockSize) - 1;
        assert previousToLastBlockIndex >= 0; // can't be -1

        // if is not last byte in block
        if (byteIndexInBlock != aesBlockSize - 1) {
            // decipher previous byte
            decipherByte(byteIndex + 1);
        }

        byte c1p = aesCipher.
            fitPreviousCipherBytesInBlock(previousToLastBlockIndex, iArray, padding, padding - 1).
            randomlyFindMatchingByte(previousToLastBlockIndex, byteIndexInBlock);

        var i2 = (byte) (c1p ^ padding);
        var p2 = (byte) (aesCipher.getByte(previousToLastBlockIndex, byteIndexInBlock) ^ i2);

        iArray[byteIndex] = i2;
        plainTextBuilder.setCharAt(byteIndex, (char)((byte) (p2 & 0xFF)));

        return this;
    }

    public OraclePaddingAttack decipherBlock(int blockIndex) {
        assert blockIndex > 0;
        decipherByte(blockIndex * aesBlockSize);
        return this;
    }

    public OraclePaddingAttack decipher() {
        for (int i = 1; i < aesCipher.getNumberOfBlocks(); i++) {
            this.decipherBlock(i);
        }
        return this;
    }

    public String getRawPlainText() {
        return plainTextBuilder.toString();
    }

    public String getPlainText() {
        return new StringBuilder(plainTextBuilder)
            .delete(findStartOfPadding(plainTextBuilder), plainTextBuilder.length())
            .delete(0, 16) //trim 1st block
            .toString();
    }

    private int findStartOfPadding(StringBuilder plainTextBuilder) {
        for (int i = plainTextBuilder.length() - 1 ; i >= 0; i--) {
            if (plainTextBuilder.charAt(i) > (0x10 & 0xFF)) {
                return i + 1;
            }
        }
        return 0;
    }
}
