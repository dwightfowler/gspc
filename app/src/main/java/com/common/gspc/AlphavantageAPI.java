package com.common.gspc;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlphavantageAPI {
    String baseUrl = "https://www.alphavantage.co/";

    // query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=demo
    @GET("query")
    Call<AlphaIntraday> getAlphaIntraday(
            @Query("function") String function,
            @Query("symbol") String symbol,
            @Query("interval") String interval,
            @Query("apikey") String api_key);


    class AlphaIntraday {
    }

}
