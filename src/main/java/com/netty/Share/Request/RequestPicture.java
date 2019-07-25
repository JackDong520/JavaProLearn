package com.netty.Share.Request;


import com.netty.Share.RequestType;
import com.netty.Share.TransportData.RoomData;

public class RequestPicture {

    private int type;
    private String obj;

    public RequestPicture() {
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public RequestPicture(String obj, int type) {
        this.type = type;
        this.obj = obj;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRequestStatus() {
        return type;
    }
}
