package com.sabotage.takeover.config;

import android.graphics.Color;

import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Кармишин on 06.08.2015.
 */
public class MapConfig {

    public static final LatLng ZONE_LAT_LONG = new LatLng(54.319862, 48.394980);
    public static final int ZONE_RADIUS = 10000;
    public static final int ZONE_STROKE_WIDTH = 5;
    public static final int ZONE_STROKE_COLOR = Color.RED;

    public static final LatLng CAMERA_LAT_LONG = ZONE_LAT_LONG;
    public static final int CAMERA_ZUM = 16;
    public static final int CAMERA_TITLE = 90;

    public static final int TOTEM_STROKE_WIDTH = 3;
    public static final int TOTEM_RADIUS = 80;
    public static final int TOTEM_STROKE_COLOR = Color.BLUE;
    public static final int TOTEM_fILL_COLOR = 0x110000ff;

    public static final int UNIT_STROKE_WIDTH = 3;
    public static final int UNIT_RADIUS = 10;
    public static final int UNIT_STROKE_COLOR = Color.BLUE;
    public static final int UNIT_fILL_COLOR = 0x110000ff;

}
