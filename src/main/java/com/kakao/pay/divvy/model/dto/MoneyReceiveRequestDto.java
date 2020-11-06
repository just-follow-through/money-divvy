package com.kakao.pay.divvy.model.dto;

import com.kakao.pay.divvy.model.domain.ChattingRoom;
import com.kakao.pay.divvy.model.domain.User;
import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import com.kakao.pay.divvy.utils.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MoneyReceiveRequestDto implements JsonSerializable {

    User user;

    ChattingRoom chattingRoom;

    ReceivableToken token;

    Long createdAt;
}
