
package com.rosinante24.ojekonlineuserside.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Route {

    @SerializedName("bounds")
    private Bounds mBounds;
    @SerializedName("copyrights")
    private String mCopyrights;
    @SerializedName("legs")
    private List<Leg> mLegs;
    @SerializedName("overview_polyline")
    private OverviewPolyline mOverviewPolyline;
    @SerializedName("summary")
    private String mSummary;
    @SerializedName("warnings")
    private List<Object> mWarnings;
    @SerializedName("waypoint_order")
    private List<Object> mWaypointOrder;

    public Bounds getBounds() {
        return mBounds;
    }

    public void setBounds(Bounds bounds) {
        mBounds = bounds;
    }

    public String getCopyrights() {
        return mCopyrights;
    }

    public void setCopyrights(String copyrights) {
        mCopyrights = copyrights;
    }

    public List<Leg> getLegs() {
        return mLegs;
    }

    public void setLegs(List<Leg> legs) {
        mLegs = legs;
    }

    public OverviewPolyline getOverviewPolyline() {
        return mOverviewPolyline;
    }

    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        mOverviewPolyline = overviewPolyline;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public List<Object> getWarnings() {
        return mWarnings;
    }

    public void setWarnings(List<Object> warnings) {
        mWarnings = warnings;
    }

    public List<Object> getWaypointOrder() {
        return mWaypointOrder;
    }

    public void setWaypointOrder(List<Object> waypointOrder) {
        mWaypointOrder = waypointOrder;
    }

}
