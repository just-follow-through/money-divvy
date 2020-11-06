package com.kakao.pay.divvy.model.domain;

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

}
