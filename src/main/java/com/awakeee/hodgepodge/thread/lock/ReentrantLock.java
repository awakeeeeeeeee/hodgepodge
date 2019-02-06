package com.awakeee.hodgepodge.thread.lock;

//可重入锁
//outer和inner是可重入的，即可以在outer中调用inner,因为都属于同一个对象的锁

//注意outer()和inner()都被声明为synchronized，这在Java中和synchronized(this)块等效。
//如果一个线程调用了outer()，在outer()里调用inner()就没有什么问题，
//因为这两个方法（代码块）都由同一个管程对象（”this”)所同步。如果一个线程已经拥有了一个管程对象上的锁，
//那么它就有权访问被这个管程对象同步的所有代码块。这就是可重入。线程可以进入任何一个它已经拥有的锁所同步着的代码块。
public class ReentrantLock {
	
	public synchronized void outer(){
		
	}
	
	public synchronized void inner(){
		
	}
	
	//不可重入
	public class Lock{
		private boolean isLock = false;
		
		private synchronized void lock() throws InterruptedException{
			while(isLock){
				wait();
			}
			isLock = true;
		}
		
		private synchronized void unlock(){
			isLock = false;
			notify();
		}
		
	}
	
	//可重入锁
//	注意到现在的while循环（自旋锁）也考虑到了已锁住该Lock实例的线程。如果当前的锁对象没有被加锁(isLocked = false)，或者当前调用线程已经对该Lock实例加了锁，那么while循环就不会被执行，调用lock()的线程就可以退出该方法（译者注：“被允许退出该方法”在当前语义下就是指不会调用wait()而导致阻塞）。
//	除此之外，我们需要记录同一个线程重复对一个锁对象加锁的次数。否则，一次unblock()调用就会解除整个锁，即使当前锁已经被加锁过多次。在unlock()调用没有达到对应lock()调用的次数之前，我们不希望锁被解除。
//	现在这个Lock类就是可重入的了。
	public class Lock1{
		private boolean isLock = false;
		Thread lockBy = null;
		int lockCount = 0;
		
		private synchronized void lock() throws InterruptedException{
			while(isLock && lockBy != Thread.currentThread()){
				wait();
			}
			isLock = true;
			lockCount++;
			lockBy = Thread.currentThread();
		}
		
		private synchronized void unlock(){
			if(Thread.currentThread() == lockBy){
				lockCount--;
			}
			if(lockCount == 0){
				isLock = false;
				notify();
			}
		}
		
	}
}
