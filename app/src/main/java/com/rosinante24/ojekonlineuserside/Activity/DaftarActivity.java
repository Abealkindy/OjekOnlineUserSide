package com.rosinante24.ojekonlineuserside.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante24.ojekonlineuserside.Fonts.Fonts;
import com.rosinante24.ojekonlineuserside.Network.ApiServices;
import com.rosinante24.ojekonlineuserside.Network.InitLibrary;
import com.rosinante24.ojekonlineuserside.R;
import com.rosinante24.ojekonlineuserside.Response.ResponseRegister;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarActivity extends AppCompatActivity {

    @BindView(R.id.text_title_daftar)
    TextView textTitleDaftar;
    @BindView(R.id.edit_text_username_daftar)
    EditText editTextUsernameDaftar;
    @BindView(R.id.edit_text_email_daftar)
    EditText editTextEmailDaftar;
    @BindView(R.id.edit_text_phone_number_daftar)
    EditText editTextPhoneNumberDaftar;
    @BindView(R.id.edit_text_password_daftar)
    EditText editTextPasswordDaftar;
    @BindView(R.id.edit_text_confirm_password_daftar)
    EditText editTextConfirmPasswordDaftar;
    @BindView(R.id.button_daftar)
    Button buttonDaftar;

    String usernamedaftar, emaildaftar, phonenumberdaftar, passworddaftar, confirmpassworddaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        ButterKnife.bind(this);

        Fonts.MontserratExtraLight(this, textTitleDaftar);
    }

    @OnClick(R.id.button_daftar)
    public void onClick() {
        usernamedaftar = editTextUsernameDaftar.getText().toString();
        emaildaftar = editTextEmailDaftar.getText().toString();
        phonenumberdaftar = editTextPhoneNumberDaftar.getText().toString();
        passworddaftar = editTextPasswordDaftar.getText().toString();
        confirmpassworddaftar = editTextConfirmPasswordDaftar.getText().toString();

        if (usernamedaftar.isEmpty()) {
            editTextUsernameDaftar.setError("Isi username terlebih dahulu!");
            editTextUsernameDaftar.requestFocus();
        } else if (emaildaftar.isEmpty()) {
            editTextEmailDaftar.setError("Isi email terlebih dahulu!");
            editTextEmailDaftar.requestFocus();
        } else if (phonenumberdaftar.isEmpty()) {
            editTextPhoneNumberDaftar.setError("Isi nomor telpon terlebih dahulu!");
            editTextPhoneNumberDaftar.requestFocus();
        } else if (passworddaftar.isEmpty()) {
            editTextPasswordDaftar.setError("Isi password terlebih dahulu!");
            editTextPasswordDaftar.requestFocus();
        } else if (confirmpassworddaftar.isEmpty()) {
            editTextConfirmPasswordDaftar.setError("Konfirmasikan password terlebih dahulu!");
            editTextConfirmPasswordDaftar.requestFocus();
        } else if (!confirmpassworddaftar.equals(passworddaftar)) {
            editTextConfirmPasswordDaftar.setError("Konfirmasikan password dengan benar!");
            editTextConfirmPasswordDaftar.requestFocus();
        } else {
            if (!usernamedaftar.isEmpty() && !emaildaftar.isEmpty() && !phonenumberdaftar.isEmpty() &&
                    !passworddaftar.isEmpty() && !confirmpassworddaftar.isEmpty() &&
                    confirmpassworddaftar.equals(passworddaftar)) {

                ApiServices api = InitLibrary.getInstances();

                Call<ResponseRegister> call = api.request_register(usernamedaftar, emaildaftar, passworddaftar, phonenumberdaftar);

                call.enqueue(new Callback<ResponseRegister>() {
                    @Override
                    public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                        if (response.isSuccessful()) {
                            String result = response.body().getResult();
                            String message = response.body().getMsg();
                            Toast.makeText(DaftarActivity.this, result, Toast.LENGTH_SHORT).show();

                            if (result.equals("1")) {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(DaftarActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseRegister> call, Throwable t) {

                    }
                });

            }
        }
    }
}
