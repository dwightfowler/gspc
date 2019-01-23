package com.common.gspc;

public class Alpha {
}


public class AlphaIntraday {

    public MetaData metaData;
    public TimeSeries5min timeSeries5min;

}

public class MetaData {

    public String _1Information;
    public String _2Symbol;
    public String _3LastRefreshed;
    public String _4Interval;
    public String _5OutputSize;
    public String _6TimeZone;

}

public class TimeSeries5min {

    public _20190122160000 _20190122160000;

}

public class _20190122160000 {

    public String _1Open;
    public String _2High;
    public String _3Low;
    public String _4Close;
    public String _5Volume;

}