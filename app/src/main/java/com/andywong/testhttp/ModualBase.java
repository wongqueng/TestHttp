package com.andywong.testhttp;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by lenovo on 2016/12/16.
 */

public class ModualBase {
    public static String Base_Url = "http://192.168.0.102:8080/andy/";
    private String url = "";
    private AsyncHttpClient client;
    private Context context;
    private RequestParams params = new RequestParams();
    Header[] headers;
    Response response;
    static final int METHOD_GET = 0;
    static final int METHOD_POST = 1;
    static final int METHOD_PUT = 2;
    static final int METHOD_DELETE = 3;
    int method = 0;

    public ModualBase(Context context) {
        this.context = context;
        client = new AsyncHttpClient();
    }

    public String getUri() {
        return Base_Url + url;
    }

    public ModualBase setUri(String url) {
        this.url = url;
        return this;
    }

    public ModualBase setMethod(int method) {
        this.method = method;
        return this;

    }

    public ModualBase setResponse(Response response) {
        this.response = response;
        return this;

    }

    public ModualBase setParams(RequestParams params) {
        this.params = params;
        return this;

    }

    public void send() {
        switch (method) {
            case METHOD_GET:
                client.get(context, getUri(), headers, params, new ResponseHandler(response));
                break;
            case METHOD_POST:
                client.post(context, getUri(), headers, params, null, new ResponseHandler(response));
                break;
            case METHOD_PUT:
                AsyncHttpResponseHandler responseHandler = new ResponseHandler(response);
                try {
                    client.put(context, getUri(), headers, params.getEntity(responseHandler), null, responseHandler);
                } catch (IOException e) {
                    e.printStackTrace();
                    responseHandler.sendFailureMessage(0, null, null, e);
                }
                break;
            case METHOD_DELETE:
                client.delete(context, getUri(), headers, params, new ResponseHandler(response));
                break;
            default:
                client.get(context, getUri(), headers, params, new ResponseHandler(response));
        }
    }
}
