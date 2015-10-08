package com.sabotage.takeover.businesslogic.parser;

import com.google.android.gms.maps.model.LatLng;
import com.sabotage.takeover.model.PolylineList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karmishin on 04.08.2015.
 */
public class PolylineParser {

    static public PolylineList create(JSONObject jsObj) throws JSONException {

        PolylineList line = null;
        String status = jsObj.getString(Param.STATUS.toString());

        if (status.equals(PolylineList.Status.OK)) {
            JSONArray routesArray = jsObj.getJSONArray(Param.ROUTES.toString());
            line = ok(routesArray);
        } else {
            line = new PolylineList();
            line.status = status;
            if (status.equalsIgnoreCase(PolylineList.Status.NOT_FOUND)) {
                // по крайней мере для одной заданной точки (исходной точки, пункта назначения или путевой точки) геокодирование невозможно.
            } else if (status.equalsIgnoreCase(PolylineList.Status.ZERO_RESULTS)) {
                // между исходной точкой и пунктом назначения не найдено ни одного маршрута.
            } else if (status.equalsIgnoreCase(PolylineList.Status.MAX_WAYPOINTS_EXCEEDED)) {
                // в запросе задано слишком много waypoints. Максимальное количество waypoints равно 8 плюс исходная точка и пункт назначения. ( (Пользователи Google Maps Premier могут выполнять запросы с количеством путевых точек до 23.)
            } else if (status.equalsIgnoreCase(PolylineList.Status.INVALID_REQUEST)) {
                // запрос недопустим
            } else if (status.equalsIgnoreCase(PolylineList.Status.OVER_QUERY_LIMIT)) {
                // служба получила слишком много запросов от вашего приложения в разрешенный период времени.
            } else if (status.equalsIgnoreCase(PolylineList.Status.REQUEST_DENIED)) {
                // служба Directions отклонила запрос вашего приложения.
            } else if (status.equalsIgnoreCase(PolylineList.Status.UNKNOWN_ERROR)) {
                // обработка запроса маршрута невозможна из-за ошибки сервера. При повторной попытке запрос может быть успешно выполнен
            } else {

            }
        }
        return line;
    }

    static private PolylineList ok(JSONArray routesArray) throws JSONException {

        JSONObject route = routesArray.getJSONObject(0);

        PolylineList polylineList = new PolylineList();
        polylineList.status = PolylineList.Status.OK;

        JSONArray legs = route.getJSONArray(Param.LEGS.toString());
        JSONObject leg = legs.getJSONObject(0);

        JSONObject distanceObj = leg.getJSONObject(Param.DISTANCE.toString());
        polylineList.distance = distanceObj.getLong(Param.VALUE.toString());

        JSONObject durationObj = leg.getJSONObject(Param.DURATION.toString());

        // содержит куб выделения информационного окна для маршрута.
        JSONObject bounds = route.getJSONObject(Param.BOUNDS.toString());
        JSONObject bounds_southwest = bounds.getJSONObject(Param.SOUTHWEST.toString());
        JSONObject bounds_northeast = bounds.getJSONObject(Param.NORTHEAAST.toString());

        double latitudeMax = bounds_northeast.getDouble(Param.LAT.toString());
        double longitudeMax = bounds_northeast.getDouble(Param.LNG.toString());
        double latitudeMin = bounds_southwest.getDouble(Param.LAT.toString());
        double longitudeMin = bounds_southwest.getDouble(Param.LNG.toString());
        polylineList.maxLatLng = new LatLng(latitudeMax, longitudeMax);
        polylineList.minLatLng = new LatLng(latitudeMin, longitudeMin);

        JSONArray steps = leg.getJSONArray(Param.STEPS.toString());
        for (int i = 0; i < steps.length(); i++) {
            JSONObject step = steps.getJSONObject(i);
            JSONObject start_location = step.getJSONObject(Param.START_LOCATION.toString());
            JSONObject end_location = step.getJSONObject(Param.END_LOCATION.toString());

            double latitudeStart = start_location.getDouble(Param.LAT.toString());
            double longitudeStart = start_location.getDouble(Param.LNG.toString());
            double latitudeEnd = end_location.getDouble(Param.LAT.toString());
            double longitudeEnd = end_location.getDouble(Param.LNG.toString());
            LatLng startGeoPoint = new LatLng(latitudeStart, longitudeStart);
            LatLng endGeoPoint = new LatLng(latitudeEnd, longitudeEnd);
            JSONObject polylineObject = step.getJSONObject(Param.POLYLINE.toString());

            //if(accuracyRoute == FINE_ROUTE ){
            List points = decodePoly(polylineObject.getString(Param.POINTS.toString()));
            polylineList.addAll(points);
//                } else {
//                    polyline.add(startGeoPoint);
//                    polyline.add(endGeoPoint);
//                }

        }
        return polylineList;
    }

    static private List decodePoly(String encoded) {

        List poly = new ArrayList();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng(lat / 1E5, lng / 1E5);
            poly.add(p);
        }

        return poly;
    }

    private enum Param {
        STATUS("status"),
        ROUTES("routes"),
        LEGS("legs"),
        DISTANCE("distance"),
        VALUE("value"),
        DURATION("duration"),
        BOUNDS("bounds"),
        SOUTHWEST("southwest"),
        NORTHEAAST("northeast"),
        LAT("lat"),
        LNG("lng"),
        STEPS("steps"),
        START_LOCATION("start_location"),
        END_LOCATION("end_location"),
        POLYLINE("polyline"),
        POINTS("points");
        String param;

        Param(String param) {
            this.param = param;
        }

        @Override
        public String toString() {
            return param;
        }

    }
}
