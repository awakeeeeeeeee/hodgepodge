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


锁

锁的公平性

Java的synchronized块并不保证尝试进入它们的线程的顺序。因此，如果多个线程不断竞争访问相同的synchronized同步块，就存在一种风险，其中一个或多个线程永远也得不到访问权 —— 也就是说访问权总是分配给了其它线程。这种情况被称作线程饥饿。为了避免这种问题，锁需要实现公平性。本文所展现的锁在内部是用synchronized同步块实现的，因此它们也不保证公平性。饥饿和公平中有更多关于该内容的讨论。

在finally语句中调用unlock()
如果用Lock来保护临界区，并且临界区有可能会抛出异常，那么在finally语句中调用unlock()就显得非常重要了。这样可以保证这个锁对象可以被解锁以便其它线程能继续对其加锁。以下是一个示例：

lock.lock();
try{
    //do critical section code,
    //which may throw exception
} finally {
    lock.unlock();
}
这个简单的结构可以保证当临界区抛出异常时Lock对象可以被解锁。如果不是在finally语句中调用的unlock()，当临界区抛出异常时，Lock对象将永远停留在被锁住的状态，这会导致其它所有在该Lock对象上调用lock()的线程一直阻塞。