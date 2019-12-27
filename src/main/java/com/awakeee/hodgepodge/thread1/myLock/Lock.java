package com.awakeee.hodgepodge.thread1.myLock;

public interface Lock {


    void lock();

    void unlock();

    void lock(long time) throws MyLock2.TimeOutException, InterruptedException;

    void unlock(long time);

    void lock(int time) throws InterruptedException;

    void unlock(int time) throws InterruptedException;
}
