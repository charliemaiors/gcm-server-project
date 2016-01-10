package org.sample.gcm.server.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Carlo on 10/01/2016.
 */
@Entity
public class Account implements Serializable {

    @Id
    private String id;
    private String accountName;
    private String accountMail;
    @ElementCollection(fetch = FetchType.EAGER,targetClass = String.class)
    private Set<String> registrationIds;
    @ElementCollection(fetch = FetchType.EAGER,targetClass = Configuration.class)
    private List<Configuration> configurations;

    @PrePersist
    private void ensureId(){
        this.id = UUID.randomUUID().toString();
    }

    public Account(String accountName, String accountMail, Set<String> registrationIds, List<Configuration> configurations) {
        this.accountName = accountName;
        this.accountMail = accountMail;
        this.registrationIds = registrationIds;
        this.configurations = configurations;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountMail() {
        return accountMail;
    }

    public void setAccountMail(String accountMail) {
        this.accountMail = accountMail;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }

    public LinkedHashMap<String, String> toCustomData(){

        LinkedHashMap<String, String> res = new LinkedHashMap<String, String>();

        for(Configuration config: configurations){
            res.put(config.getConfigurationName(),config.getConfigurationValue());
        }

        return res;
    }
}
