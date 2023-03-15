package com.example.uconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;
public class Get_Help_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText title,details;
    TextView textView;
    Button add,button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_help);
        title=findViewById(R.id.editText);
        details=findViewById(R.id.editText2);
        textView = findViewById(R.id.dateTextView);
        add = findViewById(R.id.addEvent);
        button = findViewById(R.id.SetDate);
        add.setOnClickListener(v -> {
            if( title.getText().toString().trim().equals("")) {
                title.setError( "Title is required!" );
                title.setHint("please enter title of event");
            } else if(details.getText().toString().trim().equals("")){
                details.setError( "Details are required!" );
                details.setHint("please add a brief description of the event");
            }else if(textView.getText().toString().trim().equals("")){
                textView.setError( "Date is required" );
                textView.setHint("please add event date by clicking on set date button");
            }
            else{
                Intent i = new Intent(getApplicationContext(),Landing_Activity.class);
                i.putExtra( "title", title.getText().toString());
                i.putExtra( "details", details.getText().toString());
                i.putExtra( "date", textView.getText().toString());
                setResult(RESULT_OK, i);
                //startActivity(i);
                finish();
            }

        });
        button.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        textView.setText(currentDateString);
    }
}