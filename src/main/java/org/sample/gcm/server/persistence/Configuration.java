package org.sample.gcm.server.persistence;

/**
 * Created by Carlo on 10/01/2016.
 */
public class Configuration {

    private String configurationName;
    private String  configurationType;

    public Configuration(String configurationName, String configurationType) {
        this.configurationName = configurationName;
        this.configurationType = configurationType;
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }

    public String getConfigurationType() {
        return configurationType;
    }

    public void setConfigurationType(String configurationType) {
        this.configurationType = configurationType;
    }
}
