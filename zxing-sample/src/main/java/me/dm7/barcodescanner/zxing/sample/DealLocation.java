package me.dm7.barcodescanner.zxing.sample;

import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import me.dm7.barcodescanner.zxing.sample.MainActivity;

/**
 * Created by ATIV on 12/13/2017.
 */

public class DealLocation {

    private double thresholdDistance;
    private LatLng position;

    public DealLocation(LatLng position, double thresholdDistance)
    {
        this.thresholdDistance = thresholdDistance;
        this.position = position;
    }

    double getThresholdDistance() {
        return thresholdDistance;
    }

    LatLng getPosition () {
        return position;
    }

    public void locationFoundHandler() {

    }

}
