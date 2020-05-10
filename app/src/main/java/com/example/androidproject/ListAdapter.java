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
        //holder.txtHeader.setText(String.valueOf(currentDataPlayer.getId()));

        String pos = currentDataPlayer.getPosition();
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
        holder.txtHeader.setText(currentDataPlayer.getFirst_name() + " " +  currentDataPlayer.getLast_name() + " " + textPos);



        String textFeet = new String();
        if(currentDataPlayer.getHeight_feet() == null){
            textFeet = "noData " ;
        }else{
            textFeet = currentDataPlayer.getHeight_feet();
        }

        String textInches = new String();
        if(currentDataPlayer.getHeight_inches() == null){
            textInches = "noData " ;
        }else{
            textInches = currentDataPlayer.getHeight_inches();
        }

        String textPounds = new String();
        if( currentDataPlayer.getWeight_pounds() == null){
            textPounds = "noData " ;
        }else{
            textPounds = currentDataPlayer.getWeight_pounds();
        }

        //holder.txtFooter.setText(currentDataPlayer.getHeight_feet() + " feets " + currentDataPlayer.getHeight_inches() + " inches " + currentDataPlayer.getWeight_pounds() + " pounds ");
        holder.txtFooter.setText(textFeet + " feets " + textInches + " inches " + textPounds + " pounds ");


    }


    @Override
    public int getItemCount() {
        return values.size();
    }
}


