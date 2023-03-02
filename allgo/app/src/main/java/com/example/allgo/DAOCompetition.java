package com.example.allgo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOCompetition {
    private final DatabaseReference databaseReference;

    public DAOCompetition() {
        FirebaseDatabase db=FirebaseDatabase.getInstance();

        databaseReference= db.getReference("competitions");
    }
    public Task<Void> add (Competition competition){
        return databaseReference.child(competition.getName()).setValue(competition);
    }

    public Task<Void> update(String key, HashMap<String,Object> hashMap){

        return databaseReference.child(key).updateChildren(hashMap);
    }
}
