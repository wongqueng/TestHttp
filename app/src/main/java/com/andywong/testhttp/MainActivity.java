package com.andywong.testhttp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.andywong.testhttp.Http.User;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimpleDraweeView iv= (SimpleDraweeView) findViewById(R.id.iv);
        iv.setImageURI(Uri.parse(ModualBase.Base_Url+"images/wangqian.jpg"));
        tv= (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setname();
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(ModualBase.Base_Url+"register").build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, final okhttp3.Response response) throws IOException {

                        tv.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject data=new JSONObject(response.body().string());
                                    tv.setText(data.getString("name"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });

            }


            private void setname() {
                User user=new User(MainActivity.this);
                user.regist(new Response() {
                    @Override
                    public void onSuccess() throws JSONException {
                        JSONObject data=getjsonboject();
                        tv.setText(data.getString("name"));
                    }

                    @Override
                    public void onFailure() throws JSONException {
                    Log.d("MainActivity", "faile");
                    }
                });
            }
        });

    }
public void gethttp(){

}


}
