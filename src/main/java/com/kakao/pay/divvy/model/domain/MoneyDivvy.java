package com.kakao.pay.divvy.model.domain;

import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MoneyDivvy{

    ReceivableToken token;

    ChattingRoom chattingRoom;
    User owner;

    int amountOfMoney;
    int numOfReceivers;

    Long createdAt;
    Long expiredAt;

    @Setter
    MoneyDividends moneyDividends;

    public MoneyDivvy(
            ReceivableToken token,
            ChattingRoom chattingRoom,
            User owner,
            int amountOfMoney,
            int numOfReceivers,
            Long createdAt,
            Long expiredAt
    ) {

        this.token = token;
        this.chattingRoom = chattingRoom;
        this.owner = owner;
        this.amountOfMoney = amountOfMoney;
        this.numOfReceivers = numOfReceivers;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }

    public boolean isTimeLimitExpired(Long requestAt) {
        return this.expiredAt < requestAt;
    }

    public boolean isOwner(User receiveUser) {
        return this.owner.equals(receiveUser);
    }

    public boolean isInTheSameRoom(ChattingRoom room) {
        return this.chattingRoom.equals(room);
    }

    public boolean isReceivedUser(User receiveUser) {
        return this.moneyDividends.isReceivedUser(receiveUser);
    }

    public MoneyDividend assign(User receiveUser) {
        return this.moneyDividends.assign(receiveUser);
    }

    public List<MoneyDividend> findReceivedItems() {
        return this.moneyDividends.findReceivedItems();
    }

    public String toString() {
        return String.format(
                new StringBuilder()
                        .append("%s\n")
                        .append("%s\n")
                        .append("%s\n")
                        .append("%d\n")
                        .append("%d\n")
                        .append("%s\n")
                .toString(),
                token,
                chattingRoom,
                owner,
                amountOfMoney,
                numOfReceivers,
                moneyDividends
        );
    }

}
