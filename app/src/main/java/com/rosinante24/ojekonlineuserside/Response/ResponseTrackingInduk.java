package com.rosinante24.ojekonlineuserside.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KOCHOR on 9/15/2017.
 */

public class ResponseTrackingInduk {
    @SerializedName("data")
    private List<ResponseTracking> mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("result")
    private String mResult;

    public List<ResponseTracking> getData() {
        return mData;
    }

    public void setData(List<ResponseTracking> data) {
        mData = data;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }
}
