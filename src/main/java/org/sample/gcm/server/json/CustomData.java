package org.sample.gcm.server.json;

import java.util.LinkedHashMap;

/**
 * Created by Carlo on 29/11/2015.
 */
public class CustomData {

    private LinkedHashMap<String,String> values;

    public CustomData(LinkedHashMap<String, String> values) {
        this.values = values;
    }

    public CustomData() {
    }

    public LinkedHashMap<String, String> getValues() {
        return values;
    }

    public void setValues(LinkedHashMap<String, String> values) {
        this.values = values;
    }
}
