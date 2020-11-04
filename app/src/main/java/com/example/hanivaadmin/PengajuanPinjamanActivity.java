package com.example.hanivaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.hanivaadmin.Adapter.PinjamanRecyclerViewAdapter;
import com.example.hanivaadmin.Model.data_lengkap_user;
import com.example.hanivaadmin.Model.pinjaman_user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.snapshot.Index;

import java.util.ArrayList;
import java.util.Collection;

public class PengajuanPinjamanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseReference reference, mData_nama;
    private ArrayList<pinjaman_user> dataPinjaman;
    private ArrayList<data_lengkap_user> dataUser;
    private String nama;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_pinjaman);

        recyclerView = findViewById(R.id.datalist);
        auth = FirebaseAuth.getInstance();
        MyRecyclerView();
        GetData();
    }

    private void GetData() {
        Toast.makeText(getApplicationContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_SHORT).show();

        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("DataPinjaman")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataPinjaman = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            pinjaman_user pinjaman = snapshot.getValue(pinjaman_user.class);

                            pinjaman.setKey(snapshot.getKey());
                            dataPinjaman.add(pinjaman);
                        }

                        adapter = new PinjamanRecyclerViewAdapter(dataPinjaman, PengajuanPinjamanActivity.this);
                        recyclerView.setAdapter(adapter);

                        Toast.makeText(getApplicationContext(),"Data dimuat", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(),"Data Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void MyRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        //layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(layoutManager);

    }

}
