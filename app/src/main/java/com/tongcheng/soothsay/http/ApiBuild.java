package com.tongcheng.soothsay.http;


import com.tongcheng.soothsay.constant.Constant;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuild {



    public static Retrofit retrofit;
    public static Retrofit baseRetrofit;

    public static String Tag = "ApiBuild";

    private ApiBuild() {
    }




    public static Retrofit getRetrofit() {

        if (retrofit == null) {

            synchronized (ApiBuild.class) {

                if (retrofit == null) {

                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.Url.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }


    public static Retrofit getBaseRetrofit() {
        if (baseRetrofit == null) {
            baseRetrofit = new Retrofit.Builder()
                    .baseUrl(Constant.Url.BASE_URL)
                    .build();
        }
        return baseRetrofit;
    }

}
