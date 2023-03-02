package com.example.allgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddGameActivity extends AppCompatActivity {

    EditText edtDate;
    Button btnAdd;
    Spinner spinnerCompetition,spinnerTeam1,spinnerTeam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        edtDate=findViewById(R.id.edt_date);
        spinnerCompetition=findViewById(R.id.spinner_competition);
        spinnerTeam1=findViewById(R.id.spinner_team1);
        spinnerTeam2=findViewById(R.id.spinner_team2);

        btnAdd=findViewById(R.id.btn_edit);
        Intent intent=getIntent();
        int numberGames=intent.getIntExtra("numbergames",0);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("competitions");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                final List<String> nomeCompeticoes = new ArrayList<String>();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Competition competition = ds.getValue(Competition.class);
                    String competitionName=competition.getName();
                    nomeCompeticoes.add(competitionName);
                }

                Log.d("array",nomeCompeticoes.get(0));

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddGameActivity.this, android.R.layout.simple_spinner_item, nomeCompeticoes);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCompetition.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef=database.getReference("teams");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String> nomesEquipas = new ArrayList<String>();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Team team= ds.getValue(Team.class);
                    String teamName=team.getName();
                    nomesEquipas.add(teamName);
                }

                Log.d("array",nomesEquipas.get(0));

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddGameActivity.this, android.R.layout.simple_spinner_item, nomesEquipas);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTeam1.setAdapter(arrayAdapter);
                spinnerTeam2.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game game=new Game();
                game.setCompetition(spinnerCompetition.getSelectedItem().toString());
                game.setDate(edtDate.getText().toString());
                game.setTeam1(spinnerTeam1.getSelectedItem().toString());
                game.setTeam2(spinnerTeam2.getSelectedItem().toString());
                game.setResultTeam1("0");
                game.setResultTeam2("0");
                DAOGame daoGame= new DAOGame();
                daoGame.add(game,numberGames);
            }
        });
    }
}