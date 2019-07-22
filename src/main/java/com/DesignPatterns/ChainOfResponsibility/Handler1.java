package com.DesignPatterns.ChainOfResponsibility;

import com.DesignPatterns.ChainOfResponsibility.AbstractHandler;
import com.DesignPatterns.ChainOfResponsibility.AbstractRequest;

public class Handler1 extends AbstractHandler {
    @Override
    protected int getHandleLevel() {
        return 1;
    }

    @Override
    protected void handle(AbstractRequest request) {
        System.out.println("Handler1 handle request" + request.getRequestLevel());
    }
}
