package com.sabotage.takeover.businesslogic.managers;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.model.LatLng;
import com.sabotage.takeover.App;
import com.sabotage.takeover.businesslogic.helpers.NetvorkHelper;
import com.sabotage.takeover.businesslogic.netvork.ApiManager;
import com.sabotage.takeover.businesslogic.parser.ParserManager;
import com.sabotage.takeover.model.PolylineList;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

/**
 * Created by Karmishin on 04.08.2015.
 */
public class PolylineManager {


    public void create(LatLng orign,
                       LatLng destination,
                       OnRequestListner onRequestListner) {
        final ManagerLayer managerLayer = App.getInstance().getManagerLayer();
        ApiManager api = managerLayer.getApiManager();

        OnResponseListener listener = new OnResponseListener(onRequestListner);
        OnErrorListener error = new OnErrorListener(onRequestListner);

        api.getDirections(orign,
                destination,
                listener,
                error);
    }

    public interface OnRequestListner {
        public void onRequest(PolylineList line);

        public void onErrorServer(int code, String msg);

        public void onErrorJsonParser(JSONObject json);

        public void onErrorConnect();

        public void onError();
    }

    private class OnErrorListener implements Response.ErrorListener {

        OnRequestListner onRequestListner;

        public OnErrorListener(OnRequestListner listner) {
            onRequestListner = listner;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            if(NetvorkHelper.isConnect()){
                onRequestListner.onErrorConnect();
            } else if(error.networkResponse != null){
                int code = error.networkResponse.statusCode;
                String msg = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                onRequestListner.onErrorServer(code, msg);
            } else {
                onRequestListner.onError();
            }
        }
    }

    private class OnResponseListener implements Response.Listener<JSONObject> {

        OnRequestListner onRequestListner;

        public OnResponseListener(OnRequestListner listner) {
            onRequestListner = listner;
        }

        @Override
        public void onResponse(JSONObject response) {
            ManagerLayer managerLayer = App.getInstance().getManagerLayer();
            ParserManager parser = managerLayer.getParserManager();
            try {
                PolylineList list = parser.polylineList(response);
                onRequestListner.onRequest(list);
            } catch (JSONException e) {
                e.printStackTrace();
                onRequestListner.onErrorJsonParser(response);
            }
        }
    }

}

