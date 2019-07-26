package com.netty.Share.TransportData;

import java.io.Serializable;

public class PaintData implements Serializable {
    private int RoomHash;
    private int action_Status;

    private float startX;
    private float startY;

    private float currentX;
    private float currentY;

    public PaintData() {
    }

    public int getRoomHash() {
        return RoomHash;
    }

    public void setRoomHash(int roomHash) {
        RoomHash = roomHash;
    }

    public int getAction_Status() {
        return action_Status;
    }

    public void setAction_Status(int action_Status) {
        this.action_Status = action_Status;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getCurrentX() {
        return currentX;
    }

    public void setCurrentX(float currentX) {
        this.currentX = currentX;
    }

    public float getCurrentY() {
        return currentY;
    }

    public void setCurrentY(float currentY) {
        this.currentY = currentY;
    }
}
