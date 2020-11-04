package com.example.hanivaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        CardView lyKeluar = findViewById(R.id.lyKeluar);
        lyKeluar.setOnClickListener(this);
        CardView lyPengajuan = findViewById(R.id.lyPengajuan);
        lyPengajuan.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        authListener =new  FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null){
                    Toast.makeText(DashboardActivity.this, "Logout Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authListener != null){
            auth.removeAuthStateListener(authListener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lyKeluar:
                auth.signOut();
                break;
            case R.id.lyPengajuan:
                startActivity(new Intent(DashboardActivity.this, DaftarPengajuanActivity.class));
                break;
        }
    }
}
