package ru.nsu.mbogdanov2;

import java.math.BigInteger;
import java.util.Random;


public class RabinKarp {

    private final String subString;
    private final long subStringHash;
    private final int subStrLen;
    private final long randomPrime;
    private final int radix;
    private long newRing;


    public RabinKarp(String pat) {
        this.subString = pat;
        radix = 256;
        subStrLen = pat.length();
        randomPrime = longRandomPrime();

        newRing = 1;
        for (int i = 1; i <= subStrLen-1; i++)
            newRing = (radix * newRing) % randomPrime;
        subStringHash = hash(pat, subStrLen);
    }

    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++)
            h = (radix * h + key.charAt(j)) % randomPrime;
        return h;
    }

    private boolean check(String txt, int i) {
        for (int j = 0; j < subStrLen; j++)
            if (subString.charAt(j) != txt.charAt(i + j))
                return false;
        return true;
    }


    public Integer search(String txt) {
        int n = txt.length();
        if (n < subStrLen) return n;
        long txtHash = hash(txt, subStrLen);

        if ((subStringHash == txtHash) && check(txt, 0))
            return 0;

        for (int i = subStrLen; i < n; i++) {
            txtHash = (txtHash + randomPrime - newRing*txt.charAt(i-subStrLen) % randomPrime) % randomPrime;
            txtHash = (txtHash*radix + txt.charAt(i)) % randomPrime;

            int offset = i - subStrLen + 1;
            if ((subStringHash == txtHash) && check(txt, offset))
                return offset;
        }

        return null;
    }


    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

}
