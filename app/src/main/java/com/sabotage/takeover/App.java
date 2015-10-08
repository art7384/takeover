package com.sabotage.takeover;

import android.app.Application;
import android.util.Base64;

import com.sabotage.takeover.businesslogic.managers.ManagerLayer;

/**
 * Created by  on 28.06.2015.
 */
public class App extends Application {

    private ManagerLayer mManagerLayer;
    static private App sInstance;

    //http.setHeader("Authorization", "Basic "+Base64.encodeToString("login:password".getBytes(), Base64.NO_WRAP));

    @Override
    public void onCreate() {

        mManagerLayer = ManagerLayer.getInstance();
        mManagerLayer.init(getApplicationContext());

        sInstance = this;

    }

    static public App getInstance(){
        return sInstance;
    }

    public ManagerLayer getManagerLayer(){
        return mManagerLayer;
    }

}
