package com.common.gspc;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IntrinioApi {
    @GET("indices/stock_market/{index}/data_point/level/number")
    Call<String> getPrice(@HeaderMap Map<String,String> headers, @Path("index") String index, @Query("api_key") String key);
}
