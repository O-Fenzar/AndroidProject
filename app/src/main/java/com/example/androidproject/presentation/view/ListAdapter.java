package com.example.androidproject.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.androidproject.presentation.model.Player;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable {

    private List<Player> playerList;
    private List<Player> playerListFull;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick (Player item);
    }

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
        playerList.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        playerList.remove(position);
        notifyItemRemoved(position);
    }

    public ListAdapter(List<Player> myDataset, OnItemClickListener listener) {
        this.playerList = myDataset;
        this.listener = listener;
        playerListFull = new ArrayList<>(myDataset);
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

        final Player currentDataPlayer = playerList.get(position);
        //holder.txtHeader.setText(String.valueOf(currentDataPlayer.getId()));

        String pos = currentDataPlayer.getPosition();

        holder.txtHeader.setText(currentDataPlayer.getFirst_name() + " " +  currentDataPlayer.getLast_name() );

        String textPos = new String();
        if(pos.contains("C")){
            textPos = "Pivot " ;
        }
        if(pos.contains("F")){
            textPos  = textPos + "Ailier Fort " ;

        }
        if(pos.contains("G")){
            textPos  = textPos + "Meneur " ;

        }

        holder.txtFooter.setText(textPos);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v){
               listener.onItemClick(currentDataPlayer);
           }
        });
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    @Override
    public Filter getFilter() {
        return playerFilter;
    }

    private Filter playerFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Player> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(playerListFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Player player : playerListFull){
                    if(player.getFirst_name().toLowerCase().contains(filterPattern) || player.getLast_name().toLowerCase().contains(filterPattern)){
                        filteredList.add(player);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results){
            playerList.clear();
            playerList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}


