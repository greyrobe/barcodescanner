package me.dm7.barcodescanner.zxing.sample;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by vangurd on 12/13/2017.
 */

public class ProductDeal implements Serializable {
    String uid;
    double savings;
    String name;
    double lat, lon;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ProductDeal(String uid, double savings, String name, double lat, double lon) {
        this.uid = uid;
        this.savings = savings;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }
    
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }


    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return " $" + String.valueOf(savings) + " off on " + name;
    }

}
