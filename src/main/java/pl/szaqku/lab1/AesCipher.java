package pl.szaqku.lab1;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AesCipher {

    private final byte[] cipheredText;
    private final int blockSize;

    public AesCipher(byte[] cipheredText, int blockSize) {
        this.cipheredText = cipheredText;
        this.blockSize = blockSize;
    }

    public byte getByte(int position) {
        return cipheredText[position];
    }

    public byte getByte(int blockIndex, int position) {
        return cipheredText[(blockIndex * blockSize) + position];
    }

    public int getIndexOfLastBlock() {
        return (cipheredText.length / blockSize) - 1;
    }

    public byte[] getBlock(int blockIndex) {
        assert cipheredText.length / blockSize > blockIndex;
        assert blockIndex > 0;

        return Arrays.copyOfRange(cipheredText, blockIndex * blockSize, (blockIndex + 1) * blockSize);
    }

    public byte[] getPreviousBlock(int blockIndex) {
        assert cipheredText.length / blockSize <= blockIndex;
        assert blockIndex > 0;

        return getBlock(blockIndex - 1);
    }

    public AesCipher modify(int position, byte newByte) {
        var newCipheredText = Arrays.copyOf(cipheredText, cipheredText.length);
        newCipheredText[position] = newByte;
        return new AesCipher(newCipheredText, blockSize);
    }

    public AesCipher modify(int blockIndex, int position, byte newByte) {
        var newCipheredText = Arrays.copyOf(cipheredText, cipheredText.length);
        newCipheredText[(blockIndex * blockSize) + position] = newByte;
        return new AesCipher(newCipheredText, blockSize);
    }

    public AesCipher modify(int blockIndex, int start, int end, byte[] newBytes) {
        var newCipheredText = Arrays.copyOf(cipheredText, cipheredText.length);
        start = (blockIndex * blockSize) + start;
        for (int i = start; i < end; i++) {
            newCipheredText[i] = newBytes[i];
        }
        return new AesCipher(newCipheredText, blockSize);
    }

    public byte[] getCipher() {
        return cipheredText;
    }

    public byte randomlyFindMatchingByte(int blockIndex, int bytePosition) {
        byte c1p = 0;
        var attemptTemplate = this.dropPreviousBlocks(blockIndex);
        for (int possibleValue = 0; possibleValue <= 255; possibleValue++) {
            try {
                var attempt = attemptTemplate.modify(blockIndex, bytePosition, (byte) possibleValue);
                AesUtil.decrypt(attempt.getCipher());
                c1p = (byte) possibleValue;
            } catch (BadPaddingException ex) {
                //Failed attempt
            } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException e) {
                e.printStackTrace();
            }
        }
        return c1p;
    }

    private AesCipher dropPreviousBlocks(int blockIndex) {
        var end = (blockIndex+2) * AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES;
        return new AesCipher(Arrays.copyOfRange(cipheredText,0, end), blockSize);
    }

    public AesCipher fitPreviousCipherBytesInBlock(int blockIndex, byte[] iArray, int padding, int numberOBytesStartingFromEnd) {
        var newCipheredText = Arrays.copyOf(cipheredText, cipheredText.length);
        var startOfBlock = blockIndex * blockSize;

        for (int i = blockSize - 1; i > blockSize - numberOBytesStartingFromEnd - 1 ; i--) {
            newCipheredText[startOfBlock + i] = (byte) ( padding ^ iArray[startOfBlock + i + AesUtil.AES_DEFAULT_BLOCK_SIZE_BYTES] );
        }
        for (int i = blockSize - numberOBytesStartingFromEnd - 1; i >= 0 ; i--) {
            newCipheredText[startOfBlock + i] = 0x00;
        }

        return new AesCipher(newCipheredText, blockSize);
    }

    @Override
    public AesCipher clone() {
        return new AesCipher(Arrays.copyOf(cipheredText, cipheredText.length), blockSize);
    }

    public int getNumberOfBlocks() {
        return cipheredText.length / blockSize;
    }
}
