package com.common.gspc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.PriceViewHolder> {
    private Context context;
    private DateFormat localDateTimeFormat;

    PriceAdapter(Context context) {
        this.context = context;
        localDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS aa Z", Locale.getDefault());
    }

    @NonNull
    @Override
    public PriceAdapter.PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false);
        return new PriceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {
        if (position >= 0 && position < PriceModel.Instance.size()) {
            PriceDataPoint dp = PriceModel.Instance.get(position);
            int id = dp.Date.getTime() % 2 == 0 ? R.drawable.ic_green_up_arrow : R.drawable.ic_red_down_array;
            holder.indicator.setImageDrawable(context.getDrawable(id));
            holder.content1.setText(String.format(Locale.getDefault(), "Price: $%.2f", dp.Price));
            holder.content2.setText(String.format(Locale.getDefault(), "Date: %s", localDateTimeFormat.format(dp.Date)));
            holder.content3.setText("Super");
        }
    }

    @Override
    public int getItemCount() {
        return PriceModel.Instance.size();
    }

    static class PriceViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.UpDownIndicator)
        ImageView indicator;
        @BindView(R.id.content1)
        TextView content1;
        @BindView(R.id.content2)
        TextView content2;
        @BindView(R.id.content3)
        TextView content3;

        PriceViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}