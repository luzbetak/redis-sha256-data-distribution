package com.kduraj.distribution.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class Sha256ClusterConfig {

    private String name;
    private int nodes;
    private String[] address;
    private Map<String, String> roles;


    /**
     * Read Cluster Config
     *
     * @param fileName
     */
    public String[] readClusterConfig(String fileName) throws IOException {

        File file = new File(fileName);
        String absolutePath = file.getAbsolutePath();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());


        Sha256ClusterConfig config = mapper.readValue(new File(absolutePath), Sha256ClusterConfig.class);
        System.out.println(ReflectionToStringBuilder.toString(config, ToStringStyle.JSON_STYLE));
        //DisplayHashMap(config.getAddress());
        return config.getAddress();


    }

    /**
     * Display Hash Map
     *
     * @param map
     */
    public void DisplayHashMap(Map<String, String> map) {

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " => " + value);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNodes() {
        return nodes;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public Map<String, String> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, String> roles) {
        this.roles = roles;
    }
}

