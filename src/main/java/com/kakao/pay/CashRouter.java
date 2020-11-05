package com.kakao.pay;

public class CashRouter {

    public int[] divvyEqually(int cash, int numOfReceivers) {

        int[] result = new int[numOfReceivers];

        int quotient = cash / numOfReceivers;
        int remainder = cash % numOfReceivers;

        for (int i = 0 ; i < result.length ; i++) {
            result[i] = quotient;
        }

        if (remainder > 0) {
            result[numOfReceivers-1] += 1;
        }

        return result;

    }
}
