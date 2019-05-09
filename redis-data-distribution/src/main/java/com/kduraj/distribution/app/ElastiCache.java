package com.kduraj.distribution.app;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;

import static com.kduraj.distribution.app.App.getRandomString;

/**
 * List of Redis Clusters on Amazon AWS
 * <p>
 * platform-r4large-3x2.fgaozn.clustercfg.use1.cache.amazonaws.com
 * platform-r4large-4x1.fgaozn.clustercfg.use1.cache.amazonaws.com
 */
public class ElastiCache implements Runnable {


    // HostAndPort node = new HostAndPort("platform-r4large-4x1.fgaozn.clustercfg.use1.cache.amazonaws.com", 6379);
    HostAndPort node = new HostAndPort("platform-r4large-3x2.fgaozn.clustercfg.use1.cache.amazonaws.com", 6379);
    JedisCluster cluster = new JedisCluster(node);

    @Override
    public void run() {

        System.out.println("Thread is running:");

        for (int i = 1; i <= 250000; i++) {

            String key = getRandomString(32);
            String value = getRandomString(64);
            store_key_value(i, key, value);
//          String value2 = redis.retrieve_key_value(sha256hex);
        }
        disconnect();
    }

    public void store_key_value(int i, String key, String value) {

        if ((i % 1000) == 0) {
            System.out.println(i + " Key = " + key + "\nValue = " + value);
        }
        cluster.set(key, value);

    }

    public void disconnect() {

        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public String retrieve_key_value(String key) {
//        Jedis jedis = new Jedis(cluster[0]);
//        String value = jedis.get(key);
//        jedis.close();
//        return value;
//    }

}
