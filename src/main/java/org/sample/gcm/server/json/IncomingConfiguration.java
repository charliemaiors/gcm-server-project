package org.sample.gcm.server.json;

import java.util.LinkedHashMap;

/**
 * Created by Carlo on 20/01/2016.
 */
public class IncomingConfiguration {

    private String accountMail;
    private LinkedHashMap<String,String> configurations;

    public IncomingConfiguration(String accountMail, LinkedHashMap<String, String> configurations) {
        this.accountMail = accountMail;
        this.configurations = configurations;
    }

    public IncomingConfiguration() {
    }

    public String getAccountMail() {
        return accountMail;
    }

    public void setAccountMail(String accountMail) {
        this.accountMail = accountMail;
    }

    public LinkedHashMap<String, String> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(LinkedHashMap<String, String> configurations) {
        this.configurations = configurations;
    }
}
