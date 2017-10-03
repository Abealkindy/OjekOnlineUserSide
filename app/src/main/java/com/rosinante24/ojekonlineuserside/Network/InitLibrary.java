package com.rosinante24.ojekonlineuserside.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KOCHOR on 9/12/2017.
 */

public class InitLibrary {

    public static Retrofit setInit(){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.100.15/ojeg_server/index.php/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApiServices getInstances(){
        return setInit().create(ApiServices.class);
    }
    public static Retrofit initDirections(){
        return  new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/directions/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getDirections(){
        return initDirections().create(ApiServices.class);
    }
}
