package com.kakao.pay.divvy.model;

import java.util.HashMap;
import java.util.Map;

public class MoneyDivvy {

    ChattingRoom chattingRoom;
    User owner;
    Long createdAt;
    Long expiredAt;
    Map<User, MoneyDividend> receivedUser;

    public MoneyDivvy(
            User owner,
            ChattingRoom chattingRoom,
            Long createdAt,
            Long expiredAt
    ) {

        this.owner = owner;
        this.chattingRoom = chattingRoom;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.receivedUser = new HashMap<>();
    }

    public MoneyDividend findAvailableOneDividend() {
        return new MoneyDividend();
    }

    public boolean isTimeLimitExpired(Long requestAt) {
        return this.expiredAt < requestAt;
    }

    public boolean isOwner(User receiveUser) {
        return this.owner.equals(receiveUser);
    }

    public boolean isReceivedUser(User receiveUser) {
        return receivedUser.containsKey(receiveUser);
    }

    public boolean isInTheSameRoom(ChattingRoom room) {
        return this.chattingRoom.equals(room);
    }

    public void assign(User receiveUser, MoneyDividend moneyDividend) {
        receivedUser.put(receiveUser, moneyDividend);
    }



}
