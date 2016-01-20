package org.sample.gcm.server.json;

import java.util.LinkedHashMap;

/**
 * Created by Carlo on 20/01/2016.
 */
public class GcmResponse {

    private String multicast_id;
    private int success;
    private int failure;
    private int canonical_ids;
    private LinkedHashMap<String, String> results;

    public GcmResponse(String multicast_id, int success, int failure, int canonical_ids, LinkedHashMap<String, String> results) {
        this.multicast_id = multicast_id;
        this.success = success;
        this.failure = failure;
        this.canonical_ids = canonical_ids;
        this.results = results;
    }

    public GcmResponse() {
    }

    public String getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(String multicast_id) {
        this.multicast_id = multicast_id;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public int getCanonical_ids() {
        return canonical_ids;
    }

    public void setCanonical_ids(int canonical_ids) {
        this.canonical_ids = canonical_ids;
    }

    public LinkedHashMap<String, String> getResults() {
        return results;
    }

    public void setResults(LinkedHashMap<String, String> results) {
        this.results = results;
    }
}
