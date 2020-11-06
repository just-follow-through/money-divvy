package com.kakao.pay.divvy.model.domain;

public class DivvyUp {

    public static ReceivableToken execute(DivvyRequest divvyRequest) {
        return ReceivableToken.generate(3);
    }
}
