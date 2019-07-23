package com.netty.Share.Request;


import java.io.Serializable;

public class RequestTalk extends AbstractRequest implements Serializable {

    public String name;

    public RequestTalk(Object obj, String name) {
        super(obj);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getRequestStatus() {
        return RequestType.TALK;
    }
}
