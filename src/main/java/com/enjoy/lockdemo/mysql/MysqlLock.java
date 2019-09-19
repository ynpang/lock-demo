package com.enjoy.lockdemo.mysql;

import com.enjoy.lockdemo.zk.AbstractLock;

public class MysqlLock extends AbstractLock {
    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public void waitLock() {

    }

    @Override
    public void unLock() {

    }
}
