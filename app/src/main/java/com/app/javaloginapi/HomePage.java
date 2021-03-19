package com.app.javaloginapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    Button btnProfile, btnDataDivisi, btnDataAnggota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnDataDivisi = (Button) findViewById(R.id.btnDataDivisi);
        btnDataAnggota = (Button) findViewById(R.id.btnDataAnggota);


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, ProfilePage.class);
                HomePage.this.startActivity(intent);
                finish();
            }

        });

        btnDataDivisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, DataDivisi.class);
                HomePage.this.startActivity(intent);
                finish();
            }

        });

        btnDataAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, DataAnggota.class);
                HomePage.this.startActivity(intent);
                finish();
            }

        });
    }


}