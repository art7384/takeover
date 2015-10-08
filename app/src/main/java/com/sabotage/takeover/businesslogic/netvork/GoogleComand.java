package com.sabotage.takeover.businesslogic.netvork;

/**
 * Created by Karmishin on 02.08.2015.
 */
public enum GoogleComand {
    GET_DIRECTION(GoogleComand.HOST_MAPS, "/maps/api/directions/json", com.android.volley.Request.Method.GET);

    static private final String HOST_MAPS = "http://maps.google.com";
    String mUri;
    String mHost;
    int mMethod;

    GoogleComand(String host, String uri, int method){
        mHost = host;
        mUri = uri;
        mMethod = method;
    }

    public int getmMethod(){
        return mMethod;
    }

    @Override
    public String toString(){
        return  mHost + mUri;
    }
}
