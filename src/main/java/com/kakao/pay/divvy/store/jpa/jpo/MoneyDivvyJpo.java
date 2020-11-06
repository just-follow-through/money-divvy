package com.kakao.pay.divvy.store.jpa.jpo;

import com.kakao.pay.divvy.model.domain.MoneyDividends;
import com.kakao.pay.divvy.model.domain.MoneyDivvy;
import com.kakao.pay.divvy.model.domain.token.ReceivableToken;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "money_divvy")
public class MoneyDivvyJpo {

    @Id
    String token;

    @Embedded
    UserJpo user;

    @Embedded
    ChattingRoomJpo chattingRoom;

    int amountOfMoney;

    int numOfReceivers;

    Long createdAt;

    Long expiredAt;

    @Lob
    String moneyDividends;

    public MoneyDivvyJpo(MoneyDivvy moneyDivvy) {
        this.token = moneyDivvy.getToken().toString();
        this.chattingRoom = new ChattingRoomJpo(moneyDivvy.getChattingRoom());
        this.user = new UserJpo(moneyDivvy.getOwner());

        this.amountOfMoney = moneyDivvy.getAmountOfMoney();
        this.numOfReceivers = moneyDivvy.getNumOfReceivers();
        this.createdAt = moneyDivvy.getCreatedAt();
        this.expiredAt = moneyDivvy.getExpiredAt();
        this.moneyDividends = moneyDivvy.getMoneyDividends().toJson();
    }

    public MoneyDivvy toDomain() {
        return new MoneyDivvy(
                new ReceivableToken(this.token),
                this.chattingRoom.toDomain(),
                this.user.toDomain(),
                this.amountOfMoney,
                this.numOfReceivers,
                this.createdAt,
                this.expiredAt,
                MoneyDividends.fromJson(this.moneyDividends)
        );
    }

}
