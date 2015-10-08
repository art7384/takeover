package com.sabotage.takeover.businesslogic.transport.netvork;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Karmishin on 02.08.2015.
 */
public class RequestModel {
    public int method = 0;
    public String url = null;
    public String requestBody = null;
    public Response.Listener<JSONObject> listener = null;
    public Response.ErrorListener errorListener = null;
    public Map<String, String> strParams = new HashMap();
    public Map<String, String> heder = new HashMap();

    public String getStringMethod(){
        switch (method) {
            case com.android.volley.Request.Method.GET: {
                return "GET";
            }
            case Request.Method.PUT: {
                return "PUT";
            }
            case Request.Method.POST: {
                return "POST";
            }
            case Request.Method.DELETE: {
                return "DELETE";
            }
            default:
                return null;
        }
    }

}
