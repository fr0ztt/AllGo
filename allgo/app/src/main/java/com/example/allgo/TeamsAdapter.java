package com.example.allgo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolderClass> {

    private List<Team> teamsList;
    private Context context;

    public TeamsAdapter(List<Team> teamsList, Context context) {
        this.teamsList = teamsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_item_layout, parent, false);
        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        Team team = teamsList.get(position);
        holder.name.setText(team.getName());
        /*StorageReference storageReference = FirebaseStorage.getInstance().getReference("images/" + team.getImage());
        storageReference.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(holder.imageView);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("image", "Failed to retrieve");
                    }
                });*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TeamDetailActivity.class);
                intent.putExtra("team", team);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

    public static class ViewHolderClass extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
//            imageView = itemView.findViewById(R.id.ivTeam);
        }
    }
}
