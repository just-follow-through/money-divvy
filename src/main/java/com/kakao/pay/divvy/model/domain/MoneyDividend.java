package com.kakao.pay.divvy.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MoneyDividend implements ValueObject{

    private int amountOfMoney;

    private User assignedUser;

    private boolean available;

    private Long assignAt;

    public boolean isAvailable() {
        return this.available;
    }

    public MoneyDividend(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
        this.available = false;
        this.assignedUser = null;
        this.assignAt = -1L;
    }
}
