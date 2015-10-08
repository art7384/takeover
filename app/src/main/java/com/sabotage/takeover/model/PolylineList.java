package com.sabotage.takeover.model;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Karmishin on 29.06.2015.
 */
public class PolylineList extends ArrayList {

    public String status = null;
    public long distance = 0;

    public LatLng maxLatLng = null;
    public LatLng minLatLng = null;

    public static abstract class Status {
        public static String OK = "OK";
        public static String NOT_FOUND = "NOT_FOUND";
        public static String ZERO_RESULTS = "ZERO_RESULTS";
        public static String MAX_WAYPOINTS_EXCEEDED = "MAX_WAYPOINTS_EXCEEDED";
        public static String INVALID_REQUEST = "INVALID_REQUEST";
        public static String OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
        public static String REQUEST_DENIED = "REQUEST_DENIED";
        public static String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    }
}
