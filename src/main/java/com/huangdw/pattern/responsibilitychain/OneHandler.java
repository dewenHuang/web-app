package com.huangdw.pattern.responsibilitychain;

public class OneHandler extends BaseHandler {
    public OneHandler(boolean isConsume) {
        super(isConsume);
    }

    @Override
    protected void doSomething() {
        System.out.println(this.getClass().getSimpleName() + "处理了");
    }
}
