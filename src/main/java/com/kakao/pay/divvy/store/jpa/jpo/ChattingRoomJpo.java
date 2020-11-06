package com.kakao.pay.divvy.store.jpa.jpo;

import com.kakao.pay.divvy.model.domain.ChattingRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ChattingRoomJpo {

    @Lob
    private String chattingRoomId;

    public ChattingRoomJpo(ChattingRoom chattingRoom) {
        this.chattingRoomId = chattingRoom.getRoomId();
    }

    public ChattingRoom toDomain() {
        return new ChattingRoom(this.chattingRoomId);
    }
}
