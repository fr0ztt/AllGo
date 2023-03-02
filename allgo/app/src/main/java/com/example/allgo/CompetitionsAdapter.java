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

public class CompetitionsAdapter extends RecyclerView.Adapter {

    List<Competition> competitionList;
    Context context;


    public CompetitionsAdapter(List<Competition> competitionList,Context context) {
        this.competitionList = competitionList;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.competition_item_layout,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass) holder;

        Competition competition=competitionList.get(position);
        viewHolderClass.name.setText(competition.getName());
        viewHolderClass.teams_number.setText(competition.getNumberTeams().toString() + " equipas");

        /*StorageReference storageReference= FirebaseStorage.getInstance().getReference("images/"+ competition.getImage());
        try {
            File localFile= File.createTempFile("tempfile",".jpg");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            viewHolderClass.ivCompetition.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("image","Failed to retrieve");
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public int getItemCount() {
        return competitionList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView name,teams_number;
        ImageView ivCompetition;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.txt_name);
            teams_number=itemView.findViewById(R.id.txt_teams_number);
//            ivCompetition=itemView.findViewById(R.id.ivCompetition);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(context,CompetitionDetailActivity.class);
                    intent.putExtra("competition", competitionList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
