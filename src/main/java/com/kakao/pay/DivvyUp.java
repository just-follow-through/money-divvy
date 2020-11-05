package com.kakao.pay;

public class DivvyUp {

    public static ReceivableToken execute(DivvyRequest divvyRequest) {
        return ReceivableToken.generate(3);
    }
}
