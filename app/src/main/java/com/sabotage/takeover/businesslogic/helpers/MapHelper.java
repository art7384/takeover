package com.sabotage.takeover.businesslogic.helpers;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sabotage.takeover.model.MapObject;

/**
 * Created by Кармишин on 06.08.2015.
 */
public class MapHelper {

    static public void addMarcer(GoogleMap map, MapObject object){
        MarkerOptions markerOptions = new MarkerOptions();
        LatLng position = object.getPosition();
        int markerResursId = object.getMarkerResursId();
        if(position != null){
            markerOptions.position(position);
        }
        if(markerResursId > -1){
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(markerResursId);
            markerOptions.icon(bitmapDescriptor);
        }

        Marker marker = map.addMarker(markerOptions);

        object.setMarker(marker);

    }



    static public void addCircle(GoogleMap map, MapObject object) {

        CircleOptions circleOptions = new CircleOptions();
        LatLng latLng = object.getPosition();
        double radius = object.getRadius();
        int strokeColor = object.getStrokeColor();
        float strokeWidth = object.getStrokeWidth();
        int fullColor = object.getFillColor();

        if(latLng != null) {
            circleOptions.center(latLng);
        }
        if(radius > -1) {
            circleOptions.radius(radius);
        }
        if(strokeWidth > 0) {
            circleOptions.strokeWidth(strokeWidth);
            circleOptions.strokeColor(strokeColor);
        }

        if(fullColor != 0x00000000){
            circleOptions.fillColor(fullColor);
        }

        Circle circle = map.addCircle(circleOptions);
        object.setCircle(circle);
    }



}
