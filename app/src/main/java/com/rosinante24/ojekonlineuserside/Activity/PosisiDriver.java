package com.rosinante24.ojekonlineuserside.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.rosinante24.ojekonlineuserside.Network.ApiServices;
import com.rosinante24.ojekonlineuserside.Network.InitLibrary;
import com.rosinante24.ojekonlineuserside.Activity.MainMapsActivity;
import com.rosinante24.ojekonlineuserside.R;
import com.rosinante24.ojekonlineuserside.Response.ResponseTracking;
import com.rosinante24.ojekonlineuserside.Response.ResponseTrackingInduk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PosisiDriver extends FragmentActivity implements OnMapReadyCallback {

    @BindView(R.id.lokasiawals)
    TextView lokasiawals;
    @BindView(R.id.lokasitujuans)
    TextView lokasitujuans;
    @BindView(R.id.txtnamadriver)
    TextView txtnamadriver;
    @BindView(R.id.txthpdriver)
    TextView txthpdriver;
    private GoogleMap mMap;
    Timer autoUpdate;

    String id_driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posisi_driver);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        String lokasiAwal = getIntent().getStringExtra("lokasi_awal");
        String lokasiTujuan = getIntent().getStringExtra("lokasi_tujuan");

        lokasiawals.setText(lokasiAwal);
        lokasitujuans.setText(lokasiTujuan);
        id_driver = getIntent().getStringExtra("id_driver");


        getDriver();
    }

    private void getDriver() {
        ApiServices api = InitLibrary.getInstances();
        id_driver = "133";
        Call<ResponseTrackingInduk> call = api.request_tracking(id_driver);
        call.enqueue(new Callback<ResponseTrackingInduk>() {
            @Override
            public void onResponse(Call<ResponseTrackingInduk> call, Response<ResponseTrackingInduk> response) {
                if (response.isSuccessful()) {

                    List<ResponseTracking> data = response.body().getData();
                    String lat = data.get(0).getTrackingLat();
                    String lon = data.get(0).getTrackingLng();
                    txtnamadriver.setText(data.get(0).getUserNama());
                    txthpdriver.setText(data.get(0).getUserHp());

                    LatLng posisi = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));

                    mMap.addMarker(new MarkerOptions().position(posisi).title("Your Driver"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posisi, 12));
                }
            }

            @Override
            public void onFailure(Call<ResponseTrackingInduk> call, Throwable t) {

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @OnClick({R.id.lokasiawals, R.id.lokasitujuans})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lokasiawals:
                break;
            case R.id.lokasitujuans:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
                getDriver();
            }
        }, 0, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        autoUpdate.cancel();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Where you wanna go?")
                    .setPositiveButton("Exit App", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("Back to main", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(PosisiDriver.this, MainMapsActivity.class));
                            finish();
                        }
                    })
                    .show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
