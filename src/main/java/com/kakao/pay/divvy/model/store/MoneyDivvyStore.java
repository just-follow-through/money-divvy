package com.kakao.pay.divvy.model.store;

import com.kakao.pay.divvy.model.domain.MoneyDivvy;
import com.kakao.pay.divvy.model.domain.token.ReceivableToken;

import java.util.Optional;

public interface MoneyDivvyStore {

    void save(MoneyDivvy moneyDivvy);

    Optional<MoneyDivvy> find(ReceivableToken token);

}
