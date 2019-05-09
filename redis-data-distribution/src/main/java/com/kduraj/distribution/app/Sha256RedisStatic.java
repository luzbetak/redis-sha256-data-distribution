package com.kduraj.distribution.app;

import com.google.common.hash.Hashing;
import redis.clients.jedis.Jedis;

import java.nio.charset.StandardCharsets;

import static com.kduraj.distribution.app.App.getRandomString;

public class Sha256RedisStatic implements Runnable {

    private String[] cluster = {
            "172.31.40.210",
            "172.31.33.178",
            "172.31.25.86",
            "172.31.11.125",
            "172.31.14.189"
    };

    private Jedis jedis0 = new Jedis(cluster[0]);
    private Jedis jedis1 = new Jedis(cluster[1]);
    private Jedis jedis2 = new Jedis(cluster[2]);
    private Jedis jedis3 = new Jedis(cluster[3]);
    private Jedis jedis4 = new Jedis(cluster[4]);

    // private Jedis jedis5 = new Jedis("172.31.14.189");

    @Override
    public void run() {
        System.out.println("Thread is running:");

        for (int i = 1; i <= 250000; i++) {

            String key = getRandomString(32);
            String value = getRandomString(64);
            String sha256hex = store_key_value(i, key, value);
//          String value2 = redis.retrieve_key_value(sha256hex);
        }
    }


    /**
     * Stored Key and Value
     *
     * @param key
     * @param value
     * @return
     */
    public String store_key_value(int i, String key, String value) {

        String sha256hex = Hashing.sha256().hashString(key, StandardCharsets.UTF_16).toString();
        String node = sha256hex.substring(63);

        if ((i % 1000) == 0) {
            System.out.println(i + " HashedKey = " + sha256hex + "\nKey = " + key + " | Value = " + value);
        }

        //Jedis jedis = new Jedis("172.31.14.189");
        //jedis.set(sha256hex, value);
        //jedis.close();


        // for(int x=0; x<=100; x++) System.out.print("-"); System.out.println();
        // System.out.print("Storing Key and Value on [ Node:");

        if ((node.equals("0")) || (node.equals("1")) || (node.equals("2"))) {
            if ((i % 1000) == 0) {
                System.out.println(node + " IP = " + cluster[0] + " ]");
            }
            jedis0.set(sha256hex, value);

        } else if ((node.equals("3")) || (node.equals("4")) || (node.equals("5"))) {
            if ((i % 1000) == 0) {
                System.out.println(node + " IP = " + cluster[1] + " ]");
            }
            jedis1.set(sha256hex, value);

        } else if ((node.equals("6")) || (node.equals("7")) || (node.equals("8"))) {
            if ((i % 1000) == 0) {
                System.out.println(node + " IP = " + cluster[2] + " ]");
            }
            jedis2.set(sha256hex, value);

        } else if ((node.equals("9")) || (node.equals("a")) || (node.equals("b"))) {
            if ((i % 1000) == 0) {
                System.out.println(node + " IP = " + cluster[3] + " ]");
            }
            jedis3.set(sha256hex, value);

        } else if ((node.equals("c")) || (node.equals("d")) || (node.equals("e")) || (node.equals("f"))) {
            if ((i % 1000) == 0) {
                System.out.println(node + " IP = " + cluster[4] + " ]");
            }
            jedis4.set(sha256hex, value);

        }

        return sha256hex;

    }

    /**
     * Retrieve Key and Value
     *
     * @param sha256hex
     * @return
     */
    public String retrieve_key_value(String sha256hex) {

        String value = null;
        String node = sha256hex.substring(63);
        for (int i = 0; i <= 100; i++) System.out.print("-");
        System.out.println();
        System.out.print("Retrieving Value from [ Node:");

        if ((node.equals("0")) || (node.equals("1")) || (node.equals("2")) || (node.equals("3"))) {
            System.out.println(node + " IP = " + cluster[0] + " ]");
            Jedis jedis = new Jedis(cluster[0]);
            value = jedis.get(sha256hex);
            jedis.close();

        } else if ((node.equals("4")) || (node.equals("5")) || (node.equals("6"))) {
            System.out.println(node + " IP = " + cluster[1] + " ]");
            Jedis jedis = new Jedis(cluster[1]);
            value = jedis.get(sha256hex);
            jedis.close();

        } else if ((node.equals("7")) || (node.equals("8")) || (node.equals("9"))) {
            System.out.println(node + " IP = " + cluster[2] + " ]");
            Jedis jedis = new Jedis(cluster[2]);
            value = jedis.get(sha256hex);
            jedis.close();

        } else if ((node.equals("a")) || (node.equals("b")) || (node.equals("c"))) {
            System.out.println(node + " IP = " + cluster[3] + " ]");
            Jedis jedis = new Jedis(cluster[3]);
            value = jedis.get(sha256hex);
            jedis.close();

        } else if ((node.equals("d")) || (node.equals("e")) || (node.equals("f"))) {
            System.out.println(node + " IP = " + cluster[3] + " ]");
            Jedis jedis = new Jedis(cluster[3]);
            value = jedis.get(sha256hex);
            jedis.close();
        }

        System.out.println("\nHashedKey = " + sha256hex + "\nValue = " + value + "\n");

        return value;

    }


}
