package com.example.hanivaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btLogin;
    private EditText etMyemail, etMypass;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener listener;
    private String getEmail, getPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMyemail = findViewById(R.id.getEmail);
        etMypass = findViewById(R.id.getPassword);
        btLogin = findViewById(R.id.login);
        btLogin.setOnClickListener(this);

        etMypass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        auth = FirebaseAuth.getInstance();

        listener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null){
            auth.removeAuthStateListener(listener);
        }
    }

    private void loginUserAccount(){
        auth.signInWithEmailAndPassword(getEmail,getPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Berhasil",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this,"Login Gagal, Coba Lagi!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            //case R.id.register:
                //startActivity(new Intent(LoginActivity.this, DaftarActivity.class));
                //break;

            case R.id.login:
                getEmail = etMyemail.getText().toString();
                getPass = etMypass.getText().toString();

                if (TextUtils.isEmpty(getEmail) || TextUtils.isEmpty(getPass)){
                    Toast.makeText(this,"Ada yang kosong loh", Toast.LENGTH_SHORT).show();
                }else {
                    loginUserAccount();

                }
                break;
        }
    }
}
