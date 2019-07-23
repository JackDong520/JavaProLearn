package com.netty.Share.Request;



public class Request3 extends AbstractRequest {

    public Request3(Object obj) {
        super(obj);
    }

    @Override
    public int getRequestStatus() {
        return 3;
    }
}
