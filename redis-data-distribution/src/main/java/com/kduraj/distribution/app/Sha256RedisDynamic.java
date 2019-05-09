package com.kduraj.distribution.app;

import com.google.common.hash.Hashing;
//import com.sun.xml.internal.fastinfoset.algorithm.HexadecimalEncodingAlgorithm;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.leftPad;

public class Sha256RedisDynamic {

    private String[] clusterAddress;
    Map<String, String> clusterMap = new HashMap<>();

    /**
     * Redis Dynamic Cluster Constructor
     */
    public Sha256RedisDynamic() {

        Sha256ClusterConfig config = new Sha256ClusterConfig();

        try {
            clusterAddress = config.readClusterConfig("src/main/resources/cluster.yaml");
            System.out.println(ReflectionToStringBuilder.toString(clusterAddress, ToStringStyle.JSON_STYLE));
            initializeSha256Mapping(clusterAddress.length);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Stored Key and Value
     *
     * @param key
     * @param value
     * @return
     */
    public void store_key_value(String key, String value) {


//        String sha256hex = Hashing.sha256().hashString(key, StandardCharsets.UTF_16).toString();
//        String node = sha256hex.substring(63);
//
//        Jedis jedis = new Jedis(cluster[0]);
//        jedis.set(sha256hex, value);
//        jedis.close();
//
//        return sha256hex;


        //for(String address: cluster) {
        //    System.out.println(address);
        //}

    }

    /**
     * Initialize SHA256 Mapping
     */
    private void initializeSha256Mapping(int clusterSize) {

        System.out.println("Redis Node Cluster Size: " + clusterSize);
//        int range = 4096 / clusterSize / 256;
//        System.out.println("Node Range: " + range);

        for (int i = 0; i < 4096; i++) {

            Integer node = i / clusterSize / 256;

            String hex = Integer.toHexString(i);
            String hex2 = leftPad(hex, 3, '0');

            System.out.println(hex2 + "|" + clusterAddress[node] + "|" + i  );
            clusterMap.put(hex2, clusterAddress[node]);

        }

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

        Jedis jedis = new Jedis(clusterAddress[0]);
        value = jedis.get(sha256hex);
        jedis.close();


        return value;

    }

}
