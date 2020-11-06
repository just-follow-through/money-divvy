package com.kakao.pay.divvy.model.domain.router;

import com.kakao.pay.divvy.model.domain.MoneyDividends;

public interface MoneyRouter {

    MoneyDividends divide(int amountOfMoney, int numOfReceivers);
}
