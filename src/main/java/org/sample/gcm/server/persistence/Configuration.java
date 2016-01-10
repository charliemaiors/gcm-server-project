package org.sample.gcm.server.persistence;

/**
 * Created by Carlo on 10/01/2016.
 */
public class Configuration {

    private String configurationName;
    private String  configurationValue;

    public Configuration(String configurationName, String configurationValue) {
        this.configurationName = configurationName;
        this.configurationValue = configurationValue;
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
