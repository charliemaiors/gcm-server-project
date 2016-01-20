package org.sample.gcm.server.json;

import java.util.LinkedHashMap;

/**
 * Created by Carlo on 20/01/2016.
 */
public class IncomingConfiguration {

    private String accountName;
    private LinkedHashMap<String,String> configurations;

    public IncomingConfiguration(String accountName, LinkedHashMap<String, String> configurations) {
        this.accountName = accountName;
        this.configurations = configurations;
    }

    public IncomingConfiguration() {
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public LinkedHashMap<String, String> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(LinkedHashMap<String, String> configurations) {
        this.configurations = configurations;
    }
}
