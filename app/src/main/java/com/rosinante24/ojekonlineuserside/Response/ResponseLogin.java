
package com.rosinante24.ojekonlineuserside.Response;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("data")
    private DataLogin mData;
    @SerializedName("idUser")
    private String mIdUser;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("result")
    private String mResult;
    @SerializedName("token")
    private String mToken;

    public DataLogin getData() {
        return mData;
    }

    public void setData(DataLogin data) {
        mData = data;
    }

    public String getIdUser() {
        return mIdUser;
    }

    public void setIdUser(String idUser) {
        mIdUser = idUser;
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

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

}
