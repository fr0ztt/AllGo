package com.example.allgo;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOTeam {
    private final DatabaseReference databaseReference;

    public DAOTeam() {
        FirebaseDatabase db=FirebaseDatabase.getInstance();

        databaseReference= db.getReference("teams");
    }
    public Task<Void> add (Team team){
        return databaseReference.child(team.name).setValue(team);
    }
    public Task<Void> update(String key, HashMap<String,Object> hashMap){

        return databaseReference.child(key).updateChildren(hashMap);
    }
}
