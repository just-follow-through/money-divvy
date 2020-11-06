package com.kakao.pay.divvy.model.dto;

import com.kakao.pay.divvy.utils.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MoneyDivvyReportResponseDto implements JsonSerializable {

    Long moneyDivvyCreatedAt;

    int amountOfMoney;

    int totalReceivedMoney;

    List<MoneyDividendReportDto> receivedList;

    public String toString() {
        return toJson();
    }

}
