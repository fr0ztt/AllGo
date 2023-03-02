package com.example.allgo;

import static com.google.firebase.storage.FirebaseStorage.getInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class GameDetailActivity extends AppCompatActivity {
    TextView txt_competicao,txt_data,txt_team1,txt_team2,txt_result_team1,txt_result_team2;
    TextView txt_team1_player1,txt_team1_player2,txt_team1_player3,txt_team1_player4,txt_team1_player5,
            txt_team2_player1,txt_team2_player2,txt_team2_player3,txt_team2_player4,txt_team2_player5;
    ImageView iv_team1,iv_team2;
    StorageReference storageReference;

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,ListActivity.class);
        intent.putExtra("fragment","games");
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        txt_competicao=findViewById(R.id.txt_competicao);
        txt_data=findViewById(R.id.txt_data);
        txt_team1=findViewById(R.id.txt_team1);
        txt_team2=findViewById(R.id.txt_team2);
        txt_result_team1=findViewById(R.id.txt_resultTeam1);
        txt_result_team2=findViewById(R.id.txt_resultTeam2);
        iv_team1=findViewById(R.id.iv_team1);
        iv_team2=findViewById(R.id.iv_team2);

        txt_team1_player1=findViewById(R.id.txt_team1_player1);
        txt_team1_player2=findViewById(R.id.txt_team1_player2);
        txt_team1_player3=findViewById(R.id.txt_team1_player3);
        txt_team1_player4=findViewById(R.id.txt_team1_player4);
        txt_team1_player5=findViewById(R.id.txt_team1_player5);
        txt_team2_player1=findViewById(R.id.txt_team2_player1);
        txt_team2_player2=findViewById(R.id.txt_team2_player2);
        txt_team2_player3=findViewById(R.id.txt_team2_player3);
        txt_team2_player4=findViewById(R.id.txt_team2_player4);
        txt_team2_player5=findViewById(R.id.txt_team2_player5);





        Intent intent=getIntent();
        Game game= (Game) intent.getSerializableExtra("game");


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("teams");
        databaseReference.orderByChild("name").equalTo(game.team1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){

                    txt_team1_player1.setText(ds.child("player1").getValue().toString());
                    txt_team1_player2.setText(ds.child("player2").getValue().toString());
                    txt_team1_player3.setText(ds.child("player3").getValue().toString());
                    txt_team1_player4.setText(ds.child("player4").getValue().toString());
                    txt_team1_player5.setText(ds.child("player5").getValue().toString());

                    String imageTeam1=ds.child("image").getValue().toString();

                    storageReference= getInstance().getReference("images/"+ imageTeam1);

                    try {
                        File localFile= File.createTempFile("tempfile",".jpg");
                        storageReference.getFile(localFile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                        iv_team1.setImageBitmap(bitmap);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("image","Failed to retrieve");
                                    }
                                });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        databaseReference.orderByChild("name").equalTo(game.team2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){

                    txt_team2_player1.setText(ds.child("player1").getValue().toString());
                    txt_team2_player2.setText(ds.child("player2").getValue().toString());
                    txt_team2_player3.setText(ds.child("player3").getValue().toString());
                    txt_team2_player4.setText(ds.child("player4").getValue().toString());
                    txt_team2_player5.setText(ds.child("player5").getValue().toString());

                    String imageTeam2=ds.child("image").getValue().toString();

                    storageReference= getInstance().getReference("images/"+ imageTeam2);

                    try {
                        File localFile= File.createTempFile("tempfile",".jpg");
                        storageReference.getFile(localFile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                        iv_team2.setImageBitmap(bitmap);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("image","Failed to retrieve");
                                    }
                                });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        txt_competicao.setText(game.getCompetition());
        txt_data.setText(game.getDate());
        txt_team1.setText(game.getTeam1());
        txt_team2.setText(game.getTeam2());
        txt_result_team1.setText(game.getResultTeam1());
        txt_result_team2.setText(game.getResultTeam2());

        if(game.getDate().equals("LIVE")) {
            txt_data.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            txt_result_team1.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            txt_result_team2.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        } else {
            txt_data.setTextColor(getResources().getColor(android.R.color.white));
            txt_result_team1.setTextColor(getResources().getColor(android.R.color.white));
            txt_result_team2.setTextColor(getResources().getColor(android.R.color.white));
        }



    }
}