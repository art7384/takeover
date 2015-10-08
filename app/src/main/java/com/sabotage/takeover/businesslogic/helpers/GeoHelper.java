package com.sabotage.takeover.businesslogic.helpers;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.sabotage.takeover.model.MapObject;

/**
 * Created by on 27.06.2015.
 */
public class GeoHelper {

    private static final int EARTH_RADIUS = 6372795;

    static public double getDist(LatLng latlng1, LatLng latlng2){

        double lat1 = latlng1.latitude * Math.PI / 180;
        double lat2 = latlng2.latitude * Math.PI / 180;
        double lng1 = latlng1.longitude * Math.PI / 180;
        double lng2 = latlng2.longitude * Math.PI / 180;

        double cl1 = Math.cos(lat1);
        double cl2 = Math.cos(lat2);
        double sl1 = Math.sin(lat1);
        double sl2 = Math.sin(lat2);
        double delta = lng2 - lng1;
        double cdelta = Math.cos(delta);
        double sdelta = Math.sin(delta);

        double y = Math.sqrt(Math.pow(cl2 * sdelta, 2) + Math.pow(cl1 * sl2 - sl1 * cl2 * cdelta, 2));
        double x = sl1 * sl2 + cl1 * cl2 * cdelta;

        double ad = Math.atan2(y, x);
        double dist = ad * EARTH_RADIUS;

        return dist;

    }

    static public boolean isBelongsLevel(LatLng position, LatLng center, double radius){

        double dist = getDist(position, center);

        return dist < radius;
    }

    static public boolean traversal(LatLng latLng, MapObject obj){
        float[] results = new float[1];
        Location.distanceBetween(latLng.latitude, latLng.longitude, obj.getPosition().latitude, obj.getPosition().longitude, results);
        return obj.getRadius() > results[0];
    }

}
