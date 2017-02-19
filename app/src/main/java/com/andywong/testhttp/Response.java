package com.andywong.testhttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by lenovo on 2016/12/16.
 */

public abstract class Response {
    int statusCode;
    Header[] headers;
    byte[] responseBody;
    Throwable error;

    public String getheadervalue(String name) {
        for (int i = 0; i < headers.length; i++) {
            Header header = headers[i];
            if (header.getName().equals(name))
                return header.getValue();
        }
        return "";
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


    public void setResponseBody(byte[] responseBody) {
        this.responseBody = responseBody;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public JSONObject getjsonboject() throws JSONException {
        return new JSONObject(new String(responseBody));
    }

    public JSONArray getjsonbarray() throws JSONException {
        return new JSONArray(new String(responseBody));
    }

    public abstract void onSuccess() throws JSONException;

    public abstract void onFailure() throws JSONException;
}
