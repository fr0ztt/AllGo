package com.example.allgo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminGamesAdapter extends RecyclerView.Adapter {

    List<Game> gamesList;
    Context context;

    public AdminGamesAdapter(List<Game> gamesList, Context context) {
        this.gamesList = gamesList;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item_layout,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass) holder;

        Game game=gamesList.get(position);
        viewHolderClass.team1.setText(game.getTeam1());
        viewHolderClass.team2.setText(game.getTeam2());
        viewHolderClass.date.setText(game.getDate());
        viewHolderClass.res1.setText(game.getResultTeam1());
        viewHolderClass.res2.setText(game.getResultTeam1());
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView team1, team2, date, res1, res2;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            team1=itemView.findViewById(R.id.team1);
            team2=itemView.findViewById(R.id.team2);
            date=itemView.findViewById(R.id.date);
            res1=itemView.findViewById(R.id.res1);
            res2=itemView.findViewById(R.id.res2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(context,EditGameActivity.class);
                    intent.putExtra("game", gamesList.get(getAdapterPosition()));
                    intent.putExtra("id",String.valueOf(getAdapterPosition()+1));
                    context.startActivity(intent);
                }
            });
        }
    }
}
