package com.example.uconnect;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.io.IOException;
import java.util.List;

public class Landing_Activity extends AppCompatActivity {
    TextView location_tv,eventInfo,Clicktoadd;
    ImageButton img_btn;
    String latitude, longitude, address,caddress;
    Double currentlatitude,currentlongitude;
    private DatabaseReference databaseReference;
    private static final double RADIUS_OF_EARTH_KM = 6371.01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        location_tv = findViewById(R.id.location_tv);
        address = getIntent().getStringExtra("location");
        //caddress = getIntent().getStringExtra("clocation");
        location_tv.setText(address);
        if (address != null) {
            Geocoder geocoder = new Geocoder(Landing_Activity.this);
            List<Address> addressList = null;
            try {
                addressList = geocoder.getFromLocationName(address, 1);
                Address address = addressList.get(0);
                latitude = String.valueOf(address.getLatitude());
                longitude = String.valueOf(address.getLongitude());
                try {
                    currentlatitude = Double.parseDouble(latitude);
                    currentlongitude=Double.parseDouble(longitude);
                } catch (NumberFormatException e) {
                    // latitude or longitude did not contain a valid double
                }

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("Customer");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            Map<String, Object> userMap = (HashMap<String, Object>) userSnapshot.getValue();
                            double userLatitude = Double.parseDouble(userMap.get("latitude").toString());
                            double userLongitude = Double.parseDouble(userMap.get("longitude").toString());
                            double distance = calculateDistance(currentlatitude, currentlongitude, userLatitude, userLongitude);
                            if (distance <= 10) {
                                String userId = userSnapshot.getKey();
                                String userName = userMap.get("fname").toString();
                                Log.d("User", "User ID: " + userId);
                                Log.d("User", "Name: " + userName);
                                Log.d("User", "Latitude: " + userLatitude);
                                Log.d("User", "Longitude: " + userLongitude);
                                Log.d("User", "Distance from current location: " + distance + " km");
                            }
                        }
                    }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle database error here
                }
                    private double calculateDistance(double currentLatitude, double currentLongitude, double userLatitude, double userLongitude) {
                        double latDistance = Math.toRadians(currentLatitude - userLatitude);
                        double lonDistance = Math.toRadians(currentLongitude - userLongitude);
                        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                                + Math.cos(Math.toRadians(currentLatitude)) * Math.cos(Math.toRadians(userLatitude))
                                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                        double distance = RADIUS_OF_EARTH_KM * c;
                        return distance;
                    }
            });
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
        Clicktoadd = findViewById(R.id.Clicktoadd);
        img_btn = findViewById(R.id.btn_add);
        eventInfo= findViewById(R.id.EventInfo);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK){
                Clicktoadd.setVisibility(View.INVISIBLE);
                img_btn.setVisibility(View.INVISIBLE);
                String title  = data.getStringExtra("title");
                String details  = data.getStringExtra("details");
                String date  = data.getStringExtra("date");
                eventInfo.setText(title +'\n' + details + "\n" + date);
            }
            if(resultCode==RESULT_CANCELED){
                eventInfo.setText("nothing to display");
            }
        }
    }
}