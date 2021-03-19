package com.app.javaloginapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.javaloginapi.api.BaseApiService;
import com.app.javaloginapi.api.koneksi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    EditText edt_username, edt_password;

    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;

    public final static String TAG_ID_REGISTER = "id_register";
    public final static String TAG_NAMA_USER = "nama_user";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_USERNAME = "username";
    public final static String TAG_PASSWORD = "password";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id_register, nama_user, email, username, password;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        edt_username = findViewById(R.id.edt_username_login);
        edt_password = findViewById(R.id.edt_password_login);

        mContext = this;
        mApiService = koneksi.getAPIService();

        cekSession();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrnm = edt_username.getText().toString().trim();
                String pwd = edt_password.getText().toString().trim();

                if (TextUtils.isEmpty(usrnm)) {
                    edt_username.setError("Tidak boleh kosong");
                } else if (TextUtils.isEmpty(pwd)) {
                    edt_password.setError("Tidak boleh kosong");
                } else {
                    loading = ProgressDialog.show(mContext, null,
                            "Harap Tunggu...", true, false);
                    requestLogin();
                }
            }
        });
    }

    public void cekSession() {
        // Cek session login jika TRUE maka langsung buka DashbardActivity
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id_register = sharedpreferences.getString(TAG_ID_REGISTER, null);
        nama_user = sharedpreferences.getString(TAG_NAMA_USER, null);
        email = sharedpreferences.getString(TAG_EMAIL, null);
        username = sharedpreferences.getString(TAG_USERNAME, null);
        password = sharedpreferences.getString(TAG_PASSWORD, null);

        if (session) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            intent.putExtra(TAG_ID_REGISTER, id_register);
            intent.putExtra(TAG_NAMA_USER, nama_user);
            intent.putExtra(TAG_EMAIL, email);
            intent.putExtra(TAG_USERNAME, username);
            intent.putExtra(TAG_PASSWORD, password);
            startActivity(intent);
            finish();
        }
    }

    private void requestLogin(){
        mApiService.submitLogin(edt_username.getText().toString(), edt_password.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();

                            //::::::::::::::::::::::::
                            //Dengan Metode JSON ARRAY
                            //::::::::::::::::::::::::
                            try {
                                //identifikasi JsonObject
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                //identifikasi JsonArray
                                JSONArray jsonArray = jsonRESULTS.getJSONArray("logindata");

                                if (jsonRESULTS.getString("success").equals("1")){
                                    Toast.makeText(mContext, "Login berhasil!",
                                            Toast.LENGTH_SHORT).show();

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        //identifikasi JsonObject didalam JsonArray
                                        JSONObject jObj = jsonArray.getJSONObject(i);

                                        String id_register = jObj.getString(TAG_ID_REGISTER);
                                        String nama_user = jObj.getString(TAG_NAMA_USER);
                                        String email = jObj.getString(TAG_EMAIL);
                                        String username = jObj.getString(TAG_USERNAME);
                                        String password = jObj.getString(TAG_PASSWORD);

                                        // menyimpan login ke session
                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putBoolean(session_status, true);
                                        editor.putString(TAG_ID_REGISTER, id_register);
                                        editor.putString(TAG_NAMA_USER, nama_user);
                                        editor.putString(TAG_EMAIL, email);
                                        editor.putString(TAG_USERNAME, username);
                                        editor.putString(TAG_PASSWORD, password);
                                        editor.apply();

                                        // Memanggil Dashboards
                                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                        intent.putExtra(TAG_ID_REGISTER, id_register);
                                        intent.putExtra(TAG_NAMA_USER, nama_user);
                                        intent.putExtra(TAG_EMAIL, email);
                                        intent.putExtra(TAG_USERNAME, username);
                                        intent.putExtra(TAG_PASSWORD, password);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    Toast.makeText(mContext, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                                Toast.makeText(mContext, "Bermasalah!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }
}