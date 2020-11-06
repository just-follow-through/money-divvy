package com.kakao.pay.divvy.model.dto;

import com.kakao.pay.divvy.model.domain.MoneyDividend;
import com.kakao.pay.divvy.utils.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoneyDividendReportDto implements JsonSerializable {

    int receivedMoney;

    String userId;

    public static MoneyDividendReportDto from(MoneyDividend moneyDividend) {
        return new MoneyDividendReportDto(
                moneyDividend.getAmountOfMoney(),
                moneyDividend.getAssignedUser().getUserId()
        );
    }
}
