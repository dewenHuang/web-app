package com.huangdw.pattern.responsibilitychain;

public class DefaultHandler extends BaseHandler {
    public DefaultHandler(boolean isConsume) {
        super(isConsume);
    }

    @Override
    protected void doSomething() {
        System.out.println(this.getClass().getSimpleName() + "处理了");
    }
}
