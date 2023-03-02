package com.example.allgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.allgo.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new AdminCompetitionsFragment());

        binding.adminNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.adminequipas:
                    replaceFragment(new AdminTeamsFragment());
                    break;
                case R.id.admincompeticoes:
                    replaceFragment(new AdminCompetitionsFragment());
                    break;
                case R.id.adminjogos:
                    replaceFragment(new AdminGamesFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.adminFrameLayout,fragment);
        fragmentTransaction.commit();
    }
}