package com.kakao.pay.divvy.store.jpa.jpo;

import com.kakao.pay.divvy.model.domain.User;

import javax.persistence.Embeddable;

@Embeddable
public class MoneyDividendJpo {

    private int amountOfMoney;

    private User assignedUser;

    private boolean assignStatus;

    private Long assignAt;

}
