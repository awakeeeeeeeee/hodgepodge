git submodule/git subtree 
将protobuf或者grpc生成的代码添加到仓库中，方便多个客户端和服务端之间进行代码的同步

google protobuf 序列化与反序列化

google grpc 序列化与反序列化还包含client和serve

apache thrift  序列化与反序列化还包含client和serve
传输格式、传输方式、服务模型

netty 自定义协议/protobuf 动态传递消息 (oneof,枚举)

JNI内存拷贝 java native interface

在Java代码中，Java对象被存放在JVM的Java Heap，由垃圾回收器（Garbage Collector，即GC）自动回收就可以。
在Native代码中，内存是从Native Memory中分配的，需要根据Native编程规范去操作内存。如：C/C++使用malloc()/new分配内存，需要手动使用free()/delete回收内存。
然而，JNI和上面两者又有些区别。
JNI提供了与Java相对应的引用类型（如：jobject、jstring、jclass、jarray、jintArray等），以便Native代码可以通过JNI函数访问到Java对象。引用所指向的Java对象通常就是存放在Java Heap，而Native代码持有的引用是存放在Native Memory中。

jstring jstr = env->NewStringUTF("Hello World!");
jstring类型是JNI提供的，对应于Java的String类型
JNI函数NewStringUTF()用于构造一个String对象，该对象存放在Java Heap中，同时返回了一个jstring类型的引用。
String对象的引用保存在jstr中，jstr是Native的一个局部变量，存放在Native Memory中。



nio 零拷贝

https://medium.com/@xunnan.xu/its-all-about-buffers-zero-copy-mmap-and-java-nio-50f2a1bfc05c

scatter/gather dma 

dma:直接内存访问 direct memory access

https://www.cnblogs.com/tubujia/p/11310208.html










![Image text](/Users/cool/numb/book/reactor.png)





![Image text](/Users/cool/numb/book/scalable_io_in_java.png)

Reactor

reactor模式的角色构成（5种）


1.handler 句柄或者文件描述符，本质表示一种资源，由操作系统提供，该资源表示一个个的事件，比如文件描述符或是网络编程中的socket描述符。
事件既可以来自于外部也可以来自于内部，外部事件比如客户端的连接请求，客户端发送过来的数据等，内部事件比如是操作系统产生的定时器事件，它本质上就是一个文件描述符， handler是事件产生的发源地。
2.synchronous event demultiplexer 同步事件分离器 ，它本身是一个系统调用，用于等待事件的发生，事件可能是一个或者多个，调用方在调用它的时候会阻塞，只到事件分离器上有事件产生，同步事件分离器指的
就是常用的I/0多路复用机制，比如说select，poll，epoll，在java nio中指的就是selector，对应的阻塞方法就是select。
3.event handler 事件处理器，本身由多个回调方法构成，这些回调方法构成了与应用相关的对于某个事件的反馈机制，netty相比于nio，在事件处理器上进行了升级，为我们提供了大量的回调方法，供我们在特定的事件产生时，实现相应的
回调方法实现我们自己的业务处理。
4.concrete event handler 具体事件处理器，是事件处理器的实现，相当于我们自己实现的handler。
5.initiation dispatcher 初始分发器 实际上就是reactor对象，它本身定义了一些规范，这些规范用于控制事件的调度方式，同时又提供了应用进行 事件处理器的注册，删除等设施，它本身是整个事件处理器的核心所在，它会通过同步
事件分离器来等待事件发送，一旦有事件发生，它首先会分离出每一个事件，然后调用事件处理器，最后调用相关的回调方法来处理这些事件


如何实现一个事件驱动模型？
主要利用到观察者模式
主要包含4个基本组件：
事件队列    接受事件的入口，存储待处理事件
分发器 将不同的事件分发到不同的业务逻辑单元
事件通道    分发器与处理器之间的联系渠道
事件处理器   实现业务逻辑，处理完成后会发出事件，触发下一步操作


ChannelPipline
Intercepting Filter模式，拦截器模式


if (eventLoop.inEventLoop()) { //判断当前运行注册的是thrad和当前eventloop中的thead是不是同一个
                register0(promise);
            } else {
                try {
                    eventLoop.execute(new Runnable() {
                        @Override
                        public void run() {
                            register0(promise);
                        }
                    });
                } catch (Throwable t) {
                    logger.warn(
                            "Force-closing a channel whose registration task was not accepted by an event loop: {}",
                            AbstractChannel.this, t);
                    closeForcibly();
                    closeFuture.setClosed();
                    safeSetFailure(promise, t);
                }
            }
            

1.一个EventLoopGroup当中会包含一个或多个EventLoop
2.一个EventLoop在它的整个生命周期中都只会与唯一一个Thread进行绑定
3.所有由EventLoop处理的各种I/0事件都将在它所关联的那个Thread上进行处理
4.一个Channel在它的整个生命周期中只会注册到一个EventLoop上
5.一个EventLoop在运行过程当中，会被分配一个或多个Channel
6.每一个EventLoop都会维护一个单独的任务队列,执行绑定的一个或多个Channel



