java -Denv=PRO 
环境变量 pro
-server 
-Xms4g 
堆内存最小4g

-Xmx4g 
堆最大内存4g

-Xmn2g
堆初始化内存2g

-XX:MaxDirectMemorySize=512m
虚拟机的堆外内存512M
JVM堆内存大小可以通过-Xmx来设置，同样的direct ByteBuffer可以通过-XX:MaxDirectMemorySize来设置，
此参数的含义是当Direct ByteBuffer分配的堆外内存到达指定大小后，即触发Full GC。

-XX:MetaspaceSize=128m 
元空间的大小，不过元空间与永久代最大的区别在于：元空间并不在虚拟机中，而是使用本机内存。
因此，元空间大小仅受本地内存限制。

-XX:MaxMetaspaceSize=512m
元空间最大值

-XX:-UseBiasedLocking
禁用偏向锁

-XX:-UseCounterDecay
关闭热度开启

-XX:AutoBoxCacheMax=10240
JVM在加载Integer这个类时,会优先加载静态的代码。当JVM进程启动完毕后, 
-128 ~ +127 范围的数字会被缓存起来,调用valueOf方法的时候,如果是这个范围内的数字,则直接从缓存取出。

-XX:+UseConcMarkSweepGC
老年代使用CMS收集器（如果出现"Concurrent Mode Failure"，会使用SerialOld收集器）

-XX:CMSInitiatingOccupancyFraction=75 
触发执行CMS回收的当前年代区内存占用的百分比，负值表示使用CMSTriggerRatio设置的值

-XX:+UseCMSInitiatingOccupancyOnly
只根据占用情况作为开始执行CMS收集的标准

-XX:MaxTenuringThreshold=6
设置垃圾最大年龄。如果设置为0的话，则年轻代对象不经过Survivor区，直接进入年老代。
对于年老代比较多的应用，可以提高效率。如果将此值设置为一个较大值，则年轻代对象会在Survivor区进行多次复制，这样可以增加对象再年轻代的存活时间，增加在年轻代即被回收的概论。

-XX:+ExplicitGCInvokesConcurrent
 ExplicitGCInvokesConcurrent本身的功能：其实，也是触发full gc 只不过在CMS在full gc 效率比较高。

-XX:+ParallelRefProcEnabled
默认为false，并行的处理Reference对象，如WeakReference，除非在GC log里出现Reference处理时间较长的日志，否则效果不会很明显。

-XX:+PerfDisableSharedMem
启用标准内存使用。JVM控制分为标准或共享内存，区别在于一个是在JVM内存中，
一个是生成/tmp/hsperfdata_{userid}/{pid}文件，存储统计数据，通过mmap映射到内存中，
别的进程可以通过文件访问内容。通过这个参数，可以禁止JVM写在文件中写统计数据，代价就是jps、jstat这些命令用不了了，只能通过jmx获取数据。但是在问题排查是，jps、jstat这些小工具是很好用的，比jmx这种很重的东西好用很多，所以需要自己取舍

-XX:+AlwaysPreTouch 
在启动时把所有参数定义的内存全部捋一遍。使用这个参数可能会使启动变慢，但是在后面内存使用过程中会更快。
可以保证内存页面连续分配，新生代晋升时不会因为申请内存页面使GC停顿加长。通常只有在内存大于32G的时候才会有感觉。

-XX:-OmitStackTraceInFastThrow
不忽略重复异常的栈，这是JDK的优化，大量重复的JDK异常不再打印其StackTrace。但是如果系统是长时间不重启的系统，在同一个地方跑了N多次异常，
结果就被JDK忽略了，那岂不是查看日志的时候就看不到具体的StackTrace，那还怎么调试，所以还是关了的好。

-XX:+ExplicitGCInvokesConcurrent
ExplicitGCInvokesConcurrent本身的功能：其实，也是触发full gc 只不过在CMS在full gc 效率比较高。

-XX:+ParallelRefProcEnabled
默认为false，并行的处理Reference对象，如WeakReference，除非在GC log里出现Reference处理时间较长的日志，否则效果不会很明显。

-XX:+HeapDumpOnOutOfMemoryError
发生内存溢出是进行heap-dump

-XX:HeapDumpPath=/home/devjava/logs/ 
这个参数与-XX:+HeapDumpOnOutOfMemoryError共同作用，设置heap-dump时内容输出文件。

-Xloggc:/home/devjava/logs/lifecircle-tradecore-gc.log
gc日志输出目录

-XX:+PrintGCApplicationStoppedTime
输出gc时应用停止时间

-XX:+PrintGCDateStamps
输出GC的时间戳（以以日期的形式，如2019-03-01T12:57:54.486+0800）
 
-XX:+PrintGCDetails 
发生垃圾收集时打印详细的内存回收日志

-javaagent:/home/devjava/ArmsAgent/arms-bootstrap-1.7.0-SNAPSHOT.jar -jar 
/home/devjava/lifecircle-tradecore/app/lifecircle-tradecore.jar