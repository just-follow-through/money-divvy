package com.kakao.pay.divvy.store.jpa.jpo;

import com.kakao.pay.divvy.model.domain.User;
import com.kakao.pay.divvy.utils.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserJpo implements JsonSerializable {

    @Lob
    private String userId;

    public UserJpo(User user) {
        this.userId = user.getUserId();
    }

    public User toDomain() {
        return new User(this.userId);
    }

}
