# Redisson分布式锁实现原理

### Guides
The following guides illustrate how to use some features concretely:

* [Redisson](https://redisson.org/)

分布式锁方案比较 
                       实现思路                                               优点                缺点
利用mysql的是实现方案  利用数据库自身提供的锁机制实现，要求数据库支持行级锁； 实现简单，稳定可靠   性能差，无法适应高并发场景，容易出现死锁的情况；无法优雅的实现阻塞式锁；

利用redis的实现方案   使用Setnx和lua脚本机制实现，保证对缓存操作序列的原子性  性能好                实现相对较复杂，有出现死锁的可能性；无法优雅的实现阻塞式锁；

利用zookeeper的实现方案 基于zk的节点特性及watch机制实现；                 性能好，能优雅的实现阻塞式锁；  实现相对复杂；

为什么需要锁？(一多二写三互斥)
多任务环境中才需要
任务都需要对同一共享资源进行写操作
对资源的访问是互斥的

任务通过竞争获取锁才能对改资源进行操作(竞争锁);
当有一个任务在对资源进行更新时（占有锁）,
其它任务都不可以对这个资源进行操作（任务阻塞），直到改任务完成更新。(释放锁)



