package com.sabotage.takeover.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Кармишин on 04.08.2015.
 */
public class Unit extends MapObject {
    public int idTotem = -1;
    public String name = null;
    public LatLng latLng = null;
    public static final Integer RADIUS = 10;
    public PolylineList line = null;
}
