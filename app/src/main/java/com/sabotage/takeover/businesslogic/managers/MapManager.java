package com.sabotage.takeover.businesslogic.managers;

import android.graphics.Color;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sabotage.takeover.config.MapConfig;
import com.sabotage.takeover.model.Totem;
import com.sabotage.takeover.model.Zone;

/**
 * Created by Karmishin on 04.08.2015.
 */
public class MapManager {

    MapManager(){

    }

    public CameraPosition getStartCameraPosition() {
        return new CameraPosition.Builder()
                .target(MapConfig.CAMERA_LAT_LONG)
                .zoom(MapConfig.CAMERA_ZUM)
                .tilt(MapConfig.CAMERA_TITLE)
                .build();
    }

}
