package com.kakao.pay;

import com.kakao.pay.divvy.model.domain.*;
import com.kakao.pay.divvy.model.domain.router.MoneyEquallyRouter;
import com.kakao.pay.divvy.model.domain.router.MoneyRouter;
import com.kakao.pay.divvy.model.domain.token.AsciiCharRandomTokenGenerator;
import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import com.kakao.pay.divvy.model.domain.token.TokenGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MoneyDivvyModelTest {

    MoneyDivvy moneyDivvy;

    TokenGenerator tokenGenerator;

    MoneyRouter moneyRouter;

    @Before
    public void setUp() {
        tokenGenerator = new AsciiCharRandomTokenGenerator(3);

        moneyRouter = new MoneyEquallyRouter();

        moneyDivvy = new MoneyDivvy(
                tokenGenerator.generate(),
                new ChattingRoom("A"), new User("A"),
                10000,
                2,
                System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000
        );

        moneyDivvy.setMoneyDividends(moneyRouter.divide(10000, 2));

    }

    @Test
    public void testThreeOfAsciiCharRandomGenerator() {

        int keySize = 3;
        ReceivableToken token = tokenGenerator.generate();
        assertEquals(keySize, token.length());
    }

    @Test
    public void testNoDuplicationRandomGenerator() {

        int keySize = 3;
        Map<ReceivableToken, Object> dict = new HashMap<>();

        boolean hasDuplicate = false;

        for ( int i = 0 ; i < 100 ; i++) {
            ReceivableToken token = tokenGenerator.generate();

            if (dict.containsKey(token)) {
                hasDuplicate = true;
                break;
            }
            dict.put(token, null);
        }

        assertFalse(hasDuplicate);
    }

    @Test
    public void testMoneyDivvyEqually() {

        int money = 10000;

        MoneyRouter moneyRouter = new MoneyEquallyRouter();
        MoneyDividends moneyDividends = moneyRouter.divide(money, 2);

        int sum = moneyDividends.getItems()
                .stream()
                .reduce(0,
                        (partialAgeResult, moneyDividend)
                                -> partialAgeResult + moneyDividend.getAmountOfMoney(),
                        Integer::sum
                );

        List<MoneyDividend> noMoneyDividend = moneyDividends.getItems()
                .stream()
                .filter(c -> c.getAmountOfMoney() <= 0)
                .collect(Collectors.toList());

        assertEquals(money, sum);
        assertEquals(0, noMoneyDividend.size());
    }

    @Test
    public void testCashDivvyEquallyWithRemainder() {
        int money = 10000;

        MoneyRouter moneyRouter = new MoneyEquallyRouter();
        MoneyDividends moneyDividends = moneyRouter.divide(money, 3);

        int sum = moneyDividends.getItems()
                .stream()
                .reduce(0,
                        (partialAgeResult, moneyDividend)
                                -> partialAgeResult + moneyDividend.getAmountOfMoney(),
                        Integer::sum
                );

        List<MoneyDividend> noMoneyDividend = moneyDividends.getItems()
                .stream()
                .filter(c -> c.getAmountOfMoney() <= 0)
                .collect(Collectors.toList());

        assertEquals(money, sum);
        assertEquals(0, noMoneyDividend.size());
    }

    @Test
    public void testGenerateReceivableToken() {
        ReceivableToken receivableToken = tokenGenerator.generate();

        assertEquals(3, receivableToken.length());
    }

    @Test
    public void testFindAvailableOneDividend() {

        MoneyDividend moneyDividend = moneyDivvy.getMoneyDividends().findAvailableOneDividend();

        assertTrue(moneyDividend.isAvailable());
    }

    @Test
    public void testCheckAlreadyReceivedUser() {

        User userBeta = new User("B");
        assertFalse(moneyDivvy.getMoneyDividends().isReceivedUser(userBeta));

        User userGray = new User("G");
        moneyDivvy.getMoneyDividends().assign(userGray);
        assertTrue(moneyDivvy.getMoneyDividends().isReceivedUser(userGray));

    }

    @Test
    public void testCheckDivvyOwnerUser() {

        User receiveUser = new User("A");
        boolean isOwner = moneyDivvy.isOwner(receiveUser);

        assertTrue(isOwner);
    }

    @Test
    public void testCheckDivvyTimeLimitExpired() {

        long requestAt = System.currentTimeMillis();
        boolean isTimeLimitExpired = moneyDivvy.isTimeLimitExpired(requestAt);

        assertFalse(isTimeLimitExpired);

    }

    @Test
    public void testCheckIsInTheSameRoom() {

        ChattingRoom room = new ChattingRoom("B");

        assertFalse(moneyDivvy.isInTheSameRoom(room));

    }
}
