package com.example.allgo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AdminTeamsAdapter  extends RecyclerView.Adapter {

    List<Team> teamsList;
    Context context;

    public AdminTeamsAdapter(List<Team> teamsList, Context context) {
        this.teamsList = teamsList;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.team_item_layout,parent,false);
        ViewHolderClass holder= new ViewHolderClass(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass) holder;
        Team team=teamsList.get(position);
        viewHolderClass.name.setText(team.getName());

    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.txt_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(context,EditTeamActivity.class);
                    intent.putExtra("team", teamsList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
