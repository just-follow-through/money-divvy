package com.kakao.pay;

public class ChattingRoom {

    String roomId;

    ChattingRoom(String roomId){

        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object object) {
        ChattingRoom chattingRoom = (ChattingRoom)object;
        return this.roomId.equals(chattingRoom.roomId);
    }
}
