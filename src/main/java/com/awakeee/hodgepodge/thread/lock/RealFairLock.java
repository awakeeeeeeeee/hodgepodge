package com.awakeee.hodgepodge.thread.lock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//嵌套管程锁死(Nested Monitor Lockout
//真正的公平锁 目前还存在问题，第一个线程执行到unlock的时候 程序就没响应了，暂时不知道为什么
//知道为什么了 首先下面的实现并非是公平锁 属于嵌套管程死锁 需要注意的是lock和unlock两个方法都是synchronized修饰的,
//那么当线程0执行完lock后，准备开始执行unlock时，线程1已经执行到lock，并持有了lock的锁对象，还未释放，导致线程0无法执行unlock
//但是线程1却还在等待线程0执行完unlock后唤醒线程1，这样就造成了嵌套管死锁

//嵌套管程锁死 VS 死锁
//嵌套管程锁死与死锁很像：都是线程最后被一直阻塞着互相等待。
//
//但是两者又不完全相同。在死锁中我们已经对死锁有了个大概的解释，死锁通常是因为两个线程获取锁的顺序不一致造成的，线程1锁住A，等待获取B，线程2已经获取了B，再等待获取A。如死锁避免中所说的，死锁可以通过总是以相同的顺序获取锁来避免。
//但是发生嵌套管程锁死时锁获取的顺序是一致的。线程1获得A和B，然后释放B，等待线程2的信号。线程2需要同时获得A和B，才能向线程1发送信号。所以，一个线程在等待唤醒，另一个线程在等待想要的锁被释放。
//
//不同点归纳如下：
//
//死锁中，二个线程都在等待对方释放锁。
//
//嵌套管程锁死中，线程1持有锁A，同时等待从线程2发来的信号，线程2需要锁A来发信号给线程1。

//************************如果需要实现公平锁，只需把lock方法的synchronized拿掉即可

public class RealFairLock {
	
	Lock lock = new Lock();
	
	 @Test
	    public void testLock() throws InterruptedException {
	        Proces p = new Proces();

	        Thread t = new Thread(p);
	        Thread t1 = new Thread(p);
	        Thread t2 = new Thread(p);

	        t.start();
	        t1.start();
	        t2.start();

	        t.join();
	        t1.join();
	        t2.join();

	    }
	
	
	class Lock{
		private boolean isLock = false;
		private Thread lockingThread = null;
		private List<QueueObject> waittingThread = new ArrayList<QueueObject>();
		
		private synchronized void lock() throws InterruptedException{
			
				QueueObject queueObject = new QueueObject();
				boolean isLockForCurrentThread = true;
				synchronized(this){
					//System.out.println(Thread.currentThread().getName()+" run here"+" queueObject:"+queueObject);
					waittingThread.add(queueObject);
				}
				//System.out.println("size:"+waittingThread.size());
				while(isLockForCurrentThread){
					synchronized(this){
						isLockForCurrentThread = isLock || waittingThread.get(0) != queueObject;
						//System.out.println(Thread.currentThread().getName()+" run here"+ " "+isLockForCurrentThread+" waittingsize:"+waittingThread.size());
						//System.out.println(Thread.currentThread().getName()+" run here"+" queueObject:"+queueObject);
						if(!isLockForCurrentThread){
							isLock = true;
							lockingThread = Thread.currentThread();
							waittingThread.remove(queueObject);
							return;
						}
					}
					try {
						queueObject.doWait();
					} catch (InterruptedException e) {
						synchronized(this) { 
							waittingThread.remove(queueObject); 
						}
						throw e;
					}
				}
			}
		
		
		private synchronized void unlock(){
			//System.out.println(Thread.currentThread().getName()+" "+"size:"+waittingThread.size());
			if(lockingThread != Thread.currentThread()){
				throw new IllegalMonitorStateException("Calling thread has not locked this lock");
			}
			isLock = false;
			lockingThread = null;
			if(waittingThread.size()>0){
				waittingThread.get(0).doNotify();
			}
		}
	}
	
	class Proces implements Runnable{
        @Override
        public void run() {
            try {
            	//System.out.println(Thread.currentThread().getName()+" is alive:"+Thread.currentThread().getState().toString());
				lock.lock();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            for(int i=0;i<3;i++){
                System.out.println("i:"+i +" "+Thread.currentThread().getName()+" is working");
            }
            try {
				Thread.sleep(50);//加上sleep可以更好的才看出效果，因为线程1可能在线程2和线程3还未进入到wait的时候
				//就已经执行结束了，如果这样的话 在wait前面的打印语句就不会被执行
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //System.out.println(Thread.currentThread().getName()+" start unlock");
            //System.out.println(Thread.currentThread().getName()+" is alive:"+Thread.currentThread().getState().toString());
            lock.unlock();
            //System.out.println(Thread.currentThread().getName()+" end unlock");
        }
    }
}
