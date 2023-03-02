package com.example.allgo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

public class AddCompetitionActivity extends AppCompatActivity {

    EditText edtCompetition, edtNumberTeams, edtPrizepool;

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_competition);

        edtCompetition=findViewById(R.id.edt_competition_name);
        edtNumberTeams=findViewById(R.id.edt_date);
        edtPrizepool=findViewById(R.id.edt_team1);

        btnAdd=findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Competition competition=new Competition();
                competition.setName(edtCompetition.getText().toString());
                competition.setNumberTeams(Long.valueOf(edtNumberTeams.getText().toString()));
                competition.setPrize(Long.valueOf(edtPrizepool.getText().toString()));
                DAOCompetition daoCompetition= new DAOCompetition();
                daoCompetition.add(competition);
            }
        });
    }
}