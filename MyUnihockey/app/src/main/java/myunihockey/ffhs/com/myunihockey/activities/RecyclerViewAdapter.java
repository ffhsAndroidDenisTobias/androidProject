package myunihockey.ffhs.com.myunihockey.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import myunihockey.ffhs.com.myunihockey.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {
    private ArrayList<String> mListData = new ArrayList<>();
    private LayoutInflater mLayoutInflater;


    public RecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = mLayoutInflater.inflate(R.layout.row_game_card, parent, false);
        Holder holder = new Holder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        String data = mListData.get(position);
        holder.ort.setText(data);
        holder.team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });
    }

    public void addItem(String item) {
        mListData.add(item);
        notifyItemInserted(mListData.size());
    }

    public void removeItem(String item) {
        int position = mListData.indexOf(item);
        if (position != -1) {
            mListData.remove(item);
            notifyItemRemoved(position);
        }
    }

    public void removeItem(int position) {
        mListData.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView team1;
        TextView team2;
        TextView ort;

        public Holder(View itemView) {
            super(itemView);
            ort = (TextView) itemView.findViewById(R.id.ort);

            team1 = (TextView) itemView.findViewById(R.id.team1);
            team2 = (TextView) itemView.findViewById(R.id.team2);
        }
    }

}