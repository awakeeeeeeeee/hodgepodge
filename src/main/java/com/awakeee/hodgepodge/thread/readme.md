1.Runnable对象可以方便多线程资源的共享
2.Thread无法多继承


线程之间通信 communication

1、通过共享对象通信
2、忙等待(Busy Wait)
3、wait(),notify()和notifyAll()
4、丢失的信号（Missed Signals）
    例子3中,是将通知信号存在成员变量中，所有唤醒在等待的线程没有问题,如果通知信号不通过成员变量保存,那么如果唤醒线程优先于等待线程执行，等待线程
    将永远不会被唤醒
5、多个线程等待相同信号 
    notifyAll()
    
6.在wait()/notify()机制中，不要使用全局对象，字符串常量等。应该使用对应唯一的对象    