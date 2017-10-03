
package com.rosinante24.ojekonlineuserside.Response;

import com.google.gson.annotations.SerializedName;

public class Step {

    @SerializedName("distance")
    private Distance mDistance;
    @SerializedName("duration")
    private Duration mDuration;
    @SerializedName("end_location")
    private EndLocation mEndLocation;
    @SerializedName("html_instructions")
    private String mHtmlInstructions;
    @SerializedName("maneuver")
    private String mManeuver;
    @SerializedName("polyline")
    private Polyline mPolyline;
    @SerializedName("start_location")
    private StartLocation mStartLocation;
    @SerializedName("travel_mode")
    private String mTravelMode;

    public Distance getDistance() {
        return mDistance;
    }

    public void setDistance(Distance distance) {
        mDistance = distance;
    }

    public Duration getDuration() {
        return mDuration;
    }

    public void setDuration(Duration duration) {
        mDuration = duration;
    }

    public EndLocation getEndLocation() {
        return mEndLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        mEndLocation = endLocation;
    }

    public String getHtmlInstructions() {
        return mHtmlInstructions;
    }

    public void setHtmlInstructions(String htmlInstructions) {
        mHtmlInstructions = htmlInstructions;
    }

    public String getManeuver() {
        return mManeuver;
    }

    public void setManeuver(String maneuver) {
        mManeuver = maneuver;
    }

    public Polyline getPolyline() {
        return mPolyline;
    }

    public void setPolyline(Polyline polyline) {
        mPolyline = polyline;
    }

    public StartLocation getStartLocation() {
        return mStartLocation;
    }

    public void setStartLocation(StartLocation startLocation) {
        mStartLocation = startLocation;
    }

    public String getTravelMode() {
        return mTravelMode;
    }

    public void setTravelMode(String travelMode) {
        mTravelMode = travelMode;
    }

}
