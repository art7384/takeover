package com.sabotage.takeover.businesslogic.managers;

import com.google.android.gms.maps.model.LatLng;
import com.sabotage.takeover.R;
import com.sabotage.takeover.config.MapConfig;
import com.sabotage.takeover.model.Totem;

/**
 * Created by Кармишин on 05.08.2015.
 */
public class TotemManager {

    public Totem create(LatLng latLng) {
        Totem totem = new Totem();
        totem.setPosition(latLng);
        totem.setName("Totem");
        totem.setFillColor(MapConfig.TOTEM_fILL_COLOR);
        totem.setStrokeColor(MapConfig.TOTEM_STROKE_COLOR);
        totem.setStrokeWidth(MapConfig.TOTEM_STROKE_WIDTH);
        totem.setRadius(MapConfig.TOTEM_RADIUS);
        return totem;
    }

}
