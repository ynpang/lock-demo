package com.enjoy.lockdemo.template;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 模板方法模式(在父类中编排主流程，将步骤实现延迟到子类去实现)  清点商品-》计算价目-》微信/支付宝支付、会员卡支付、现金支付 -》送货上门
 */
public abstract  class AbstractTemplate {

    public void shopping(){
        Map<String,Float> cars = new ConcurrentHashMap<>();
        cars.put("a",10f);
        cars.put("b",10f);
        cars.put("c",10f);
        
        checkGoods(cars);
        float money = calculation(cars);
        
        if(pay(money)){
            delivery();
        }

    }

    private void checkGoods(Map<String, Float> cars) {
        if(cars !=null){
            System.out.println("你购买了：");
            for(String key:cars.keySet()){
                System.out.print(key + " ");
            }
            System.out.println();
        }
    }

    private float calculation(Map<String, Float> cars) {
        float result = 0;
        if(cars!=null){
            for(String key:cars.keySet()){
                result += cars.get(key);
            }
        }
        System.out.println("你总共应该支付:" + result);
        return result;
    }

    public abstract boolean pay(Float money);

    private void delivery() {
        System.out.println("邮寄货品成功");
    }
}
