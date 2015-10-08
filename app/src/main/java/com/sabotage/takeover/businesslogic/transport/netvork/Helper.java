package com.sabotage.takeover.businesslogic.transport.netvork;

import com.android.volley.Request;

import java.util.Map;
import java.util.Set;

/**
 * Created by  on 30.06.2015.
 */
public class Helper {

    static public String concat(String uri, Map<String, String> params){
        String result = uri + "?";
        Set<String> keys = params.keySet();
        for (String key : keys){
            result += key + "=" + params.get(key) + "&";
        }
        result = result.substring(0, result.length() - 2);
        return result;
    }

}
