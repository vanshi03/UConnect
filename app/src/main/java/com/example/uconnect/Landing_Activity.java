package com.example.uconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Landing_Activity extends AppCompatActivity {
    TextView location_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        location_tv=findViewById(R.id.location_tv);
        location_tv.setText(getIntent().getStringExtra("location"));
    }
}