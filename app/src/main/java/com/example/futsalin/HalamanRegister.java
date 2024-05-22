package com.example.futsalin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HalamanRegister extends AppCompatActivity {
    EditText editTextEmail, editTextPassword, editTextName, editTextNoHP;
    Button button;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        editTextName = findViewById(R.id.namaregis);
        editTextEmail = findViewById(R.id.emailregis);
        editTextNoHP = findViewById(R.id.nohpregis);
        editTextPassword = findViewById(R.id.passwordregis);
        button = findViewById(R.id.buttonregis);
        progressBar = findViewById(R.id.probarregis);
        textView = findViewById(R.id.buttonRegis);
        progressBar.setVisibility(View.GONE);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), HalamanLogin.class);
               startActivity(i);
               finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String nama, email, nohp, password;
                nama = String.valueOf(editTextName.getText());
                email = String.valueOf(editTextEmail.getText());
                nohp = String.valueOf(editTextNoHP.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(nama)){
                    Toast.makeText(HalamanRegister.this, "Masukkan nama", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(HalamanRegister.this, "Masukkan email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(nohp)){
                    Toast.makeText(HalamanRegister.this, "Masukkan no. handphone", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(HalamanRegister.this, "Masukkan password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(HalamanRegister.this, "Akun berhasil dibuat, silahkan masuk.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), HalamanBerhasilRegis.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(HalamanRegister.this, "Autentikasi gagal.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}