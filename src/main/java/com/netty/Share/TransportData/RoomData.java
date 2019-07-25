package com.netty.Share.TransportData;

public class RoomData {
    private int roomAction;
    private int roomHash;

    public RoomData() {
    }

    public RoomData(int roomAction, int roomHash) {
        this.roomAction = roomAction;
        this.roomHash = roomHash;
    }

    public int getRoomAction() {
        return roomAction;
    }

    public void setRoomAction(int roomAction) {
        this.roomAction = roomAction;
    }

    public int getRoomHash() {
        return roomHash;
    }

    public void setRoomHash(int roomHash) {
        this.roomHash = roomHash;
    }
}
