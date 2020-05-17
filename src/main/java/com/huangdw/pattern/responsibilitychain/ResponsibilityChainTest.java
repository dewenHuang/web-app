package com.huangdw.pattern.responsibilitychain;

public class ResponsibilityChainTest {
    public static void main(String[] args) {
        // 据此判断由谁处理
        String input = "1";

        BaseHandler oneHandler = new OneHandler("1".equals(input));
        BaseHandler twoHandler = new TwoHandler("2".equals(input));
        BaseHandler defaultHandler = new DefaultHandler(true);

        oneHandler.setNextHandler(twoHandler);
        twoHandler.setNextHandler(defaultHandler);

        // 如果1号处理器处理不了，就交由下一个处理器处理，以此类推
        oneHandler.handleRequest();
    }
}
