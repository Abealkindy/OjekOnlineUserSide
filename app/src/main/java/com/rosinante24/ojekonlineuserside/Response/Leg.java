
package com.rosinante24.ojekonlineuserside.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Leg {

    @SerializedName("distance")
    private Distance mDistance;
    @SerializedName("duration")
    private Duration mDuration;
    @SerializedName("end_address")
    private String mEndAddress;
    @SerializedName("end_location")
    private EndLocation mEndLocation;
    @SerializedName("start_address")
    private String mStartAddress;
    @SerializedName("start_location")
    private StartLocation mStartLocation;
    @SerializedName("steps")
    private List<Step> mSteps;
    @SerializedName("traffic_speed_entry")
    private List<Object> mTrafficSpeedEntry;
    @SerializedName("via_waypoint")
    private List<Object> mViaWaypoint;

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

    public String getEndAddress() {
        return mEndAddress;
    }

    public void setEndAddress(String endAddress) {
        mEndAddress = endAddress;
    }

    public EndLocation getEndLocation() {
        return mEndLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        mEndLocation = endLocation;
    }

    public String getStartAddress() {
        return mStartAddress;
    }

    public void setStartAddress(String startAddress) {
        mStartAddress = startAddress;
    }

    public StartLocation getStartLocation() {
        return mStartLocation;
    }

    public void setStartLocation(StartLocation startLocation) {
        mStartLocation = startLocation;
    }

    public List<Step> getSteps() {
        return mSteps;
    }

    public void setSteps(List<Step> steps) {
        mSteps = steps;
    }

    public List<Object> getTrafficSpeedEntry() {
        return mTrafficSpeedEntry;
    }

    public void setTrafficSpeedEntry(List<Object> trafficSpeedEntry) {
        mTrafficSpeedEntry = trafficSpeedEntry;
    }

    public List<Object> getViaWaypoint() {
        return mViaWaypoint;
    }

    public void setViaWaypoint(List<Object> viaWaypoint) {
        mViaWaypoint = viaWaypoint;
    }

}
