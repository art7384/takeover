package com.sabotage.takeover.businesslogic.netvork;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;
import com.sabotage.takeover.businesslogic.transport.netvork.Request;

import org.json.JSONObject;

/**
 * Created by  on 30.06.2015.
 */
public class ApiManager {

    private static final String LOG_TAG = "ApiManager";
    private RequestQueue mRequestQueue;
    private ApiGoogle mApiGoogle;
    private Context mContext;

    public ApiManager(Context cnx) {
        mContext = cnx;
        init();
    }

    private void init() {
        mRequestQueue = Volley.newRequestQueue(mContext);
        mApiGoogle = new ApiGoogle();
    }

    public void getDirections(LatLng orign,
                              LatLng destination,
                              Response.Listener<JSONObject> listener,
                              Response.ErrorListener errorListener) {

        Request request = mApiGoogle.getDirections(orign, destination,
                listener, errorListener);
        send(request);
    }

    private void send(Request request) {
        request.log();
        mRequestQueue.add(request);
    }



}
