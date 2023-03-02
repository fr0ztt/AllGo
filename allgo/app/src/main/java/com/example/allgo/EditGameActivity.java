package com.example.allgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class EditGameActivity extends AppCompatActivity {

    EditText edtCompetitionName,edtDate,edtTeam1,edtTeam2,edtResultadoTeam1,edtResultadoTeam2;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);
        edtCompetitionName=findViewById(R.id.edt_competition_name);
        edtDate=findViewById(R.id.edt_date);
        edtTeam1=findViewById(R.id.edt_team1);
        edtTeam2=findViewById(R.id.edt_team2);
        edtResultadoTeam1=findViewById(R.id.edt_resultteam1);
        edtResultadoTeam2=findViewById(R.id.edt_resultteam2);
        btnEdit=findViewById(R.id.btn_edit);
        DAOGame daoGame= new DAOGame();

        Intent intent=getIntent();
        Game game= (Game) intent.getSerializableExtra("game");
        String id= intent.getStringExtra("id");
        edtCompetitionName.setText(game.getCompetition());
        edtDate.setText(game.getDate());
        edtTeam1.setText(game.getTeam1());
        edtTeam2.setText(game.getTeam2());
        edtResultadoTeam1.setText(game.getResultTeam1());
        edtResultadoTeam2.setText(game.getResultTeam2());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> hashMap =new HashMap<>();
                hashMap.put("competition",edtCompetitionName.getText().toString());
                hashMap.put("date",edtDate.getText().toString());
                hashMap.put("team1",edtTeam1.getText().toString());
                hashMap.put("team2",edtTeam2.getText().toString());
                hashMap.put("resultTeam1",edtResultadoTeam1.getText().toString());
                hashMap.put("resultTeam2",edtResultadoTeam2.getText().toString());
                daoGame.update(id,hashMap).addOnSuccessListener(suc->{
                    Log.d("test","Updated");
                }).addOnFailureListener(er->{
                    Log.d("test","Not Updated");
                });
            }
        });
    }
}