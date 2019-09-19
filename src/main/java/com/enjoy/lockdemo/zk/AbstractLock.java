package com.enjoy.lockdemo.zk;

public abstract class AbstractLock  implements Lock{
    @Override
    public void getLock() {
        if(tryLock()){
            System.out.println("获取lock锁的资源");
        }else{
            waitLock();
            getLock();
        }
    }

    public abstract boolean tryLock();

    public abstract void waitLock();

    public abstract void unLock();
}
