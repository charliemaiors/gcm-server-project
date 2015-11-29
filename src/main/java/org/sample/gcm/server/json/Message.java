package org.sample.gcm.server.json;

/**
 * Created by Carlo on 29/11/2015.
 */
public abstract class Message {

    private String to;
    private String message_id;
    private String collapse_key;
    private Long time_to_live;
    private boolean delay_when_idle;
    private Data data;

    public Message(String to, String message_id, String collapse_key, Long time_to_live, boolean delay_when_idle, Data data) {
        this.to = to;
        this.message_id = message_id;
        this.collapse_key = collapse_key;
        this.time_to_live = time_to_live;
        this.delay_when_idle = delay_when_idle;
        this.data = data;
    }

    public Message() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getCollapse_key() {
        return collapse_key;
    }

    public void setCollapse_key(String collapse_key) {
        this.collapse_key = collapse_key;
    }

    public Long getTime_to_live() {
        return time_to_live;
    }

    public void setTime_to_live(Long time_to_live) {
        this.time_to_live = time_to_live;
    }

    public boolean isDelay_when_idle() {
        return delay_when_idle;
    }

    public void setDelay_when_idle(boolean delay_when_idle) {
        this.delay_when_idle = delay_when_idle;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
