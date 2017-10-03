package com.rosinante24.ojekonlineuserside.Response;

import com.google.gson.annotations.SerializedName;


public class ResponseBooking {

    @SerializedName("id_booking")
    private Long mIdBooking;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("result")
    private String mResult;
    @SerializedName("waktu")
    private String mWaktu;

    public Long getIdBooking() {
        return mIdBooking;
    }

    public void setIdBooking(Long idBooking) {
        mIdBooking = idBooking;
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

    public String getWaktu() {
        return mWaktu;
    }

    public void setWaktu(String waktu) {
        mWaktu = waktu;
    }

}
