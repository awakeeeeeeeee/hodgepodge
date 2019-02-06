package com.awakeee.hodgepodge.thread.lock;

public class QueueObject {
	
	boolean isNoify = false;
	
	
	public synchronized void doWait() throws InterruptedException {
		while(!isNoify){
			this.wait();
		}
		this.isNoify = false;
	}

	public synchronized void doNotify() {
		this.isNoify = true;
		this.notify();
	}
	
	public boolean equals(Object o) {
    	return this == o;
	}

	


}
