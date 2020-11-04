package com.example.hanivaadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DaftarPengajuanActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView cvPinjaman, cvTabungan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarpengajuan);

        cvPinjaman = findViewById(R.id.lyDaftarPinjaman);
        cvPinjaman.setOnClickListener(this);
        cvTabungan = findViewById(R.id.lyDaftarTabungan);
        cvTabungan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lyDaftarPinjaman:
                startActivity(new Intent(DaftarPengajuanActivity.this, PengajuanPinjamanActivity.class));
                break;
            case R.id.lyDaftarTabungan:
                startActivity(new Intent(DaftarPengajuanActivity.this, PengajuanTabunganActivity.class));
                break;
        }
    }
}
