package org.sample.gcm.server.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.UUID;

/**
 * Created by Carlo on 10/01/2016.
 */
@Entity
public class Configuration {

    @Id
    private String id;
    private String configurationName;
    private String configurationValue;

    public Configuration(String configurationName, String configurationValue) {
        this.configurationName = configurationName;
        this.configurationValue = configurationValue;
    }

    @PrePersist
    private void ensureId(){
        this.id = UUID.randomUUID().toString();
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }

    public String getConfigurationValue() {
        return configurationValue;
    }

    public void setConfigurationValue(String configurationValue) {
        this.configurationValue = configurationValue;
    }
}
