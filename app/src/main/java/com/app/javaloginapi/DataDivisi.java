package com.app.javaloginapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataDivisi extends AppCompatActivity {

    Button btnBack, btnUpdate, btnDelete;

    TextView id_divisi, nama_divisi, newline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_divisi);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        id_divisi = (TextView) findViewById(R.id.id_divisi);
        nama_divisi = (TextView) findViewById(R.id.nama_divisi);
        newline = (TextView) findViewById(R.id.newline);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataDivisi.this, HomePage.class);
                DataDivisi.this.startActivity(intent);
                finish();
            }

        });

        StringBuilder ans = new StringBuilder();
        StringBuilder anss = new StringBuilder();
        StringBuilder ansss = new StringBuilder();
        StringBuilder anssss = new StringBuilder();
        StringBuilder ansssss = new StringBuilder();

        for(int i = 0; i<10; i++){
            ans.append("1");
            anss.append("Divisi 1");
            ansss.append(i);
            anssss.append(i);
            ansssss.append("\n");
        }

        id_divisi.setText(ans);
        nama_divisi.setText(anss);
        btnUpdate.setText(ansss);
        btnDelete.setText(anssss);
        newline.setText(ansssss);


    }
}