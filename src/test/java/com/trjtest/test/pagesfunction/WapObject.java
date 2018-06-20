package com.trjtest.test.pagesfunction;

import org.apache.http.client.CookieStore;
import org.json.JSONObject;

/**
 * Created by gjw on 17/6/27.
 */
public class WapObject {
    private CookieStore cookieStore;
    private String message;
    private String boolen;

    private JSONObject dataObject;

    public JSONObject getDataObject() {
        return dataObject;
    }

    public void setDataObject(JSONObject dataObject) {
        this.dataObject = dataObject;
    }

    public String getBoolen() {
        return boolen;
    }

    public void setBoolen(String boolen) {
        this.boolen = boolen;
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
