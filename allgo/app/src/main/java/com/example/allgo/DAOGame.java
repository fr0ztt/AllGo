package com.example.allgo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOGame {
    private final DatabaseReference databaseReference;

    public DAOGame() {
        FirebaseDatabase db=FirebaseDatabase.getInstance();

        databaseReference= db.getReference("games");
    }
    public Task<Void> add (Game game,int gameNumber){
        String newId=String.valueOf(gameNumber+1);
        return databaseReference.child(newId).setValue(game);
    }

    public Task<Void> update(String key, HashMap<String,Object> hashMap){

        return databaseReference.child(key).updateChildren(hashMap);
    }
}