通过上面的3,4两点，可以得知channel是线程安全的，基于此，我们可以存储一个channel的引用，并且在需要向远端发送数据时，通过这个引用来调用channel
相关的方法，即便当时有很多线程都在使用它也不会出现多线程的问题，而且消息一定会按照顺序发送出去,

日常开发中，不要在channel中执行比较耗时的操作，否则会阻塞该channel所注册的eventloop上其他所有channel的任务执行
通过有两种解决方式:
1.在channelHandler的回调方法中，使用自己定义的业务线程池去执行
2.借助于netty提供的向channelPipline添加ChannelHandler时调用的addLast方法来传递EventExecutor,这样业务的执行就转到了EventExecutorGroup
中


适配器模式
simpleChannelInboundHandler ---> channelHandlerAdapter

模版模式
channelRead0

referenceCounted 引用计数


在netty中有两种发送消息的方式，可以用channel.write也可以用channelHandlerContext.write
一个channelPipline中有多个hanndler，通过addLast进行添加，类似于spring多个拦截器的chain
前一种方式消息会从channelPipline中的最后一个hanndler开始往前写
后一种方式消息会从channelPipline中当前hanndler的下一个hanndler开始往前写

所以
1。对于channelHandlerContext和channelHandler之间的关联绑定关系是永远不会变的，因此对其进行缓存是没有任何问题的
2。对于与channel的同名的方法来说，channelHandlerContext的方法将产生更短的事件流，所以我们应该在可能的情况下利用这个特性来提升应用性能


一种特殊一点的使用场景：
client---->server---->server1

server即是服务端也是客户端，这种情况下，比较好的处理方式是通过一个eventloop来处理数据的写入和输出
伪代码：
    void channdelActive(ChannelHandlerContext ctx){
           BootStrp bootstrp = ....
           bootstrp.channel(NioSocketHandler.class).handler(
                bootstrp.group(ctx.channel().eventLoop());
                bootstrp.connect().....
           )
    }


jdk提供的future只能通过手工的方式检查执行结果，而整个操作是会阻塞的，netty则对channelfuture进行了增强，通过channelfuturelistener以
回调的方式来获取执行结果，去除了手工检查的阻塞过程，需要注意的是，channelfuturelistener的operationcomplete方法是由i/o线程执行的，因此
不要在这里执行耗时操作



netty bytebuf 提供的三种缓冲区类型：
1。heap buffer

最常用的类型 bytebuff 将数据存储在jvm堆空间上，并且将实际的数据存放到byte array中实现
优点：由于数据是存在jvm上，因此可以快速的创建或者释放，并且它提供了直接访问内部字节数组的方法
缺点：每次读写数据时，都需要先将数据复制到直接缓冲区中再进行网络传输

2。direct buffer
在堆之外直接分配内存空间，直接缓冲区并不会占用堆的容量空间，因为它是由操作系统在本地内存进行的数据分配
优点：在使用socket传输时，性能非常好，因为数据直接位于操作系统的本地内存中，所以不需要从jvm将数据复制到直接缓冲区，性能很好
缺点：因为direct buff 是直接在操作系统内存中，所以内存空间的分配和释放比堆空间更复杂，而且速度要慢一些

netty 通过提供内存池来解决这个问题，直接缓冲区并不支持通过字节数组的方式来访问
重点：对于后端的业务消息的编解码来说，推荐使用heap buffer,对于i/o通信线程来读写缓冲区时，推荐使用direct buffer

3。composite buffer(复合缓冲区)

java nio中的bytebuffer不能自动扩容，声明的大小是多少就是多少，需要自己实现，重新创建新的bytebuffer，复制旧的数据到新的对象中
netty 中的bytebuf可以自动扩容，最大值=Integer.MAX_VALUE

tcp粘包和拆包

netty的编解码器的重要结论：
1。无论是编码器还是解码器，其所接受的消息类型必须要与待处理的参数类型一致，否则该编码器或解码器不会执行
2。在解码器进行数据解码时，一定要判断缓冲(bytebuf)中的数据是否足够，否则会产生数据不正确的问题

LineBasedFrameDecoder //行解码器
FixdLengthBaseFrameDecoder //固定长度解码器
DelimiterBaseFrameDecoder //分隔符解码器
LengthFieldBaseFrameDecoder //长度解码器 重点 解码带有消息头和消息体常用








ASCII (American Stander Code for Information Interchange)美国信息交换标准代码

7bit表示一个字符，最多可以表示127个字符

ISO-8859-1
8bit表示一个字符，即用一个字节(byte) (8bit)表示一个字符，最多可以表示256个字符

unicode
包含世界上所有语言对应的编码
16进制表示形式
两个字节表示一个字符

UTF (Unicode Translation Format)

unicode是一种编码方式，utf是一种存储方式,utf-8是unicode的一种实现方式(utf-16,utf-32)

utf-8
一个英文用一个字节表示
一个中文一般用三个字节表示




