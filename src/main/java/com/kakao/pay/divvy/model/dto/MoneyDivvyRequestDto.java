package com.kakao.pay.divvy.model.dto;

import com.kakao.pay.divvy.model.domain.ChattingRoom;
import com.kakao.pay.divvy.model.domain.User;
import com.kakao.pay.divvy.utils.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MoneyDivvyRequestDto implements JsonSerializable {

    User user;

    ChattingRoom chattingRoom;

    int amountOfMoney;

    int numOfReceivers;

}
