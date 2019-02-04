package com.awakeee.hodgepodge.thread.lock;

import java.util.ArrayList;
import java.util.List;

//真正的公平锁
public class RealFairLock {

	
	
	
	
	
	
	class Lock{
		private boolean isLock = false;
		private Thread lockingThread;
		private List<QueueObject> waittingThread = new ArrayList<QueueObject>();
		
		private synchronized void lock(){
			
			while(isLock){
				QueueObject queueObject = new QueueObject();
				try {
					waittingThread.add(queueObject);
					queueObject.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			isLock = true;
			lockingThread = Thread.currentThread();
		}
		
		
		private synchronized void unlock(){
			if(lockingThread != Thread.currentThread()){
				throw new IllegalMonitorStateException("Calling thread has not locked this lock");
			}
		}
	}
}
