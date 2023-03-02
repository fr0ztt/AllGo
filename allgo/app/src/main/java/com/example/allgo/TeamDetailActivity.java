package com.example.allgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class TeamDetailActivity extends AppCompatActivity {
    TextView txt_teamname,txtplayer1,txtplayer2,txtplayer3, txtplayer4,txtplayer5;
    ImageView iv_teamimage;

    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        txt_teamname=findViewById(R.id.txt_teamname);
        txtplayer1=findViewById(R.id.txt_player1);
        txtplayer2=findViewById(R.id.txt_player2);
        txtplayer3=findViewById(R.id.txt_player3);
        txtplayer4=findViewById(R.id.txt_player4);
        txtplayer5=findViewById(R.id.txt_player5);
        iv_teamimage=findViewById(R.id.iv_teamimage);

        Intent intent=getIntent();
        Team team= (Team) intent.getSerializableExtra("team");
        txt_teamname.setText(team.getName());
        txtplayer1.setText(team.getPlayer1());
        txtplayer2.setText(team.getPlayer2());
        txtplayer3.setText(team.getPlayer3());
        txtplayer4.setText(team.getPlayer4());
        txtplayer5.setText(team.getPlayer5());

        storageReference= FirebaseStorage.getInstance().getReference("images/"+ team.getImage());

        try {
            File localFile= File.createTempFile("tempfile",".jpg");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            iv_teamimage.setImageBitmap(bitmap);
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