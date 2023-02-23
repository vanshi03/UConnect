package com.example.uconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class Login extends AppCompatActivity {
    EditText mobile_number;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile_number=findViewById(R.id.input_mobile_number);
        Button login_button=findViewById(R.id.MainLogin);
        TextView tv=findViewById(R.id.SignUpofLogin);
        tv.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Signup.class);
            startActivity(intent);
        });
        login_button.setOnClickListener(v -> {
            if (!mobile_number.getText().toString().trim().isEmpty()) {
                if ((mobile_number.getText().toString().trim()).length() == 10) {
                    String nameString = mobile_number.getText().toString();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    ref.child("Customer").orderByChild("phone").equalTo(nameString).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
//                        Toast.makeText(Login.this, "Loging.....", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, Location_Activity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                            } else {
                                Toast.makeText(Login.this, "Please signup first", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });

                } else {
                    Toast.makeText(this, "Enter 10 digit mobile number", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Mobile number can't be empty", Toast.LENGTH_SHORT).show();
            }


        });
    }
}