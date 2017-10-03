
package com.rosinante24.ojekonlineuserside.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCheckBooking {

    @SerializedName("data")
    private List<DatumFindDriver> mData;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("result")
    private String mResult;

    public List<DatumFindDriver> getData() {
        return mData;
    }

    public void setData(List<DatumFindDriver> data) {
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
