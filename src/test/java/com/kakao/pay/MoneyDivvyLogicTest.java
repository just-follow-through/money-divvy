package com.kakao.pay;

import com.kakao.pay.divvy.MoneyDivvyApplication;
import com.kakao.pay.divvy.model.domain.ChattingRoom;
import com.kakao.pay.divvy.model.domain.MoneyDividend;
import com.kakao.pay.divvy.model.domain.MoneyDivvy;
import com.kakao.pay.divvy.model.domain.User;
import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import com.kakao.pay.divvy.model.dto.*;
import com.kakao.pay.divvy.service.MoneyDivvyService;
import com.kakao.pay.divvy.service.MoneyDivvyReportService;
import com.kakao.pay.divvy.service.MoneyReceiveService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = MoneyDivvyApplication.class
)
public class MoneyDivvyLogicTest {

    @Autowired
    MoneyDivvyService moneyDivvyService;

    @Autowired
    MoneyReceiveService moneyReceiveService;

    @Autowired
    MoneyDivvyReportService moneyDivvyReportService;

    private ReceivableToken memberToken;

    @Before
    public void setUp() {
        MoneyDivvyRequestDto moneyDivvyRequestDto =
                new MoneyDivvyRequestDto(
                        new User("user-A"),
                        new ChattingRoom("room-A"),
                        10000,
                        10
                );

        memberToken = moneyDivvyService.register(moneyDivvyRequestDto);

    }

    @Test
    public void testMoneyDivvyRequest() {

        MoneyDivvyRequestDto moneyDivvyRequestDto =
                new MoneyDivvyRequestDto(
                    new User("user-A"),
                    new ChattingRoom("room-A"),
                   10000,
                    10
                );

        ReceivableToken token = moneyDivvyService.register(moneyDivvyRequestDto);

        assertEquals(3, token.length());

        MoneyDivvy moneyDivvy = moneyDivvyService.find(token);

        System.out.println(moneyDivvy);

        assertEquals(token.toString(), moneyDivvy.getToken().toString());

    }

    @Test
    public void testMoneyReceiveRequest() {

        MoneyReceiveRequestDto moneyReceiveRequestDto =
                new MoneyReceiveRequestDto(
                        new User("user-B"),
                        new ChattingRoom("room-A"),
                        memberToken,
                        System.currentTimeMillis()
                );

        MoneyReceiveResponseDto responseDto = moneyReceiveService.receive(moneyReceiveRequestDto);

        assertTrue(responseDto.getReceiveMoney() > 0);

    }

    @Test
    public void testMoneyDivvyReportRequest() {

        MoneyReceiveRequestDto moneyReceiveRequestDto =
                new MoneyReceiveRequestDto(
                        new User("user-B"),
                        new ChattingRoom("room-A"),
                        memberToken,
                        System.currentTimeMillis()
                );

        moneyReceiveService.receive(moneyReceiveRequestDto);

        MoneyDivvyReportRequestDto requestDto = new MoneyDivvyReportRequestDto(
                new User("user-A"),
                new ChattingRoom("room-A"),
                memberToken,
                System.currentTimeMillis()
        );
        MoneyDivvyReportResponseDto result = moneyDivvyReportService.report(requestDto);

        System.out.println(result);
        assertEquals(10000, result.getAmountOfMoney());
    }

}
