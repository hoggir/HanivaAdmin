package com.example.hanivaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailPinjamanActivity extends AppCompatActivity {

    private TextView tvNama, tvAlamat, tvNohp, tvDPNama;
    private TextView tvJumlah, tvWaktu, tvJaminan, tvStatus, tvIduser;
    private DatabaseReference mData_nama, mDataAlamat, mDataNohp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pinjaman);

        tvNama = findViewById(R.id.tvDNama);
        tvAlamat = findViewById(R.id.tvDAlamat);
        tvNohp = findViewById(R.id.tvDNohp);

        tvJumlah = findViewById(R.id.tvDJumlah);
        tvWaktu = findViewById(R.id.tvDWaktu);
        tvJaminan = findViewById(R.id.tvDJaminan);
        tvStatus = findViewById(R.id.tvDStatus);
        tvIduser = findViewById(R.id.tvDIduser);
        tvDPNama = findViewById(R.id.tvDPNama);

        getData();
    }

    private void getData() {
        final String getIdUser = getIntent().getExtras().getString("dataIduser");

        final String getJumlah = getIntent().getExtras().getString("dataJumlah");
        final String getWaktu = getIntent().getExtras().getString("dataWaktu");
        final String getJaminan = getIntent().getExtras().getString("dataJaminan");
        final String getStatus = getIntent().getExtras().getString("dataStatus");


        mData_nama = FirebaseDatabase.getInstance().getReference().child("DataUser").child(getIdUser).child("nama");
        mData_nama.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvNama.setText(snapshot.getValue(String.class));
                tvDPNama.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDataAlamat = FirebaseDatabase.getInstance().getReference().child("DataUser").child(getIdUser).child("alamattgl");
        mDataAlamat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvAlamat.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDataNohp = FirebaseDatabase.getInstance().getReference().child("DataUser").child(getIdUser).child("nohp");
        mDataNohp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvNohp.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        tvJumlah.setText(getJumlah);
        tvWaktu.setText(getWaktu);
        tvJaminan.setText(getJaminan);
        tvStatus.setText(getStatus);
        tvIduser.setText(getIdUser);
    }
}
