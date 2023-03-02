package com.example.allgo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTeamActivity extends AppCompatActivity {

    EditText edt_teamname,edtplayer1,edtplayer2,edtplayer3,edtplayer4,edtplayer5;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        edt_teamname=findViewById(R.id.edt_competition_name);
        edtplayer1=findViewById(R.id.edt_date);
        edtplayer2=findViewById(R.id.edt_team1);
        edtplayer3=findViewById(R.id.edt_team2);
        edtplayer4=findViewById(R.id.edt_player4);
        edtplayer5=findViewById(R.id.edt_player5);
        btnAdd=findViewById(R.id.btn_edit);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Team team= new Team();
                team.setName(edt_teamname.getText().toString());
                team.setPlayer1(edtplayer1.getText().toString());
                team.setPlayer2(edtplayer2.getText().toString());
                team.setPlayer3(edtplayer3.getText().toString());
                team.setPlayer4(edtplayer4.getText().toString());
                team.setPlayer5(edtplayer5.getText().toString());
                DAOTeam daoTeam= new DAOTeam();
                daoTeam.add(team);
            }
        });


    }
}