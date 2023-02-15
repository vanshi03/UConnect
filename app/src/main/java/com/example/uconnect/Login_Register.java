package com.example.uconnect;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
public class Login_Register extends AppCompatActivity {

    EditText enternumber;
    Button getotpbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
//                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        enternumber = findViewById(R.id.input_mobile_number);
        getotpbutton = findViewById(R.id.buttongetotp);

        getotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enternumber.getText().toString().trim().isEmpty()){
                    if ((enternumber.getText().toString().trim()).length()==10){
                        Intent intent = new Intent(getApplicationContext(), OtpVerification.class);
                        intent.putExtra( "mobile", enternumber.getText().toString());
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(Login_Register.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                    }
                else
                {
                    Toast.makeText(Login_Register.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }
                }
            }
        );
    }
}


