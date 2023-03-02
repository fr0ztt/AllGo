package com.example.allgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class EditCompetitionActivity extends AppCompatActivity {

    EditText edtCompetition, edtNumberTeams, edtPrizepool;
    Button btnEdit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_competition);
        edtCompetition=findViewById(R.id.edt_competition_name);
        edtNumberTeams=findViewById(R.id.edt_date);
        edtPrizepool=findViewById(R.id.edt_team1);
        btnEdit=findViewById(R.id.btn_edit);
        DAOCompetition daoCompetition= new DAOCompetition();

        Intent intent=getIntent();
        Competition competition= (Competition) intent.getSerializableExtra("competition");
        edtCompetition.setText(competition.getName());
        edtNumberTeams.setText(competition.getNumberTeams().toString());
        edtPrizepool.setText(competition.getPrize().toString());
        String atualName=edtCompetition.getText().toString();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String,Object> hashMap =new HashMap<>();
                hashMap.put("name",edtCompetition.getText().toString());
                hashMap.put("numberTeams",Long.valueOf(edtNumberTeams.getText().toString()));
                hashMap.put("prize",Long.valueOf(edtPrizepool.getText().toString()));
                daoCompetition.update(atualName,hashMap).addOnSuccessListener(suc->{
                    Log.d("test","Updated");
                }).addOnFailureListener(er->{
                    Log.d("test","Not Updated");
                });
            }
        });
    }
}