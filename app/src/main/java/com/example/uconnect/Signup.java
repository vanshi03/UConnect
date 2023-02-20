package com.example.uconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Signup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText fname=findViewById(R.id.fname);
        final EditText lname=findViewById(R.id.lname);
        final EditText mobile=findViewById(R.id.mobile);
        final EditText address=findViewById(R.id.address);
        Button btn=findViewById(R.id.register);
            ProgressBar progressBar = findViewById(R.id.progressbar_sending_otp);
            btn.setOnClickListener(v1 -> {
                        if (!mobile.getText().toString().trim().isEmpty()){
                            if ((mobile.getText().toString().trim()).length()==10){
                                progressBar.setVisibility(View.VISIBLE);
                                btn.setVisibility(View.INVISIBLE);
                                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + mobile.getText().toString(),
                                        60, TimeUnit.SECONDS, Signup.this,
                                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                            @Override
                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                                progressBar.setVisibility(View.GONE);
                                                btn.setVisibility(View.VISIBLE);
                                            }

                                            @Override
                                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                                progressBar.setVisibility(View.GONE);
                                               btn.setVisibility(View.VISIBLE);
                                                Toast.makeText(Signup.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                progressBar.setVisibility(View.GONE);
                                                btn.setVisibility(View.VISIBLE);
                                                Intent intent = new Intent(getApplicationContext(), OtpVerification.class);
                                                intent.putExtra( "mobile", mobile.getText().toString());
                                                intent.putExtra( "fname", fname.getText().toString());
                                                intent.putExtra( "lname", lname.getText().toString());
                                                intent.putExtra( "address", address.getText().toString());
                                                intent.putExtra("backendotp",backendotp);
                                                startActivity(intent);
                                                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                                            }
                                        }
                                );
                            }
                            else {
                                Toast.makeText(Signup.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Signup.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }
    }