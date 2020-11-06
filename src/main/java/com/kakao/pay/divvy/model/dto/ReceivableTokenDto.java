package com.kakao.pay.divvy.model.dto;

import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import com.kakao.pay.divvy.utils.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReceivableTokenDto implements JsonSerializable {

    private String token;

    public static ReceivableTokenDto from(ReceivableToken token) {
        return new ReceivableTokenDto(token.getKey());
    }

}
