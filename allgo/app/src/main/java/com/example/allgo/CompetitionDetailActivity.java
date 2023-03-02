package com.example.allgo;

import static com.google.firebase.storage.FirebaseStorage.getInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class CompetitionDetailActivity extends AppCompatActivity {
    TextView txt_competition_name,txt_number_teams,txt_prize,txt_equipas;
    ImageView iv_competitionImage;

    StorageReference storageReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_detail);
        txt_competition_name=findViewById(R.id.txt_competition_name);
        txt_number_teams=findViewById(R.id.txt_number_teams);
        txt_prize=findViewById(R.id.txt_prize);
        iv_competitionImage=findViewById(R.id.iv_competionImage);
        txt_equipas=findViewById(R.id.txt_teams_names);

        Intent intent=getIntent();
        Competition competition= (Competition) intent.getSerializableExtra("competition");
        txt_competition_name.setText(competition.getName());
        txt_number_teams.setText("Número de equipas:\n" + competition.getNumberTeams().toString() + " Equipas");
        txt_prize.setText("Prizepool:\n" + competition.getPrize().toString() + "€");
        txt_equipas.setText(competition.getEquipas());

        storageReference= getInstance().getReference("images/"+ competition.getImage());

        try {
            File localFile= File.createTempFile("tempfile",".jpg");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            iv_competitionImage.setImageBitmap(bitmap);
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