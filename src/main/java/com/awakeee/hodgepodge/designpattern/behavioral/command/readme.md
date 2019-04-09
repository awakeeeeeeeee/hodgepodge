命令模式

java中的runnable接口就类似与命令模式的接口，实现了该接口的类可以被理解为具体的行为，而发出命令的则是thread类，thread可以理解为调用者，
可以发出具体的命令，比如:start,run,join,interrupt等等来控制命令，也就是runnable的执行

这里代码中fileinvoker就相当于是thread调用者,去调用command的执行

命令模式和策略模式的区别:
策略模式和命令模式的其中一个最大区别，是在于：策略模式对付的问题域通常是一个，就是说，多个策略只是处理同一个问题，而命令模式对付的是多个问题域，就是很多不同的命令来做不同的事情。

Typically the Command pattern is used to make an object out of what needs to be done -- to take an operation and its arguments and wrap them up in an object to be logged, held for undo, sent to a remote site, etc. There will tend to be a large number of distinct Command objects that pass through a given point in a system over time, and the Command objects will hold varying parameters describing the operation requested.

The Strategy pattern, on the other hand, is used to specify how something should be done, and plugs into a larger object or method to provide a specific algorithm. A Strategy for sorting might be a merge sort, might be an insertion sort, or perhaps something more complex like only using merge sort if the list is larger than some minimum size. Strategy objects are rarely subjected to the sort of mass shuffling about that Command objects are, instead often being used for configuration or tuning purposes.

Both patterns involve factoring the code and possibly parameters for individual operations out of the original class that contained them into another object to provide for independent variability. The differences are in the use cases encountered in practice and the intent behind each pattern.