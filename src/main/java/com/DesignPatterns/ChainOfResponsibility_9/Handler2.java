package com.DesignPatterns.ChainOfResponsibility_9;

public class Handler2 extends AbstractHandler {
    @Override
    protected int getHandleLevel() {
        return 2;
    }

    @Override
    protected void handle(AbstractRequest request) {
        System.out.println("Handler2 handle request" + request.getRequestLevel());
    }
}
