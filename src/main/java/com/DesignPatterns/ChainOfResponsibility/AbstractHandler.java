package com.DesignPatterns.ChainOfResponsibility;

public abstract class AbstractHandler {
    protected AbstractHandler nextHandler;//下一个节点

    public final void handleRequest(AbstractRequest request){

        if (getHandleLevel() == request.getRequestLevel()){
            handle(request);
        }else {
            if (nextHandler !=null){
                nextHandler.handleRequest(request);
            }else {
                System.out.println("All of handler can not handle the request");
            }
        }
    }

    /**
     * 获取处理级别
     * @return
     */
    protected abstract int getHandleLevel();

    /**
     * 每个处理对象的具体处理方式
     * @param request
     */
    protected abstract void handle(AbstractRequest request);


}
