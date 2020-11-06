package com.kakao.pay.divvy.service;

import com.kakao.pay.divvy.model.domain.MoneyDividend;
import com.kakao.pay.divvy.model.domain.MoneyDivvy;
import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import com.kakao.pay.divvy.model.dto.MoneyReceiveRequestDto;
import com.kakao.pay.divvy.model.dto.MoneyReceiveResponseDto;
import com.kakao.pay.divvy.model.store.MoneyDivvyStore;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MoneyReceiveService {

    private MoneyDivvyStore moneyDivvyStore;

    public MoneyReceiveService(
            MoneyDivvyStore moneyDivvyStore
    ) {
        this.moneyDivvyStore = moneyDivvyStore;
    }

    public MoneyReceiveResponseDto receive(MoneyReceiveRequestDto moneyReceiveRequestDto) {

        MoneyDivvy moneyDivvy = find(moneyReceiveRequestDto.getToken());

        validate(moneyDivvy, moneyReceiveRequestDto);

        MoneyDividend result = moneyDivvy.assign(moneyReceiveRequestDto.getUser());

        moneyDivvyStore.save(moneyDivvy);

        return new MoneyReceiveResponseDto(result.getAmountOfMoney());
    }

    private MoneyDivvy find(ReceivableToken token) {

        return moneyDivvyStore.find(token)
                        .orElseThrow(() -> new NoSuchElementException("MoneyDivvy key not found:" + token));
    }

    private void validate(MoneyDivvy moneyDivvy, MoneyReceiveRequestDto moneyReceiveRequestDto) {

        if(moneyDivvy.isInTheSameRoom(moneyReceiveRequestDto.getChattingRoom()) == false)
        {
            throw new IllegalArgumentException("You are not in the same room");
        }

        if(moneyDivvy.isOwner(moneyReceiveRequestDto.getUser()))
        {
            throw new IllegalArgumentException("You are the owner of the key");
        }

        if(moneyDivvy.isTimeLimitExpired(moneyReceiveRequestDto.getCreatedAt()))
        {
            throw new IllegalArgumentException("MoneyDivvy is expired");
        }

        if(moneyDivvy.isReceivedUser(moneyReceiveRequestDto.getUser()))
        {
            throw new IllegalArgumentException("You received a money");
        }
    }
}
