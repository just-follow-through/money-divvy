package com.kakao.pay.divvy.model.domain;

import com.kakao.pay.divvy.utils.JsonSerializable;
import com.kakao.pay.divvy.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MoneyDividends implements JsonSerializable {

    private List<MoneyDividend> items;

    private Map<User, MoneyDividend> receivedUser;

    public MoneyDividends(List<MoneyDividend> items) {
        this.items = items;
        this.receivedUser = new HashMap<>();
    }

    public boolean isReceivedUser(User receiveUser) {
        return receivedUser.containsKey(receiveUser);
    }

    public void assign(User receiveUser, MoneyDividend moneyDividend) {
        receivedUser.put(receiveUser, moneyDividend);
    }

    public static MoneyDividends fromJson(String json) {
        return JsonUtil.fromJson(json, MoneyDividends.class);
    }

    public String toString() {
        return toJson();
    }

}
