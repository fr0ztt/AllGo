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

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolderClass> {
    List<Game> gamesList;
    Context context;

    public GamesAdapter(List<Game> gamesList, Context context) {
        this.gamesList = gamesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item_layout,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        Game game = gamesList.get(position);
        holder.txtTeam1.setText(game.getTeam1());
        holder.txtTeam2.setText(game.getTeam2());
        holder.txtData.setText(game.getDate());
        holder.txtRes1.setText(game.getResultTeam1());
        holder.txtRes2.setText(game.getResultTeam2());

        if(game.getDate().equals("LIVE")) {
            holder.txtData.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
            holder.txtRes1.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
            holder.txtRes2.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
        } else {
            holder.txtData.setTextColor(context.getResources().getColor(android.R.color.white));
            holder.txtRes1.setTextColor(context.getResources().getColor(android.R.color.white));
            holder.txtRes2.setTextColor(context.getResources().getColor(android.R.color.white));
        }

    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView txtTeam1,txtTeam2,txtData,txtRes1,txtRes2;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            txtTeam1=itemView.findViewById(R.id.team1);
            txtTeam2=itemView.findViewById(R.id.team2);
            txtData=itemView.findViewById(R.id.date);
            txtRes1=itemView.findViewById(R.id.res1);
            txtRes2=itemView.findViewById(R.id.res2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GameDetailActivity.class);
                    intent.putExtra("game", gamesList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

    // Atualize o item específico na lista em vez de recarregar a lista inteira
    public void updateItem(int position, Game game) {
        gamesList.set(position, game);
        notifyItemChanged(position);
    }
}
