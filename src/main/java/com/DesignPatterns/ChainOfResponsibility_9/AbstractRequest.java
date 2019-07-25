package com.DesignPatterns.ChainOfResponsibility_9;

public abstract class AbstractRequest {
    private Object obj;//处理对象

    public AbstractRequest(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }
    public abstract int getRequestLevel();
}
