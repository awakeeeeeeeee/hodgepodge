并发编程的几种设计模式：

Single Threaded Execution —— 能通过这座桥的只有一个人
所谓“Single Threaded Execution”，即“以一个线程执行”，该模式用于设置限制，以确保同一时间内只让一个线程执行处理。

 

Immutable —— 想破坏也破坏不了
Immutable模式中存在着确保实例状态不发生改变的类（immutable类）。在访问这些实例时并不需要执行耗时的互斥处理，因此若能巧妙利用该模式，定能提高程序性能。

 

Guarded Suspension —— 等我准备好
如果执行现在的处理会造成问题，就让执行处理的线程等待，通过让线程等待来保证实例的安全性。

 

Balking —— 不需要就算了
如果现在不合适执行这个操作，或者没必要执行这个操作，就停止处理，直接返回。

 

Producer-Consumer —— 我来做，你来用
Producer：生成数据的线程

Consumer：使用数据的线程

Producer-Consumer模式在生产者和消费者之间加入了一个“桥梁角色”，该桥梁用于消除线程间处理速度的差异。

 

Read-Write Lock —— 大家一起读没问题，但读的时候不要写
当线程读取实例的状态时，实例的状态不会变化。实例的状态仅在线程执行写入操作时才会发生变化。

 

Thread-Per-Message —— 这项工作就交给你了
为每个命令或请求新分配一个线程，由这个线程来执行处理。

 

Worker Thread —— 工作没来就一直等，工作来来就干活
在Worker Thread模式中，工人线程（work thread）会逐个取回工作并进行处理。当所有工作全部完成后，工人线程会等待新的工作到来。

 

Future —— 先给你提货单
Future的意思是未来、期货。假设有一个方法需要花费很长时间才能获取运行结果，那么，与其一直等待结果，不如先拿一张“提货单”。这里的“提货单”就称为Future角色，是“未来”可以转化为实物的凭证。

 

Two-Phase Termination —— 先收拾房间再睡觉
开始 -> 操作中 -> 终止处理中 -> 终止

先从“操作中”状态变为“终止处理中”的状态，然后再真正地终止线程。

 

Thread-Specific Storage —— 一个线程一个储物柜
即使只有一个入口，也会在内部为每个线程分配特有的存储空间的模式。

 

Active Object —— 接收异步消息的主动对象
在Active Object模式中出场的主动对象可不仅仅“有自己特有的线程”。它同时还具有可以从外部接收和处理异步消息并根据需要返回处理结果的特征。

Active Object模式中的主动对象会通过自己特有的线程在合适的时机处理从外部接收到的异步消息。

Active Object模式综合来Producer-Consumer模式、Thread-Per Message模式、Future模式等各种模式，有时也被称为Actor模式。
