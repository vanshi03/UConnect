package com.example.uconnect;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerDatabase {
    private DatabaseReference databaseReference;
    public CustomerDatabase(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Customer.class.getSimpleName());
    }
    public Task<Void> add(Customer cus){
        return databaseReference.push().setValue(cus);
    }
}
