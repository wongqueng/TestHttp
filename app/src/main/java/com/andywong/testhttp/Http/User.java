package com.andywong.testhttp.Http;

import android.content.Context;

import com.andywong.testhttp.ModualBase;
import com.andywong.testhttp.Response;

/**
 * Created by lenovo on 2017/1/18.
 */

public class User extends ModualBase {
    public User(Context context) {
        super(context);
    }
    public void regist(Response response){
        this.setUri("register")
        .setResponse(response)
        .send();
    }
}
