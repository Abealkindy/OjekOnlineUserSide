package com.rosinante24.ojekonlineuserside.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante24.ojekonlineuserside.Fonts.Fonts;
import com.rosinante24.ojekonlineuserside.Helper.HeroHelper;
import com.rosinante24.ojekonlineuserside.Helper.SessionManager;
import com.rosinante24.ojekonlineuserside.Maps.GPSTracker;
import com.rosinante24.ojekonlineuserside.Network.ApiServices;
import com.rosinante24.ojekonlineuserside.Network.InitLibrary;
import com.rosinante24.ojekonlineuserside.R;
import com.rosinante24.ojekonlineuserside.Response.DataLogin;
import com.rosinante24.ojekonlineuserside.Response.ResponseLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.text_title_login)
    TextView textTitleLogin;
    @BindView(R.id.edit_text_email_login)
    EditText editTextEmailLogin;
    @BindView(R.id.edit_text_password_login)
    EditText editTextPasswordLogin;
    @BindView(R.id.button_masuk_login)
    Button buttonMasukLogin;
    @BindView(R.id.text_link_to_register)
    TextView textLinkToRegister;

    String emaillogin, passwordlogin;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (!GPSTracker.checkPermission(this)) {
            requestPermissions(
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    }
                    , 12
            );
        }

        Fonts.MontserratExtraLight(this, textTitleLogin);
        Fonts.RobotoRegular(this, textLinkToRegister);

    }

    @OnClick({R.id.button_masuk_login, R.id.text_link_to_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_masuk_login:

                emaillogin = editTextEmailLogin.getText().toString();
                passwordlogin = editTextPasswordLogin.getText().toString();
                String Imei = HeroHelper.getDeviceId(this);

                if (emaillogin.isEmpty()) {
                    editTextEmailLogin.setError("Isi email terlebih dahulu!");
                    editTextEmailLogin.requestFocus();
                } else if (passwordlogin.isEmpty()) {
                    editTextPasswordLogin.setError("Isi password terlebih dahulu!");
                } else {

                    if (!emaillogin.isEmpty() && !passwordlogin.isEmpty()) {

                        ApiServices api = InitLibrary.getInstances();

                        Call<ResponseLogin> call = api.request_login(Imei, emaillogin, passwordlogin);

                        call.enqueue(new Callback<ResponseLogin>() {
                            @Override
                            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                                String result = response.body().getResult();
                                String message = response.body().getMsg();


                                if (result.equals("true")) {
                                    String token = response.body().getToken();

                                    DataLogin data = response.body().getData();
                                    String id_user = data.getIdUser();
                                    String nama_user = data.getUserNama();
                                    String email_user = data.getUserEmail();
                                    String telp_user = data.getUserHp();

                                    SessionManager sesi = new SessionManager(LoginActivity.this);
                                    sesi.cerateLoginSession(token);
                                    sesi.setIduser(id_user);
                                    sesi.setEmail(email_user);
                                    sesi.setNama(nama_user);
                                    sesi.setPhone(telp_user);

                                    startActivity(new Intent(getApplicationContext(), MainMapsActivity.class));
                                    finish();

                                } else {

                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLogin> call, Throwable t) {

                            }

                        });
                    }
                }
                break;
            case R.id.text_link_to_register:
                startActivity(new Intent(LoginActivity.this, DaftarActivity.class));
                finish();
                break;
        }
    }
}
