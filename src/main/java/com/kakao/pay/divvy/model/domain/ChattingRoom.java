package com.kakao.pay.divvy.model.domain;

import lombok.Getter;

@Getter
public class ChattingRoom {

    String roomId;

    public ChattingRoom(String roomId){

        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object object) {
        ChattingRoom chattingRoom = (ChattingRoom)object;
        return this.roomId.equals(chattingRoom.roomId);
    }

    @Override
    public String toString() {
        return String.format("RoomId[%s]", roomId);
    }
}
