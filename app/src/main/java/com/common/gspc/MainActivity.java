package com.common.gspc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.quoteList)
    RecyclerView priceList;
    private Retrofit retrofit;
    private PriceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        retrofit = IntrinioController.getRetrofit();

        priceList.setHasFixedSize(true);
        priceList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PriceAdapter(this);
        priceList.setAdapter(adapter);
    }

    @OnClick(R.id.refreshMarketIndex)
    public void onClick(View item) {
        if (item.getId() == R.id.refreshMarketIndex) {
            IntrinioApi api = retrofit.create(IntrinioApi.class);
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", IntrinioController.key);
            Call<String> call = api.getPrice(headers, "ind_NX6GzO", IntrinioController.key);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    for (Map.Entry<String, List<String>> header : call.request().headers().toMultimap().entrySet()) {
                        Log.i(TAG, "Header: " + header.getKey() + " = " + header.getValue().get(0));
                    }
                    if (response.isSuccessful()) {
                        String sPrice = response.body();
                        Log.i(TAG, "Message: " + sPrice);
                        float price = sPrice == null || sPrice.isEmpty() ? 0.0f : Float.parseFloat(sPrice);
                        PriceModel.Instance.add(new PriceDataPoint(price, new Date()));
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.e(TAG, "HTTP Result Code: " + response.code());
                        try {
                            Log.e(TAG, "Error Response: " + response.errorBody().string());
                        } catch (IOException ignore) {
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                    for (Map.Entry<String, List<String>> header : call.request().headers().toMultimap().entrySet()) {
                        Log.i(TAG, "Header: " + header.getKey() + " " + header.getValue().get(0));
                    }
                }
            });
        }
    }
}
