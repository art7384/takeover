package com.sabotage.takeover.presentation;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sabotage.takeover.R;
import com.sabotage.takeover.businesslogic.helpers.GeoHelper;
import com.sabotage.takeover.businesslogic.helpers.MapHelper;
import com.sabotage.takeover.businesslogic.managers.ManagerLayer;
import com.sabotage.takeover.businesslogic.managers.MapManager;
import com.sabotage.takeover.businesslogic.managers.TotemManager;
import com.sabotage.takeover.businesslogic.managers.UnitManager;
import com.sabotage.takeover.businesslogic.netvork.ApiManager;
import com.sabotage.takeover.businesslogic.parser.PolylineParser;
import com.sabotage.takeover.config.AppConfig;
import com.sabotage.takeover.model.PolylineList;
import com.sabotage.takeover.model.Totem;
import com.sabotage.takeover.model.Unit;
import com.sabotage.takeover.model.Zone;

import org.json.JSONException;
import org.json.JSONObject;


public class MapsActivity extends FragmentActivity {

    private static final String LOG_TAG = "MapsActivity";
    private GoogleMap map;
    private Polyline mPolylineActyor = null;
    ManagerLayer managerLayer = ManagerLayer.getInstance();
    private Totem totem = null;
    private Zone zone = null;
    private Unit unit = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #map} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            // Try to obtain the map from the SupportMapFragment.
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #map} is not null.
     */
    private void setUpMap() {

        initCamera();
        initZone();
        MapHelper.addCircle(map, zone);

        map.setOnMapClickListener(new OnMapClick());

        if(totem == null){
            ManagerLayer.getInstance().getDialogManager().showDialogHelp(this, "Выбири место для базы");
        }
    }



    private void drawPolyline(PolylineList polylineList){
        removePolylineActyor();
        mPolylineActyor = map.addPolyline((new PolylineOptions().color(Color.BLUE).width(5)).addAll(polylineList));
    }

    private void removePolylineActyor(){
        if(mPolylineActyor != null){
            mPolylineActyor.remove();
            mPolylineActyor = null;
        }
    }

    class OnMapClick implements GoogleMap.OnMapClickListener {

        @Override
        public void onMapClick(LatLng latLng) {

            if(!GeoHelper.traversal(latLng, zone)){
                ManagerLayer.getInstance().getDialogManager().showDialogHelp(MapsActivity.this, "Клик за територией зоны");
               return;
            }
            if(totem == null){
                initTotem(latLng);
                ManagerLayer.getInstance().getDialogManager().showDialogHelp(MapsActivity.this, "Выбири место для персонажа, на територии базы");
                return;
            }
            if(unit == null){
                if(GeoHelper.traversal(latLng, totem)){
                    initUnit(latLng);
                } else {
                    ManagerLayer.getInstance().getDialogManager().showDialogHelp(MapsActivity.this, "Персонаж должен находится на територии базы");
                }

                return;
            }

            ApiManager api = managerLayer.getApiManager();
            api.getDirections(
                        unit.getPosition(),
                        latLng,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(LOG_TAG, response.toString());
                                PolylineList polyline = null;
                                try {
                                    polyline = PolylineParser.create(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                drawPolyline(polyline);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(LOG_TAG, error.toString());
                            }
                        });


        }
    }

    private void initCamera(){
        MapManager mapManager = managerLayer.getMapManager();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(mapManager.getStartCameraPosition());
        map.animateCamera(cameraUpdate);
    }

    private void initZone(){
        zone = ManagerLayer.getInstance().getZoneManager().crieit();
        MapHelper.addMarcer(map, zone);
    }

    private void initTotem(LatLng latLng){
        TotemManager totemManager = ManagerLayer.getInstance().getTotemManager();
        totem = totemManager.create(latLng);
        MapHelper.addMarcer(map, totem);
        if(AppConfig.IS_UI_DEBAG){
            MapHelper.addCircle(map, totem);
        }
    }

    private void initUnit(LatLng latLng){
        UnitManager userManager = ManagerLayer.getInstance().getUnitManager();
        unit = userManager.create(latLng);
        MapHelper.addMarcer(map, unit);
    }

}
