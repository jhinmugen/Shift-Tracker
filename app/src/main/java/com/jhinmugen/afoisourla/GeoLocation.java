package com.jhinmugen.afoisourla;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.util.List;
import java.util.Locale;

public class GeoLocation {

    private static final String TAG = "GeoLocation";
    private double longtitude;
    private double latitude;
    private Context context;
    private List<Address> addresses;
    private String address;
    private String city;
    private String knownName;


    public GeoLocation(double longtitude, double latitude, Context context) {

        this.longtitude = longtitude;
        this.latitude = latitude;
        this.context = context;
    }


    public void calculateAddress() {
        Geocoder geocoder;
        Locale current = context.getResources().getConfiguration().locale;
        Log.d(TAG, "calculateAddress: " + longtitude);
        Log.d(TAG, "calculateAddress: " + latitude);
        geocoder = new Geocoder(context, current);
        try {
            addresses = geocoder.getFromLocation(latitude, longtitude, 1);
            address = addresses.get(0).getAddressLine(0);
            city = addresses.get(0).getLocality();
            knownName = addresses.get(0).getFeatureName();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getKnownName() {
        return knownName;
    }
}
