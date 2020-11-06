package com.kakao.pay.divvy.model.domain;

public class ReceivableToken {

    private String key;

    ReceivableToken(String key) {
        this.key = key;
    }

    public int length() {
        return key.length();
    }

    public static ReceivableToken generate(int keySize) {
        return AsciiCharRandomGenerator.generate(keySize);
    }

    @Override
    public boolean equals(Object object) {

        ReceivableToken token = (ReceivableToken)object;
        return this.key.equals(token.key);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String toString() {
        return key;
    }
}
