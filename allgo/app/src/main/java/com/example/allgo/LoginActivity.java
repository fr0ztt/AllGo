package com.example.allgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-teste-64ed7-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText edt_username=findViewById(R.id.edt_username);
        final EditText edt_password=findViewById(R.id.edt_password);
        final Button btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username=edt_username.getText().toString();
                final String password=edt_password.getText().toString();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Please enter your username or password",Toast.LENGTH_LONG).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(username)) {
                                final String getPassword = snapshot.child(username).child("password").getValue(String.class);
                                if (getPassword.equals(password)){
                                    Toast.makeText(LoginActivity.this,"Successfully Logged In",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(LoginActivity.this,AdminActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(LoginActivity.this,"Wrong password",Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(LoginActivity.this,"Wrong password",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}