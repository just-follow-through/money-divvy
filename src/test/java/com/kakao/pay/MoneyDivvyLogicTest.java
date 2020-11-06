package com.kakao.pay;

import com.kakao.pay.divvy.MoneyDivvyApplication;
import com.kakao.pay.divvy.model.domain.ChattingRoom;
import com.kakao.pay.divvy.model.domain.MoneyDivvy;
import com.kakao.pay.divvy.model.domain.User;
import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import com.kakao.pay.divvy.model.dto.MoneyDivvyRequestDto;
import com.kakao.pay.divvy.service.MoneyDivvyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = MoneyDivvyApplication.class
)
public class MoneyDivvyLogicTest {

    @Autowired
    MoneyDivvyService service;

    @Test
    public void testMoneyDivvyRequest() {

        MoneyDivvyRequestDto moneyDivvyRequestDto =
                new MoneyDivvyRequestDto(
                new User("user-A"),
                new ChattingRoom("room-A"),
                10000,
                10
        );

        ReceivableToken token = service.register(moneyDivvyRequestDto);

        assertEquals(3, token.length());

        MoneyDivvy moneyDivvy = service.find(token);

        System.out.println(moneyDivvy);

        assertEquals(token.toString(), moneyDivvy.getToken().toString());

    }

}
