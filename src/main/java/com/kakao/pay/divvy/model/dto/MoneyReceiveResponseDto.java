package com.kakao.pay.divvy.model.dto;

import com.kakao.pay.divvy.utils.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoneyReceiveResponseDto implements JsonSerializable {
    int receiveMoney;
}
