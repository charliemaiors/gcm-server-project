package org.sample.gcm.server.persistence;

import org.sample.gcm.server.json.CustomData;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Carlo on 10/01/2016.
 */
@Entity
public class Account implements Serializable {

    @Id
    private String id;
    private String accountMail;
    @ElementCollection(fetch = FetchType.EAGER,targetClass = String.class)
    private Set<String> registrationIds;
    @ElementCollection(fetch = FetchType.EAGER,targetClass = Configuration.class)
    private List<Configuration> configurations;

    @PrePersist
    private void ensureId(){
        this.id = UUID.randomUUID().toString();
    }

    public Account(String accountMail, Set<String> registrationIds, List<Configuration> configurations) {
        this.accountMail = accountMail;
        this.registrationIds = registrationIds;
        this.configurations = configurations;
    }

    public Account(String accountMail) {
        this.accountMail = accountMail;
        this.registrationIds = new HashSet<String>();
        this.configurations = new ArrayList<Configuration>();
    }

    public Account() {
        this.registrationIds = new HashSet<String>();
        this.configurations = new ArrayList<Configuration>();
    }

    public Set<String> getRegistrationIds() {
        return registrationIds;
    }

    public void setRegistrationIds(Set<String> registrationIds) {
        this.registrationIds = registrationIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public CustomData toCustomData(){

        LinkedHashMap<String, String> res = new LinkedHashMap<String, String>();

        for(Configuration config: configurations){
            res.put(config.getConfigurationName(),config.getConfigurationValue());
        }

        return new CustomData(res);
    }

    public List<Configuration> fromCustomValues(LinkedHashMap<String,String> configurationValues){

        List<Configuration> res = new ArrayList<>();

        for (String key : configurationValues.keySet()){

            String value = configurationValues.get(key);
            Configuration config = new Configuration(key,value);
            res.add(config);

        }

        return res;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", accountMail='" + accountMail + '\'' +
                ", registrationIds=" + registrationIds +
                ", configurations=" + configurations +
                '}';
    }
}
