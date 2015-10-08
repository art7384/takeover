package com.sabotage.takeover.businesslogic.managers;

import android.content.Context;

import com.sabotage.takeover.businesslogic.netvork.ApiManager;
import com.sabotage.takeover.businesslogic.parser.ParserManager;

/**
 * Created by  on 29.06.2015.
 */
public class ManagerLayer {

    static private ManagerLayer sInstance = null;
    private ApiManager apiManager = null;
    private MapManager mapManager = null;
    private ParserManager arserManager =null;
    private TotemManager totemManager = null;
    private DialogManager dialogManager = null;
    private ZoneManager zoneManager = null;
    private UnitManager unitManager = null;

    private ManagerLayer(){

    }

    static public ManagerLayer getInstance(){
        if(sInstance == null){
            sInstance = new ManagerLayer();
        }
        return sInstance;
    }

    public void init(Context cnx){
        apiManager = new ApiManager(cnx);
        mapManager = new MapManager();
        arserManager = new ParserManager();
        totemManager = new TotemManager();
        dialogManager = new DialogManager();
        zoneManager = new ZoneManager();
        unitManager = new UnitManager();
    }

    public ApiManager getApiManager(){
        return apiManager;
    }

    public MapManager getMapManager(){
        return mapManager;
    }

    public ParserManager getParserManager() {
        return arserManager;
    }

    public TotemManager getTotemManager() {
        return totemManager;
    }

    public DialogManager getDialogManager() {
        return dialogManager;
    }

    public ZoneManager getZoneManager() {
        return zoneManager;
    }

    public UnitManager getUnitManager() {
        return unitManager;
    }
}
