package com.netty.Share.Request;

import java.io.Serializable;

public abstract class AbstractRequest implements Serializable {
    protected Object obj;//处理对象



    public Object getObj() {
        return obj;
    }
    public abstract int getRequestStatus();
}
