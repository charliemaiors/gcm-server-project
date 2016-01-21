package org.sample.gcm.server.json;

/**
 * Created by Carlo on 21/01/2016.
 */
public class Notification extends Message{

    private NotificationBody notification;

    public Notification(String to, String message_id, String collapse_key, Long time_to_live, boolean delay_when_idle, CustomData data, NotificationBody notification) {
        super(to, message_id, collapse_key, time_to_live, delay_when_idle, data);
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
