package com.example.allgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class EditTeamActivity extends AppCompatActivity {

    EditText edt_teamname,edtplayer1,edtplayer2,edtplayer3,edtplayer4,edtplayer5;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        edt_teamname=findViewById(R.id.edt_competition_name);
        edtplayer1=findViewById(R.id.edt_date);
        edtplayer2=findViewById(R.id.edt_team1);
        edtplayer3=findViewById(R.id.edt_team2);
        edtplayer4=findViewById(R.id.edt_player4);
        edtplayer5=findViewById(R.id.edt_player5);
        btnEdit=findViewById(R.id.btn_edit);
        DAOTeam daoTeam= new DAOTeam();

        Intent intent=getIntent();
        Team team= (Team) intent.getSerializableExtra("team");
        edt_teamname.setText(team.getName());
        edtplayer1.setText(team.getPlayer1());
        edtplayer2.setText(team.getPlayer2());
        edtplayer3.setText(team.getPlayer3());
        edtplayer4.setText(team.getPlayer4());
        edtplayer5.setText(team.getPlayer5());
        String atualName=edt_teamname.getText().toString();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap <String,Object> hashMap =new HashMap<>();
                hashMap.put("name",edt_teamname.getText().toString());
                hashMap.put("player1",edtplayer1.getText().toString());
                hashMap.put("player2",edtplayer2.getText().toString());
                hashMap.put("player3",edtplayer3.getText().toString());
                hashMap.put("player4",edtplayer4.getText().toString());
                hashMap.put("player5",edtplayer5.getText().toString());
                daoTeam.update(atualName,hashMap).addOnSuccessListener(suc->{
                    Log.d("test","Updated");
                }).addOnFailureListener(er->{
                    Log.d("test","Not Updated");
                });
            }
        });



    }
}