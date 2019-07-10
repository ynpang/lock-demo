package com.enjoy.lockdemo;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class IndexController {

    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("deduct_stock")
    public String deductStock(){
        String lockKey = "product_001";
        try{
            //jedis.setnx(key,value);
            //Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey,"a");
            //stringRedisTemplate.expire(lockKey, 10, TimeUnit.SECONDS);
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "a", 10, TimeUnit.SECONDS);
            if(!result){
                return "error";
            }

            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if(stock > 0){
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock",realStock + "") ;
                System.out.println("扣减成功，剩余库存：" + realStock + "");
            }else{
                System.out.println("扣减失败，库存不足");
            }

        }finally {
            stringRedisTemplate.delete(lockKey);

        }
        return "end";
    }

    @RequestMapping("deduct_stock2")
    public String deductStock2(){
        String lockKey = "product_001";
        String clientId = UUID.randomUUID().toString();
        try{
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 10, TimeUnit.SECONDS);
            if(!result){
                return "error";
            }

            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if(stock > 0){
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock",realStock + "") ;
                System.out.println("扣减成功，剩余库存：" + realStock + "");
            }else{
                System.out.println("扣减失败，库存不足");
            }

        }finally {
            if(clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))){
                stringRedisTemplate.delete(lockKey);
            }
        }
        return "end";
    }

    @RequestMapping("deduct_stock3")
    public String deductStock3() throws InterruptedException{
        String lockKey = "product_001";
        RLock lock = redisson.getLock(lockKey);
        try{
            lock.tryLock(30,TimeUnit.SECONDS);

            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if(stock > 0){
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock",realStock + "") ;
                System.out.println("扣减成功，剩余库存：" + realStock + "");
            }else{
                System.out.println("扣减失败，库存不足");
            }

        }finally {
            lock.unlock();
        }
        return "end";
    }
}
