package com.awakeee.hodgepodge.thread.cas;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyLock {
    //原子性操作，保证在多线程环境下不会出现异常

    private AtomicBoolean locked = new AtomicBoolean(false);

    public boolean lock(){
        return locked.compareAndSet(false,true);
    }
}
