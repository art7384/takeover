package com.sabotage.takeover.businesslogic.managers;

import com.sabotage.takeover.config.MapConfig;
import com.sabotage.takeover.model.Zone;

/**
 * Created by Кармишин on 06.08.2015.
 */
public class ZoneManager {

    public Zone crieit() {
        Zone zone = new Zone();
        zone.setRadius(MapConfig.ZONE_RADIUS);
        zone.setStrokeColor(MapConfig.ZONE_STROKE_COLOR);
        zone.setStrokeWidth(MapConfig.ZONE_STROKE_WIDTH);
        zone.setPosition(MapConfig.ZONE_LAT_LONG);
        return zone;
    }

}
