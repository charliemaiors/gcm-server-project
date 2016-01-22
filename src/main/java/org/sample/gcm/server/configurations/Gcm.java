package org.sample.gcm.server.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Created by Carlo on 20/01/2016.
 */
@Service
@ConfigurationProperties (locations = "classpath:gcm-project.properties",prefix = "gcm")
public class Gcm {

    private String projectID;
    private String apiKeys;
    private String server;

    public Gcm() {
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getApiKeys() {
        return apiKeys;
    }

    public void setApiKeys(String apiKeys) {
        this.apiKeys = apiKeys;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
