package com.enjoy.lockdemo.syn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumGenerator {

    public static int count = 0;
    public static Object lock = new Object();

    public String getNumber(){
        synchronized (lock){
            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            return simple.format(new Date()) + "-" + ++count;
        }
    }
}
