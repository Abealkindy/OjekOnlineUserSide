
package com.rosinante24.ojekonlineuserside.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseRoute {

    @SerializedName("geocoded_waypoints")
    private List<GeocodedWaypoint> mGeocodedWaypoints;
    @SerializedName("routes")
    private List<Route> mRoutes;
    @SerializedName("status")
    private String mStatus;

    public List<GeocodedWaypoint> getGeocodedWaypoints() {
        return mGeocodedWaypoints;
    }

    public void setGeocodedWaypoints(List<GeocodedWaypoint> geocodedWaypoints) {
        mGeocodedWaypoints = geocodedWaypoints;
    }

    public List<Route> getRoutes() {
        return mRoutes;
    }

    public void setRoutes(List<Route> routes) {
        mRoutes = routes;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
