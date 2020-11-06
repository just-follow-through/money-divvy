package com.kakao.pay.divvy.model.domain;

import com.kakao.pay.divvy.utils.JsonSerializable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User implements JsonSerializable {

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
