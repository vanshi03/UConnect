package com.example.uconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        CustomerDatabase cdb = new CustomerDatabase();
        btn.setOnClickListener( v->{
                Customer cus=new Customer(fname.getText().toString(),lname.getText().toString(),mobile.getText().toString(),address.getText().toString());
                cdb.add(cus).addOnSuccessListener(suc->{
                    Toast.makeText(this, "SignUp Successfull", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        );
    }
}