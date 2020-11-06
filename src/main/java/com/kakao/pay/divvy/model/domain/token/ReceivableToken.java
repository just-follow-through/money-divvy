package com.kakao.pay.divvy.model.domain.token;

import com.kakao.pay.divvy.utils.JsonSerializable;
import lombok.Getter;

@Getter
public class ReceivableToken implements JsonSerializable {

    private String key;

    public ReceivableToken(String key) {
        this.key = key;
    }

    public int length() {
        return key.length();
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
