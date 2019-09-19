package com.enjoy.lockdemo.template;

public class WxPayTemplate extends AbstractTemplate{
    @Override
    public boolean pay(Float money) {
        System.out.println("微信支付了:" + money);
        return true;
    }

    public static void main(String[] args) {
        AbstractTemplate at = new WxPayTemplate();
        at.shopping();
    }
}
