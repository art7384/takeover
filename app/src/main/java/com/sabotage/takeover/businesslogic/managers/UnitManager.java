package com.sabotage.takeover.businesslogic.managers;

import com.google.android.gms.maps.model.LatLng;
import com.sabotage.takeover.config.MapConfig;
import com.sabotage.takeover.model.Unit;

/**
 * Created by Кармишин on 25.09.2015.
 */
public class UnitManager {

    public Unit create(LatLng latLng) {
        Unit totem = new Unit();
        totem.setPosition(latLng);
        totem.setName("Unit");
        totem.setFillColor(MapConfig.UNIT_fILL_COLOR);
        totem.setStrokeColor(MapConfig.UNIT_STROKE_COLOR);
        totem.setStrokeWidth(MapConfig.UNIT_STROKE_WIDTH);
        totem.setRadius(MapConfig.TOTEM_RADIUS);
        return totem;
    }
}
