package com.enjoy.lockdemo.lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderNumGenerator {

    public static int count = 0;
    public Lock lock = new ReentrantLock();

    public String getNumber(){
        try{
            lock.lock();
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            return  simple.format(new Date()) + "-" + ++count;
        }finally {
            lock.unlock();
        }
    }

}
