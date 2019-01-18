package com.common.gspc;

import java.util.Date;

import androidx.annotation.NonNull;

public class PriceDataPoint {
    public final float Price;
    public final Date Date;

    public PriceDataPoint(float price, @NonNull Date date){
        Price = price;
        Date = date;
    }
}