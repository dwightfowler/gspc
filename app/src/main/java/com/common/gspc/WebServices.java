package com.common.gspc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WebServices
{
    static final String key = "OmVmODUyOTU2M2Q1NmI2MDQxNTlhYmQ1NjYzYTk3NmZl";
    private static final String baseUrl = "https://api-v2.intrinio.com/";

    public static Retrofit getRetrofit(){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
}