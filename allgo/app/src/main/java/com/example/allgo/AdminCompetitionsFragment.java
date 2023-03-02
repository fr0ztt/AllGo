package com.example.allgo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminCompetitionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminCompetitionsFragment extends Fragment {

    List<Competition> competitions;
    RecyclerView adminCompetitionsRecyclerView;
    AdminCompetitionsAdapter adapter;
    DatabaseReference databaseReference;
    FloatingActionButton fab;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminCompetitionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminCompetitionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminCompetitionsFragment newInstance(String param1, String param2) {
        AdminCompetitionsFragment fragment = new AdminCompetitionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_competitions, container, false);
        adminCompetitionsRecyclerView= view.findViewById(R.id.adminCompetitionsRecyclerView);
        adminCompetitionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        competitions=new ArrayList<>();
        fab=view.findViewById(R.id.competition_add);

        databaseReference= FirebaseDatabase.getInstance().getReference("competitions");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),AddCompetitionActivity.class);
                startActivity(intent);
            }
        });
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Competition competition=ds.getValue(Competition.class);
                    competitions.add(competition);
                }
                adapter=new AdminCompetitionsAdapter(competitions,getActivity());
                adminCompetitionsRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}