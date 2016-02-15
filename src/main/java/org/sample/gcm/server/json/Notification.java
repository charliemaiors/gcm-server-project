package org.sample.gcm.server.json;

/**
 * Created by Carlo on 21/01/2016.
 */
public class Notification extends AbstractMessage{

    private NotificationBody notification;

    public Notification(String to, String collapse_key, Long time_to_live, boolean delay_when_idle, NotificationBody notification) {
        super(to,collapse_key,time_to_live,delay_when_idle);
        this.notification = notification;
    }

    public Notification() {
    }

    public NotificationBody getNotification() {
        return notification;
    }

    public void setNotification(NotificationBody notification) {
        this.notification = notification;
    }
}
