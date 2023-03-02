package com.example.allgo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminCompetitionsAdapter extends RecyclerView.Adapter {

    List<Competition> competitionList;
    Context context;


    public AdminCompetitionsAdapter(List<Competition> competitionList,Context context) {
        this.competitionList = competitionList;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.competition_item_layout,parent,false);
        ViewHolderClass viewHolderClass= new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass viewHolderClass = (ViewHolderClass) holder;

        Competition competition=competitionList.get(position);
        viewHolderClass.name.setText(competition.getName());
        viewHolderClass.numberTeams.setText(competition.getNumberTeams().toString() + " equipas");
    }

    @Override
    public int getItemCount() {
        return competitionList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView name,numberTeams;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.txt_name);
            numberTeams=itemView.findViewById(R.id.txt_teams_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(context,EditCompetitionActivity.class);
                    intent.putExtra("competition", competitionList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
