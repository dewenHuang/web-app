package com.huangdw.pattern.responsibilitychain;

public class TwoHandler extends BaseHandler {
    public TwoHandler(boolean isConsume) {
        super(isConsume);
    }

    @Override
    protected void doSomething() {
        System.out.println(this.getClass().getSimpleName() + "处理了");
    }
}
