package com.example.uconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Location_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Button manual = findViewById(R.id.LocationManually);
        manual.setOnClickListener(v -> {
            Intent i = new Intent(Location_Activity.this,MapsActivity.class);
            startActivity(i);
        });

        TextView auto = findViewById(R.id.AllowAccess);
        auto.setOnClickListener(v -> {
            Intent j = new Intent(Location_Activity.this,CurrentLocation.class);
            startActivity(j);
        });

    }
}