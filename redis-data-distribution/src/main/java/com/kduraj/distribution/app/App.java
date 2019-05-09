package com.kduraj.distribution.app;

import java.util.Random;

/**
 * Redis Distributed Data - proof-of-concept
 */
public class App {

    static Random random = new Random();
    final static int THREADS = 64;

    /**
     * Main Function
     *
     * @param args
     */
    public static void main(String[] args) {

//        Sha256RedisDynamic dynamic = new Sha256RedisDynamic();
//        dynamic.store_key_value("key1","value1");
//
//        Sha256ClusterConfig config = new Sha256ClusterConfig();
//        config.readClusterConfig("src/main/resources/cluster.yaml");

        // BenchmarkElastiCache();
	BenchmarkRedisSha256();
    }


    /**
     * Benchmark Multi Thread ElastiCache
     * Spanning multiple threads
     */
    private static void BenchmarkElastiCache() {

        ElastiCache redis[] = new ElastiCache[THREADS];

        for (int i = 0; i < THREADS; i++) {

            redis[i] = new ElastiCache();
            Thread thread = new Thread(redis[i]);
            thread.start();
        }

    }


    /**
     * Benchmark SHA256 Distribution
     * Spanning multiple threads
     */
    private static void BenchmarkRedisSha256() {

        //int count = Integer.parseInt(args[0]);
        long start = System.currentTimeMillis();

        /*---------------------------------------------------*/
        // Spanning multiple threads
        /*---------------------------------------------------*/
        Sha256RedisStatic redis[] = new Sha256RedisStatic[THREADS];

        for (int i = 0; i < THREADS; i++) {

            redis[i] = new Sha256RedisStatic();
            Thread thread = new Thread(redis[i]);
            thread.start();

        }

        /*---------------------------------------------------*/
        long end = System.currentTimeMillis();
        System.out.println("Time Taken: " + (end - start) + " milliseconds");

    }

    /**
     * Benchmark Single Thread ElastiCache
     */
    private static void BenchmarkSingleThreadElastiCache() {

        ElastiCache redis1 = new ElastiCache();
        Thread thread1 = new Thread(redis1);
        thread1.start();

    }

    /**
     * Generate Random Strings
     *
     * @param len
     * @return
     */
    public static String getRandomString(int len) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            sb.append((char) (random.nextInt(24) + 'a'));
        }
        return sb.toString();
    }

}

/*---------------------------------------------------*/
// Spinning a single thread
/*---------------------------------------------------*/
// Sha256RedisStatic redis1 = new Sha256RedisStatic();
// Thread thread1 = new Thread(redis1);
// thread1.start();
