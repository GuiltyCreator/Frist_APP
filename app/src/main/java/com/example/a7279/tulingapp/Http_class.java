package com.example.a7279.tulingapp;

import android.content.Context;
import android.content.SharedPreferences;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by a7279 on 2017/11/19.
 */

public class Http_class {
    static void Post_Okhttp(final String text)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    RequestBody requestBody=new FormBody.Builder().add("key","268be5a907a24140ae5c68c1229665eb")
                            .add("info",text)
                            .add("userid","GuiltyCreator")
                            .build();
                    Request request=new Request.Builder().url("http://www.tuling123.com/openapi/api").post(requestBody).build();
                    OkHttpClient client=new OkHttpClient();
                    Response response=client.newCall(request).execute();
                    String data=response.body().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
