package com.sabotage.takeover.businesslogic.parser;

import com.sabotage.takeover.model.PolylineList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Karmishin on 04.08.2015.
 */
public class ParserManager {

    public PolylineList polylineList(JSONObject jsObj) throws JSONException {
        PolylineParser polylineParser = new PolylineParser();
        return polylineParser.create(jsObj);
    }
}
