package com.sabotage.takeover.businesslogic.netvork;

/**
 * Created by Karmishin on 02.08.2015.
 */
public enum GoogleParams {
    SENSOR("sensor"),
    DESTINATION("destination"),
    ORIGIN("origin");

    String mParam;

    GoogleParams(String param){
        mParam = param;
    }

    @Override
    public String toString(){
        return  mParam;
    }
}
