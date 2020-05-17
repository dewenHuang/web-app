package com.huangdw.pattern.responsibilitychain;

public abstract class BaseHandler {
    // 为 true 表明自己可以处理该 case
    private Boolean isConsume;

    // 下一个责任节点
    private BaseHandler nextHandler;

    public BaseHandler(boolean isConsume) {
        this.isConsume = isConsume;
    }

    protected void handleRequest() {
        if (isConsume) {
            // 如果当前节点可以处理，直接处理
            doSomething();
        } else {
            // 如果当前节点不能处理，并且有下个节点，交由下个节点处理
            if (null != nextHandler) {
                nextHandler.handleRequest();
            }
        }
    }

    protected abstract void doSomething();

    public void setNextHandler(BaseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
