package com.kakao.pay;

import com.kakao.pay.divvy.model.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MoneyDivvyModelTest {

    MoneyDivvy moneyDivvy;

    @Before
    public void setUp() {
        moneyDivvy = new MoneyDivvy(
                new User("A"),
                new ChattingRoom("A"),
                System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000
        );
    }

    @Test
    public void testThreeOfAsciiCharRandomGenerator() {

        int keySize = 3;
        ReceivableToken token = AsciiCharRandomGenerator.generate(keySize);
        assertEquals(keySize, token.length());
    }

    @Test
    public void testNoDuplicationRandomGenerator() {

        int keySize = 3;
        Map<ReceivableToken, Object> dict = new HashMap<>();

        boolean hasDuplicate = false;

        for ( int i = 0 ; i < 100 ; i++) {
            ReceivableToken token = AsciiCharRandomGenerator.generate(keySize);

            if (dict.containsKey(token)) {
                hasDuplicate = true;
                break;
            }
            dict.put(token, null);
        }

        assertEquals(false, hasDuplicate);
    }

    @Test
    public void testCashDivvyEqually() {

        int cash = 10000;

        CashRouter cashRouter = new CashRouter();
        int[] dividends = cashRouter.divvyEqually(10000, 2);

        assertEquals(5000, dividends[0]);
        assertEquals(5000, dividends[1]);

        int sum = 0;
        boolean hasNoMoney = false;
        for (int i = 0; i < dividends.length ; i++) {
            if (dividends[i] <= 0) {
                hasNoMoney = true;
            }
            sum += dividends[i];
        }

        assertEquals(cash, sum);
        assertFalse(hasNoMoney);
    }

    @Test
    public void testCashDivvyEquallyWithRemainder() {
        int cash = 10000;

        CashRouter cashRouter = new CashRouter();
        int[] dividends = cashRouter.divvyEqually(10000, 3);

        assertEquals(3333, dividends[0]);
        assertEquals(3333, dividends[1]);
        assertEquals(3334, dividends[2]);

        int sum = 0;
        boolean hasNoMoney = false;
        for (int i = 0; i < dividends.length ; i++) {
            if (dividends[i] <= 0) {
                hasNoMoney = true;
            }
            sum += dividends[i];
        }

        assertEquals(cash, sum);
        assertFalse(hasNoMoney);
    }

    @Test
    public void testGenerateReceivableToken() {
        ReceivableToken receivableToken = ReceivableToken.generate(3);

        assertEquals(3, receivableToken.length());
    }

    @Test
    public void testCashDivvyRequest() {

        DivvyRequest divvyRequest = new DivvyRequest(10000, 3);

        ReceivableToken receivableToken = DivvyUp.execute(divvyRequest);

        assertEquals(3, receivableToken.length());

    }

    @Test
    public void testFindAvailableOneDividend() {

        MoneyDividend moneyDividend = moneyDivvy.findAvailableOneDividend();

        assertTrue(moneyDividend.isAvailable());
    }

    @Test
    public void testCheckAlreadyReceivedUser() {

        User userBeta = new User("B");
        assertFalse(moneyDivvy.isReceivedUser(userBeta));

        User userGray = new User("G");
        moneyDivvy.assign(userGray, moneyDivvy.findAvailableOneDividend());
        assertTrue(moneyDivvy.isReceivedUser(userGray));

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
