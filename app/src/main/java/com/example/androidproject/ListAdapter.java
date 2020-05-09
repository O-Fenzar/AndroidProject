package com.example.androidproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Player> values;

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHeader;
        TextView txtFooter;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, Player item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public ListAdapter(List<Player> myDataset) {
        values = myDataset;
    }


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Player currentDataPlayer = values.get(position);
        holder.txtHeader.setText(String.valueOf(currentDataPlayer.getId()));
        holder.txtHeader.setText(currentDataPlayer.getFirst_name());
        holder.txtHeader.setText(currentDataPlayer.getLast_name());
        holder.txtHeader.setText(currentDataPlayer.getPosition());

        //holder.txtFooter.setText(String.valueOf(currentDataPlayer.getHeight_feet()));
        //holder.txtFooter.setText(currentDataPlayer.getHeight_inches());
        //holder.txtFooter.setText(currentDataPlayer.getWeight_pounds());
    }


    @Override
    public int getItemCount() {
        return values.size();
    }
}


