package com.common.gspc;

import java.util.ArrayList;
import java.util.List;

public class PriceModel {
    public static final PriceModel Instance;

    static {
        Instance = new PriceModel();
    }

    private List<PriceDataPoint> prices;

    private PriceModel() {
        prices = new ArrayList<>();
    }

    public PriceDataPoint get(int i){
        return prices.get(i);
    }

    public int size(){
        return prices.size();
    }

    public void add(PriceDataPoint dataPoint){
        prices.add(dataPoint);
    }
}
