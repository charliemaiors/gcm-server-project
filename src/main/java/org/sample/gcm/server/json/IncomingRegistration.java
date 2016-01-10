package org.sample.gcm.server.json;

/**
 * Created by Carlo on 10/01/2016.
 */
public class IncomingRegistration {

    private String account;
    private String registrationId;

    public IncomingRegistration(String account, String registrationId) {
        this.account = account;
        this.registrationId = registrationId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
