package com.rosinante24.ojekonlineuserside.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rosinante24.ojekonlineuserside.Helper.HeroHelper;
import com.rosinante24.ojekonlineuserside.Helper.SessionManager;
import com.rosinante24.ojekonlineuserside.Maps.DirectionMapsV2;
import com.rosinante24.ojekonlineuserside.Network.ApiServices;
import com.rosinante24.ojekonlineuserside.Network.InitLibrary;
import com.rosinante24.ojekonlineuserside.R;
import com.rosinante24.ojekonlineuserside.Response.Distance;
import com.rosinante24.ojekonlineuserside.Response.Leg;
import com.rosinante24.ojekonlineuserside.Response.OverviewPolyline;
import com.rosinante24.ojekonlineuserside.Response.ResponseBooking;
import com.rosinante24.ojekonlineuserside.Response.ResponseRoute;
import com.rosinante24.ojekonlineuserside.Response.Route;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @BindView(R.id.lokasiawal)
    EditText lokasiawal;
    @BindView(R.id.lokasitujuan)
    EditText lokasitujuan;
    @BindView(R.id.text_price)
    TextView textPrice;
    @BindView(R.id.requestorder)
    Button requestorder;
    private GoogleMap mMap;
    // origin - lokasi awal
    private double latawal;
    private double lonawal;
    // destination - lokasi tujuan
    private double latakhir;
    private double lonakhir;
    private Long km_jarak;
    private Double ongkos_ojeg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_maps);
        ButterKnife.bind(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.history:
                startActivity(new Intent(MainMapsActivity.this, History.class));
                break;

            case R.id.Profil:
                pindahProfil();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void pindahProfil() {
        final SessionManager sesi = new SessionManager(MainMapsActivity.this);

        AlertDialog.Builder alert = new AlertDialog.Builder(MainMapsActivity.this);
        alert.setTitle("Profil Pengguna");
        alert.setMessage("Nama : " + sesi.getNama() + " \n" +
                "Email : " + sesi.getEmail() + " \n" +
                "No HP : " + sesi.getPhone() + " \n");
        alert.setPositiveButton("LogOut", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sesi.logout(); // logout
                startActivity(new Intent(MainMapsActivity.this, LoginActivity.class));
                finish();
            }
        });
        alert.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode != 0) {
            // ambil data
            Place place = PlaceAutocomplete.getPlace(MainMapsActivity.this, data);
            // dapatkan koordinat tempat
            latawal = place.getLatLng().latitude; // bujur
            lonawal = place.getLatLng().longitude; // lintang

            LatLng posisi_awal = new LatLng(latawal, lonawal);
            // bersihkan map dari marker sebelumnya
            mMap.clear();

            // buat marker
            mMap.addMarker(new MarkerOptions().position(posisi_awal)
                    .title(place.getAddress().toString()));
            lokasiawal.setMaxLines(1);
            lokasiawal.setText(place.getAddress());
            // set auto zoom
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posisi_awal, 16)); // 16 = level zoom
        } else if (requestCode == 2 & resultCode != 0) {
            // ambil data
            Place place = PlaceAutocomplete.getPlace(MainMapsActivity.this, data);
            // dapatkan koordinat tempat
            latakhir = place.getLatLng().latitude; // bujur
            lonakhir = place.getLatLng().longitude; // lintang

            LatLng posisi_akhir = new LatLng(latakhir, lonakhir);

            // buat marker
            mMap.addMarker(new MarkerOptions().position(posisi_akhir)
                    .title(place.getAddress().toString()));
            lokasitujuan.setMaxLines(1);
            lokasitujuan.setText(place.getAddress());
            // set auto zoom
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posisi_akhir, 16)); // 16 = level zoom

            // buat polyline
            actionRoute();
        }
    }


    private void actionRoute() {
        ApiServices api = InitLibrary.getDirections();
        // 121.2123, 121.121
        // koordinat awal
        String origin = String.valueOf(latawal) + ", " + String.valueOf(lonawal);
        // koordinat tujuan
        String destination = String.valueOf(latakhir) + ", " + String.valueOf(lonakhir);

        // siapkan request
        Call<ResponseRoute> call = api.request_route(origin, destination);
        //
        call.enqueue(new Callback<ResponseRoute>() {
            @Override
            public void onResponse(Call<ResponseRoute> call, Response<ResponseRoute> response) {
                if (response.isSuccessful()) {
                    // get response from server
                    List<Route> object = response.body().getRoutes();
                    Route route = object.get(0);
                    OverviewPolyline overview = route.getOverviewPolyline();
                    // 10.10.10.128/DirectionMapsV2.java.zip
                    String point = overview.getPoints();
                    DirectionMapsV2 direction = new DirectionMapsV2(MainMapsActivity.this);
                    direction.gambarRoute(mMap, point);

                    // ambil jarak
                    List<Leg> legs = route.getLegs();
                    Leg data_leg = legs.get(0);
                    // jarak
                    Distance jarak = data_leg.getDistance();
                    // jarak meter & jarak string
                    String jarak_tempuh = jarak.getText();

                    km_jarak = jarak.getValue() / 1000;
                    // harga ngojek
                    ongkos_ojeg = Double.parseDouble(String.valueOf(km_jarak)) * 2000;
                    // set ke textview
                    textPrice.setText("Rp. " + HeroHelper.toRupiahFormat2(ongkos_ojeg.toString()));


                }
            }

            @Override
            public void onFailure(Call<ResponseRoute> call, Throwable t) {

            }
        });


    }


    private void completeAuto(int type) {
        // filter result of places
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("ID")
                .build();

        Intent intent = null;
        try {
            // menampilkan overlay search box untuk auto complete places
            intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .setFilter(typeFilter)
                    .build(MainMapsActivity.this);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
        startActivityForResult(intent, type);
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
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    @OnClick({R.id.lokasiawal, R.id.lokasitujuan, R.id.requestorder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lokasiawal:
                completeAuto(1);
                break;
            case R.id.lokasitujuan:
                completeAuto(2);
                break;
            case R.id.requestorder:
                requestOrder();
                break;
        }
    }


    private void requestOrder() {

        SessionManager sesi = new SessionManager(MainMapsActivity.this);
        String token = sesi.getToken();
        String deviceId = HeroHelper.getDeviceId(MainMapsActivity.this);
        String id_user = sesi.getIdUser();
        String alamat_awal = lokasiawal.getText().toString();
        String alamat_akhir = lokasitujuan.getText().toString();
        String lat1 = String.valueOf(latawal);
        String long1 = String.valueOf(lonawal);
        String lat2 = String.valueOf(latakhir);
        String long2 = String.valueOf(lonakhir);
        String tarif_order = ongkos_ojeg.toString();
        String jarak_tempuh = km_jarak.toString();

        ApiServices api = InitLibrary.getInstances();
        Call<ResponseBooking> call = api.request_booking(
                token, deviceId, id_user, lat1, long1, alamat_awal, lat2, long2, alamat_akhir, jarak_tempuh, tarif_order
        );
        call.enqueue(new Callback<ResponseBooking>() {
            @Override
            public void onResponse(Call<ResponseBooking> call, Response<ResponseBooking> response) {
                long id_booking = response.body().getIdBooking();
                if (response.body().getResult().equals("true")) {
                    Intent intent = new Intent(MainMapsActivity.this, Finddriver.class);
                    intent.putExtra("id_booking", id_booking);
                    startActivity(intent);
                } else {
                    HeroHelper.pesan(MainMapsActivity.this, response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResponseBooking> call, Throwable t) {

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2) {
            mMap.isMyLocationEnabled();
        }
    }
}
