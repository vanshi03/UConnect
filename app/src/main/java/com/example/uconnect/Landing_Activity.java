package com.example.uconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Landing_Activity extends AppCompatActivity {
    TextView location_tv;
    ImageButton img_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        location_tv=findViewById(R.id.location_tv);
        location_tv.setText(getIntent().getStringExtra("location"));
        img_btn=findViewById(R.id.btn_add);
        img_btn.setOnClickListener(v -> {
            Intent i = new Intent(Landing_Activity.this,Get_Help_Activity.class);
            startActivity(i);
        });

    }
}