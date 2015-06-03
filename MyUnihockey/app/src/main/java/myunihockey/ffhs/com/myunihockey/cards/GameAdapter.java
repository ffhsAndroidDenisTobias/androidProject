package myunihockey.ffhs.com.myunihockey.cards;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.R;
import myunihockey.ffhs.com.myunihockey.persistence.dto.Game;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<Game> gameList;
    private LayoutInflater mLayoutInflater;


    public GameAdapter(List<Game> gameList) {

        this.gameList = gameList;
    }

    @Override
    public int getItemCount() {
        return gameList.size();

    }


    @Override
    public void onBindViewHolder(GameViewHolder holder, final int i) {
        Game data = gameList.get(i);

        holder.datum.setText(data.getDate());
        holder.ort.setText("");
        holder.team1.setText(data.getHometeam_name());
        holder.team2.setText(data.getAwayteam_name());
        if (data.isPlayed()) {
            holder.gameresult.setText(data.getHometeam_name() + ":" + data.getGoalsaway());
        } else {
            holder.gameresult.setText("--:--");
        }
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.row_game_card, viewGroup, false);

        return new GameViewHolder(itemView);
    }


    public class GameViewHolder extends RecyclerView.ViewHolder {


        protected TextView team1;
        protected TextView team2;
        protected TextView datum;
        protected TextView ort;
        protected TextView gameresult;


        public GameViewHolder(View v) {
            super(v);
            team1 = (TextView) v.findViewById(R.id.team1);
            team2 = (TextView) v.findViewById(R.id.team2);
            datum = (TextView) v.findViewById(R.id.datum);
            ort = (TextView) v.findViewById(R.id.ort);
            gameresult = (TextView) v.findViewById(R.id.gameresult);
        }


    }
}