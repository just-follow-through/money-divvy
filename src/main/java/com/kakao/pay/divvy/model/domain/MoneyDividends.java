package com.kakao.pay.divvy.model.domain;

import com.kakao.pay.divvy.utils.JsonSerializable;
import com.kakao.pay.divvy.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public MoneyDividend findAvailableOneDividend() {

        MoneyDividend result = new MoneyDividend();
        for (MoneyDividend moneyDividend: items) {
            if (moneyDividend.isAvailable()) {
                result = moneyDividend;
                break;
            }
        }

        return result;
    }

    public boolean isReceivedUser(User receiveUser) {
        return receivedUser.containsKey(receiveUser);
    }

    public MoneyDividend assign(User receiveUser) {

        MoneyDividend moneyDividend = findAvailableOneDividend();

        if (moneyDividend.isAvailable())
        {
            moneyDividend.setAssignAt(System.currentTimeMillis());
            moneyDividend.setAvailable(false);
            moneyDividend.setAssignedUser(receiveUser);
            receivedUser.put(receiveUser, moneyDividend);

            return moneyDividend;
        }
        return null;
    }

    public List<MoneyDividend> findReceivedItems() {
        return items.stream().filter(
                item -> item.getAssignedUser() != null)
                .collect(Collectors.toList());
    }

    public static MoneyDividends fromJson(String json) {
        return JsonUtil.fromJson(json, MoneyDividends.class);
    }

    public String toString() {
        return toJson();
    }

}
