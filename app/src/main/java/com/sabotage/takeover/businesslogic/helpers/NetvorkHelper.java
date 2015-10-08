package com.sabotage.takeover.businesslogic.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sabotage.takeover.App;

/**
 * Created by Karmishin on 05.08.2015.
 */
public class NetvorkHelper {
    static public boolean isConnect() {
        Context cnx = App.getInstance().getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) cnx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }

        }
        return false;
    }
}
