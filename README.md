# Redis SHA256 Data Distribution 

This project is proof-of-concept for Distributing Data based on SHA256 algorithm among many single Redis nodes.

### Redis Single Node Installation ###
```
$ curl -O http://download.redis.io/releases/redis-4.0.9.tar.gz
$ tar xzvf redis-4.0.9.tar.gz
$ cd redis-4.0.9
$ make
$ make install 

$ src/redis-server
$ src/redis-cli 
```

### Benefits of SHA256 data distribution 

1. Disabling Redis cluster, we prevent nodes moving data buckets between the nodes and thus prevent latency. 

2. Disabling Redis cluster, we unload Master / Slave overhead of managing the cluster to the Client application.

3. Disabling Redis cluster,  we will save on memory usage and less cluster will need it to cache data because Slaves will be eliminated and no longer keep a copy of the master data.

4. Using SHA256 we are achieving uniform data distribution among al Redis nodes

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Sample Output 

```
Redis Distributed Data
-----------------------------------------------------------------------
Storing Key and Value on [ Node:e IP=192.168.0.30 ]

Key = mtss-dev.com.endplay.feeds.model.impl.OBFeedSitemapImpl
HashedKey = 3b1b0f4645eea9670500e38c8e18361080ce691092e9eaea95e468eb587db40e
Value = Fox|SHA256|Breaking News|Discover Los Angeles|California

-----------------------------------------------------------------------
Retrieving Value from [ Node:e IP=192.168.0.30 ]

HashedKey = 3b1b0f4645eea9670500e38c8e18361080ce691092e9eaea95e468eb587db40e
Value = Fox|SHA256|Breaking News|Discover Los Angeles|California
```
