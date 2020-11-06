package com.kakao.pay.divvy.service;

import com.kakao.pay.divvy.model.domain.MoneyDivvy;
import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import com.kakao.pay.divvy.model.dto.MoneyDividendReportDto;
import com.kakao.pay.divvy.model.dto.MoneyDivvyReportRequestDto;
import com.kakao.pay.divvy.model.dto.MoneyDivvyReportResponseDto;
import com.kakao.pay.divvy.model.store.MoneyDivvyStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MoneyDivvyReportService {

    private MoneyDivvyStore moneyDivvyStore;

    public MoneyDivvyReportService(
            MoneyDivvyStore moneyDivvyStore
    ) {
        this.moneyDivvyStore = moneyDivvyStore;
    }

    public MoneyDivvyReportResponseDto report(MoneyDivvyReportRequestDto moneyDivvyReportRequestDto) {

        MoneyDivvy moneyDivvy = find(moneyDivvyReportRequestDto.getToken());

        validate(moneyDivvy, moneyDivvyReportRequestDto);

        List<MoneyDividendReportDto> receivedItems = moneyDivvy.findReceivedItems()
                .stream()
                .map(MoneyDividendReportDto::from)
                .collect(Collectors.toList());

        int totalReceivedMoney =
                receivedItems.stream().reduce(0,
                        (partialMoneyResult, moneyDividend) ->
                                partialMoneyResult + moneyDividend.getReceivedMoney(),
                        Integer::sum
                );

        return new MoneyDivvyReportResponseDto(
                moneyDivvy.getCreatedAt(),
                moneyDivvy.getAmountOfMoney(),
                totalReceivedMoney,
                receivedItems
        );
    }

    private MoneyDivvy find(ReceivableToken token) {

        return moneyDivvyStore.find(token)
                        .orElseThrow(() -> new NoSuchElementException("MoneyDivvy key not found:" + token));
    }

    private void validate(MoneyDivvy moneyDivvy, MoneyDivvyReportRequestDto moneyDivvyReportRequestDto) {

        if (moneyDivvy.isOwner(moneyDivvyReportRequestDto.getUser()) == false) {
            throw new IllegalArgumentException("You are not the owner of the key");
        }
        if (moneyDivvy.getCreatedAt() <  moneyDivvyReportRequestDto.getCreatedAt() - 7*24*60*60*1000) {
            throw new IllegalArgumentException("The read of MoneyDivvy is expired");
        }

    }

}
