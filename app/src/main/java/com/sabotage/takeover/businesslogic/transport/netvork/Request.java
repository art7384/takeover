package com.sabotage.takeover.businesslogic.transport.netvork;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Set;

/**
 * Created by  on 28.06.2015.
 */
public class Request extends JsonRequest<JSONObject> {

    private static final String LOG_TAG = "NetvorkRequest";
    RequestModel mModel;

    static public Request create(RequestModel modele){

        String url = modele.url;
        Request request = null;

        switch (modele.method){
            case Method.GET:{
                url = Helper.concat(url, modele.strParams);
                request = new Request(
                        Method.GET,
                        url,
                        modele.requestBody,
                        modele.listener,
                        modele.errorListener
                );
                break;
            }
            case Method.PUT:{
                break;
            }
            case Method.POST:{
                break;
            }
            case Method.DELETE:{
                break;
            }
        }
        request.mModel = modele;
        return request;
    }

    public void log() {

        Log.d(LOG_TAG, "----- REQUEST -----");
        Log.d(LOG_TAG, "// METHOD: " + mModel.getStringMethod());
        Log.d(LOG_TAG, "// URL: " + mModel.url);
        Log.d(LOG_TAG, "// HEDER:");
        Set<String> keysHeder = mModel.heder.keySet();
        for (String key: keysHeder){
            Log.d(LOG_TAG, "//    " + key + ":" + mModel.heder.get(key));
        }
        Log.d(LOG_TAG, "// PARAM:");
        Set<String> keysParam = mModel.strParams.keySet();
        for (String key: keysParam){
            Log.d(LOG_TAG, "//    " + key + ":" + mModel.strParams.get(key));
        }
    }

    private Request(
            int method,
            String url,
            String requestBody,
            Response.Listener<JSONObject> listener,
            Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

}
