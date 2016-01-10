package org.sample.gcm.server.json;

/**
 * Created by Carlo on 10/01/2016.
 */
public class IncomingRegistration {

    private String accountName;
    private String accountMail;
    private String registrationId;

    public IncomingRegistration(String accountName, String accountMail, String registrationId) {
        this.accountName = accountName;
        this.accountMail = accountMail;
        this.registrationId = registrationId;
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

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
