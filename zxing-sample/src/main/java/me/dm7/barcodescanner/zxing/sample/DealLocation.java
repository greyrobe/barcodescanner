package me.dm7.barcodescanner.zxing.sample;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import me.dm7.barcodescanner.zxing.sample.MainActivity;

/**
 * Created by ATIV on 12/13/2017.
 */

public class DealLocation {

    private double thresholdDistance;
    private LatLng position;
    Context context;

    public DealLocation(LatLng position, double thresholdDistance, Context context)
    {
        this.thresholdDistance = thresholdDistance;
        this.position = position;
        this.context = context;
    }

    double getThresholdDistance() {
        return thresholdDistance;
    }

    LatLng getPosition () {
        return position;
    }

    public void locationFoundHandler() {
        Toast.makeText(context, "Deal found!", Toast.LENGTH_LONG);
    }

}
