package com.sabotage.takeover.businesslogic.netvork;

import com.android.volley.Response;
import com.google.android.gms.maps.model.LatLng;
import com.sabotage.takeover.businesslogic.transport.netvork.Request;
import com.sabotage.takeover.businesslogic.transport.netvork.RequestModel;

import org.json.JSONObject;

/**
 * Created by  on 30.06.2015.
 */
public class ApiGoogle {

    public ApiGoogle(){
    }

    public Request getDirections(
            LatLng orign,
            LatLng destination,
            Response.Listener<JSONObject> listener,
            Response.ErrorListener errorListener){

        RequestModel requestModel = new RequestModel();

        requestModel.method = GoogleComand.GET_DIRECTION.getmMethod();
        requestModel.url = GoogleComand.GET_DIRECTION.toString();

        requestModel.strParams.put(GoogleParams.ORIGIN.toString(), orign.latitude + "," + orign.longitude);
        requestModel.strParams.put(GoogleParams.DESTINATION.toString(), destination.latitude + "," + destination.longitude);
        requestModel.strParams.put(GoogleParams.SENSOR.toString(), "true");

        requestModel.listener = listener;
        requestModel.errorListener = errorListener;

        return Request.create(requestModel);
    }
}
