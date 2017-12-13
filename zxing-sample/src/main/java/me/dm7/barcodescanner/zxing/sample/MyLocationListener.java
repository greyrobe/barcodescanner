package me.dm7.barcodescanner.zxing.sample;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by ATIV on 12/13/2017.
 */

public class MyLocationListener implements LocationListener {

    GoogleMap mMap;
    Marker mMarker;
    ArrayList<DealLocation> mDealLocations;

    public MyLocationListener(GoogleMap map, Marker marker) {
        super();
        mMap = map;
        mMarker = marker;
    }

    private void setTargets(ArrayList<DealLocation> dealLocations) {
        mDealLocations = dealLocations;
    }

    @Override
    public void onLocationChanged(Location location) {

        LatLng pos=new LatLng(location.getLatitude(), location.getLongitude());

        if(mMarker == null){
            mMarker = mMap.addMarker(new MarkerOptions().position(pos).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_location)));
        }
        else {
            mMarker.setPosition(pos);
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));

        if( mDealLocations != null) {
            for (DealLocation dl: mDealLocations) {

                Location current = new Location("my_pos");
                current.setLatitude(pos.latitude);
                current.setLongitude(pos.longitude);

                Location target = new Location("target");
                target.setLatitude(dl.getPosition().latitude);
                target.setLongitude(dl.getPosition().longitude);

                if( current.distanceTo(target) < dl.getThresholdDistance() ) {
                    dl.locationFoundHandler();
                }
            }
        }
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
