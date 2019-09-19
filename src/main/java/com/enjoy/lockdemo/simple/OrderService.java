package com.enjoy.lockdemo.simple;

public class OrderService implements  Runnable {

    private OrderNumGenerator    orderNumGenerator  = new OrderNumGenerator(); //定义成全局的

    public void run() {
        getNumber();
    }

    public void getNumber(){
        String number =    orderNumGenerator.getNumber();
        System.out.println(Thread.currentThread().getName()+"num"+number);
    }

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        for (int i = 0; i <1000; i++) {  //开启100个线程
            new Thread(orderService).start();
        }
    }

}
