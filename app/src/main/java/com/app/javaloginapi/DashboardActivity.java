package com.app.javaloginapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    TextView tv_idregister, tv_nama, tv_email, tv_username, tv_password;
    Button btn_logout;

    SharedPreferences sharedpreferences;
    String id_register, nama_user, email, username, password;

    public final static String TAG_ID_REGISTER = "id_register";
    public final static String TAG_NAMA_USER = "nama_user";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_USERNAME = "username";
    public final static String TAG_PASSWORD = "password";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tv_idregister = findViewById(R.id.tv_idRegister_dash);
        tv_nama = findViewById(R.id.tv_nama_dash);
        tv_email = findViewById(R.id.tv_email_dash);
        tv_username = findViewById(R.id.tv_username_dash);
        tv_password = findViewById(R.id.tv_pass_dash);
        btn_logout = findViewById(R.id.btn_logout_dash);

        ambilSession();

        tv_idregister.setText("ID Register : "+id_register);
        tv_nama.setText("Nama : "+nama_user);
        tv_email.setText("Email : "+email);
        tv_username.setText("Username : "+username);
        tv_password.setText("Password : "+password);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endSession();
            }
        });
    }

    public void ambilSession() {
        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id_register = getIntent().getStringExtra(TAG_ID_REGISTER);
        nama_user = getIntent().getStringExtra(TAG_NAMA_USER);
        email = getIntent().getStringExtra(TAG_EMAIL);
        username = getIntent().getStringExtra(TAG_USERNAME);
        password = getIntent().getStringExtra(TAG_PASSWORD);
    }

    public void endSession() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(LoginActivity.session_status, false);
        editor.putString(TAG_ID_REGISTER, null);
        editor.putString(TAG_NAMA_USER, null);
        editor.putString(TAG_EMAIL, null);
        editor.putString(TAG_USERNAME, null);
        editor.putString(TAG_PASSWORD, null);
        editor.apply();

        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
//    }
}