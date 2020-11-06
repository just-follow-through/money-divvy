package com.kakao.pay.divvy.service;

import com.kakao.pay.divvy.model.domain.MoneyDividends;
import com.kakao.pay.divvy.model.domain.MoneyDivvy;
import com.kakao.pay.divvy.model.domain.router.MoneyRouter;
import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import com.kakao.pay.divvy.model.domain.token.TokenGenerator;
import com.kakao.pay.divvy.model.dto.MoneyDivvyRequestDto;
import com.kakao.pay.divvy.model.store.MoneyDivvyStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MoneyDivvyService {

    private TokenGenerator tokenGenerator;

    private MoneyRouter moneyRouter;

    private MoneyDivvyStore moneyDivvyStore;

    @Value("1000")
    private int expiredTime;

    public MoneyDivvyService (
            TokenGenerator tokenGenerator,
            MoneyRouter moneyRouter,
            MoneyDivvyStore moneyDivvyStore
    ) {
        this.tokenGenerator = tokenGenerator;
        this.moneyRouter = moneyRouter;
        this.moneyDivvyStore = moneyDivvyStore;
    }

    public ReceivableToken register(MoneyDivvyRequestDto moneyDivvyRequestDto) {

        ReceivableToken receivableToken = tokenGenerator.generate();

        MoneyDivvy moneyDivvy = new MoneyDivvy(
                receivableToken,
                moneyDivvyRequestDto.getChattingRoom(),
                moneyDivvyRequestDto.getUser(),
                moneyDivvyRequestDto.getAmountOfMoney(),
                moneyDivvyRequestDto.getNumOfReceivers(),
                System.currentTimeMillis(),
                System.currentTimeMillis() + expiredTime
        );

        MoneyDividends moneyDividends = moneyRouter.divide(
                moneyDivvy.getAmountOfMoney(),
                moneyDivvy.getNumOfReceivers()
        );

        System.out.println(moneyDividends);

        moneyDivvy.setMoneyDividends(moneyDividends);

        moneyDivvyStore.save(moneyDivvy);

        return receivableToken;
    }

    public MoneyDivvy find(ReceivableToken token) {

        return moneyDivvyStore.find(token)
                        .orElseThrow(() -> new NoSuchElementException("MoneyDivvy key not found:" + token));
    }

}
