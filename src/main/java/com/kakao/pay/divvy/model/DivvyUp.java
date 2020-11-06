package com.kakao.pay.divvy.model;

public class DivvyUp {

    public static ReceivableToken execute(DivvyRequest divvyRequest) {
        return ReceivableToken.generate(3);
    }
}
