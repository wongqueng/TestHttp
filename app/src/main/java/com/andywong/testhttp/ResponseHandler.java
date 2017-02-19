package com.andywong.testhttp;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by lenovo on 2016/12/16.
 */

public class ResponseHandler extends AsyncHttpResponseHandler {
    Response response;

    public ResponseHandler(Response response) {
        this.response = response;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        response.setStatusCode(statusCode);
        response.setHeaders(headers);
        response.setResponseBody(responseBody);
        try {
            response.onSuccess();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        response.setStatusCode(statusCode);
        response.setHeaders(headers);
        response.setResponseBody(responseBody);
        response.setError(error);
        try {
            response.onFailure();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
