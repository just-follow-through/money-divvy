package com.kakao.pay.divvy.model.domain.router;

import com.kakao.pay.divvy.model.domain.MoneyDividend;
import com.kakao.pay.divvy.model.domain.MoneyDividends;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MoneyEquallyRouter implements MoneyRouter {

    @Override
    public MoneyDividends divide(int amountOfMoney, int numOfReceivers) {

        List<MoneyDividend> result = new ArrayList<>();

        int quotient = amountOfMoney / numOfReceivers;
        int remainder = amountOfMoney % numOfReceivers;

        for (int i = 0 ; i < numOfReceivers ; i++) {
            result.add(new MoneyDividend(quotient));
        }

        if (remainder > 0) {
            MoneyDividend lastItem = result.get(numOfReceivers-1);
            result.set(numOfReceivers-1,
                    new MoneyDividend(lastItem.getAmountOfMoney() + 1));
        }

        return new MoneyDividends(result);
    }

}
