package com.rosinante24.ojekonlineuserside.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.rosinante24.ojekonlineuserside.Helper.HeroHelper;
import com.rosinante24.ojekonlineuserside.Helper.SessionManager;
import com.rosinante24.ojekonlineuserside.Network.ApiServices;
import com.rosinante24.ojekonlineuserside.Network.InitLibrary;
import com.rosinante24.ojekonlineuserside.Activity.MainMapsActivity;
import com.rosinante24.ojekonlineuserside.R;
import com.rosinante24.ojekonlineuserside.Response.DatumFindDriver;
import com.rosinante24.ojekonlineuserside.Response.ResponseCancelBooking;
import com.rosinante24.ojekonlineuserside.Response.ResponseCheckBooking;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Finddriver extends AppCompatActivity {


    @BindView(R.id.pulsator)
    PulsatorLayout pulsator;
    @BindView(R.id.buttoncancel)
    Button buttoncancel;

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finddriver);
        ButterKnife.bind(this);

        pulsator.start();
    }

    @OnClick(R.id.buttoncancel)
    public void onClick() {
        AlertDialog.Builder alert = new AlertDialog.Builder(Finddriver.this);
        alert.setTitle("Konfirmasi")
                .setMessage("Anda yakin ingin membatalkan order?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        cancelBooking();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();

    }

    private void cancelBooking() {
        ApiServices api = InitLibrary.getInstances();

        String id_booking = String.valueOf(getIntent().getLongExtra("id_booking", 0));

        SessionManager sesi = new SessionManager(Finddriver.this);

        String token = sesi.getToken();
        String device_id = HeroHelper.getDeviceId(Finddriver.this);
        Call<ResponseCancelBooking> call = api.request_cancel(
                id_booking,
                token,
                device_id
        );
        call.enqueue(new Callback<ResponseCancelBooking>() {
            @Override
            public void onResponse(Call<ResponseCancelBooking> call, Response<ResponseCancelBooking> response) {
                if (response.body().getResult().equals("true")) {
                    startActivity(new Intent(Finddriver.this, MainMapsActivity.class));
                    finish();
                } else {
                    HeroHelper.pesan(Finddriver.this, response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseCancelBooking> call, Throwable t) {

            }
        });
    }


    private void checkBooking() {

        ApiServices api = InitLibrary.getInstances();

        String id_booking = String.valueOf(getIntent().getLongExtra("id_booking", 0));

        SessionManager sesi = new SessionManager(Finddriver.this);

        String token = sesi.getToken();
        String device_id = HeroHelper.getDeviceId(Finddriver.this);

        Call<ResponseCheckBooking> call = api.request_check(
                id_booking,
                token,
                device_id
        );

        call.enqueue(new Callback<ResponseCheckBooking>() {
            @Override
            public void onResponse(Call<ResponseCheckBooking> call, Response<ResponseCheckBooking> response) {
                if (response.body().getResult().equals("true")) {

                    List<DatumFindDriver> data = response.body().getData();
                    String lokasiAwal = data.get(0).getBookingFrom();
                    String lokasiTujuan = data.get(0).getBookingTujuan();

                    String idDriver = data.get(0).getBookingDriver();

                    Intent intens = new Intent(Finddriver.this, PosisiDriver.class);
                    intens.putExtra("lokasi_awal", lokasiAwal)
                            .putExtra("lokasi_tujuan", lokasiTujuan)
                            .putExtra("id_driver", idDriver);
                    startActivity(intens);

                } else {
                    HeroHelper.pesan(Finddriver.this, response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseCheckBooking> call, Throwable t) {

            }
        });

    }


    @Override
    protected void onResume() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                checkBooking();

            }
        }, 0, 1000);
        super.onResume();
    }

    @Override
    protected void onPause() {
        timer.cancel();
        super.onPause();
    }
}
