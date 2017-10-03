
package com.rosinante24.ojekonlineuserside.Response;

import com.google.gson.annotations.SerializedName;


public class DataLogin {

    @SerializedName("id_user")
    private String mIdUser;
    @SerializedName("user_avatar")
    private Object mUserAvatar;
    @SerializedName("user_email")
    private String mUserEmail;
    @SerializedName("user_gcm")
    private Object mUserGcm;
    @SerializedName("user_hp")
    private String mUserHp;
    @SerializedName("user_level")
    private String mUserLevel;
    @SerializedName("user_nama")
    private String mUserNama;
    @SerializedName("user_password")
    private String mUserPassword;
    @SerializedName("user_register")
    private String mUserRegister;
    @SerializedName("user_status")
    private String mUserStatus;

    public String getIdUser() {
        return mIdUser;
    }

    public void setIdUser(String idUser) {
        mIdUser = idUser;
    }

    public Object getUserAvatar() {
        return mUserAvatar;
    }

    public void setUserAvatar(Object userAvatar) {
        mUserAvatar = userAvatar;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
    }

    public Object getUserGcm() {
        return mUserGcm;
    }

    public void setUserGcm(Object userGcm) {
        mUserGcm = userGcm;
    }

    public String getUserHp() {
        return mUserHp;
    }

    public void setUserHp(String userHp) {
        mUserHp = userHp;
    }

    public String getUserLevel() {
        return mUserLevel;
    }

    public void setUserLevel(String userLevel) {
        mUserLevel = userLevel;
    }

    public String getUserNama() {
        return mUserNama;
    }

    public void setUserNama(String userNama) {
        mUserNama = userNama;
    }

    public String getUserPassword() {
        return mUserPassword;
    }

    public void setUserPassword(String userPassword) {
        mUserPassword = userPassword;
    }

    public String getUserRegister() {
        return mUserRegister;
    }

    public void setUserRegister(String userRegister) {
        mUserRegister = userRegister;
    }

    public String getUserStatus() {
        return mUserStatus;
    }

    public void setUserStatus(String userStatus) {
        mUserStatus = userStatus;
    }

}
