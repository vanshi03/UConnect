package com.example.uconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class Landing_Activity extends AppCompatActivity {
    TextView location_tv;
    ImageButton img_btn;
    String latitude, longitude, address;
    TextView eventInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        location_tv = findViewById(R.id.location_tv);
        address = getIntent().getStringExtra("location");
        location_tv.setText(address);
        if (address != null) {
            Geocoder geocoder = new Geocoder(Landing_Activity.this);
            List<Address> addressList = null;
            try {
                addressList = geocoder.getFromLocationName(address, 1);
                Address address = addressList.get(0);
                latitude = String.valueOf(address.getLatitude());
                longitude = String.valueOf(address.getLongitude());
            } catch (IOException e) {
                e.printStackTrace();
            }
            img_btn = findViewById(R.id.btn_add);
            img_btn.setOnClickListener(v -> {
                Intent i = new Intent(Landing_Activity.this, Get_Help_Activity.class);
                //noinspection deprecation
                startActivityForResult(i,1);
            });
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        eventInfo= findViewById(R.id.EventInfo);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK){
                String title  = data.getStringExtra("title");
                String details  = data.getStringExtra("details");
                String date  = data.getStringExtra("date");
                eventInfo.setText(title + details + date);
            }
            if(resultCode==RESULT_CANCELED){
                eventInfo.setText("nothing to display");
            }

        }
    }
}