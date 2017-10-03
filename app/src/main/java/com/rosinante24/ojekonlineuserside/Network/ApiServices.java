package com.rosinante24.ojekonlineuserside.Network;

import com.rosinante24.ojekonlineuserside.Response.ResponseBooking;
import com.rosinante24.ojekonlineuserside.Response.ResponseCancelBooking;
import com.rosinante24.ojekonlineuserside.Response.ResponseCheckBooking;
import com.rosinante24.ojekonlineuserside.Response.ResponseHistory;
import com.rosinante24.ojekonlineuserside.Response.ResponseLogin;
import com.rosinante24.ojekonlineuserside.Response.ResponseRegister;
import com.rosinante24.ojekonlineuserside.Response.ResponseRoute;
import com.rosinante24.ojekonlineuserside.Response.ResponseTrackingInduk;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by KOCHOR on 9/12/2017.
 */

public interface ApiServices {
    @FormUrlEncoded
    @POST("daftar")
    Call<ResponseRegister> request_register(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> request_login(
            @Field("device") String device,
            @Field("f_email") String email,
            @Field("f_password") String password
    );

    @GET("json")
    Call<ResponseRoute> request_route(
            @Query("origin") String origin,
            @Query("destination") String destination
    );

    @FormUrlEncoded
    @POST("insert_booking")
    Call<ResponseBooking> request_booking(
            @Field("f_token") String token,
            @Field("f_device") String device,
            @Field("f_idUser") String idUser,
            @Field("f_latAwal") String latAwal,
            @Field("f_lngAwal") String langAawal,
            @Field("f_awal") String awal,
            @Field("f_latAkhir") String latAkhir,
            @Field("f_lngAkhir") String langAkhir,
            @Field("f_akhir") String akhir,
            @Field("f_jarak") String jarak,
            @Field("f_tarif") String tarif
    );

    @FormUrlEncoded
    @POST("cancel_booking")
    Call<ResponseCancelBooking> request_cancel(
            @Field("id_booking") String id_booking,
            @Field("f_token") String token,
            @Field("f_device") String device
    );

    @FormUrlEncoded
    @POST("check_booking")
    Call<ResponseCheckBooking> request_check(
            @Field("id_booking") String id_booking,
            @Field("f_token") String token,
            @Field("f_device") String device
    );

    @FormUrlEncoded
    @POST("get_driver")
    Call<ResponseTrackingInduk> request_tracking(
            @Field("id") String id_driver
    );


    @FormUrlEncoded
    @POST("get_booking")
    Call<ResponseHistory> request_history(
            @Field("status") String status,
            @Field("f_idUser") String id_user
    );



}
