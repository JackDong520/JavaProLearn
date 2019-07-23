package com.netty.Share.Request;


public class RequestPicture extends AbstractRequest {

    public RequestPicture(Object obj) {
        super(obj);
    }

    @Override
    public int getRequestStatus() {
        return RequestType.PICTURE;
    }
}
