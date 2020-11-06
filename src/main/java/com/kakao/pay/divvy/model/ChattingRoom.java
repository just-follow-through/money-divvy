package com.kakao.pay.divvy.model;

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
}
