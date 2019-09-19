package com.enjoy.lockdemo.service;

import com.enjoy.lockdemo.lock.OrderNumGenerator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

@Service
public class OrderService implements Runnable{

    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();

    private static CountDownLatch countDownLatch = new CountDownLatch(50);

    private static List<String> result = new Vector<>();

    public void run(){
        try{
            countDownLatch.await();
            result.add(orderNumGenerator.getNumber());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("生成唯一订单号");
        for(int i = 0; i < 99; i ++){
            new Thread(new OrderService()).start();
            countDownLatch.countDown();
        }

        Thread.sleep(1000);

        Collections.sort(result);
        for(String str:result){
            System.out.println(str);
        }

    }

}
