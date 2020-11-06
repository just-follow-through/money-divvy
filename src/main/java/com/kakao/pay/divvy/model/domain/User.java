package com.kakao.pay.divvy.model.domain;

import lombok.Getter;

@Getter
public class User {

    private String userId;

    public User(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object object) {
        User user = (User)object;
        return this.userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("UserId[%s]", userId);
    }

}
