package com.kdbk.fiszki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterWordsKits extends RecyclerView.Adapter<AdapterWordsKits.MyViewHolder> {

    private ArrayList<ModelWordsKits> listCategories;
    private SelectListenerWordsKits listener;

    public AdapterWordsKits(ArrayList<ModelWordsKits> listCategories, SelectListenerWordsKits listener){
        this.listCategories = listCategories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterWordsKits.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_show_kits, parent, false);
        AdapterWordsKits.MyViewHolder myHolder = new AdapterWordsKits.MyViewHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWordsKits.MyViewHolder holder, int position) {
        ModelWordsKits currentItem = listCategories.get(position);
        holder.textID.setText(currentItem.getTextID());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                listener.onItemClicked(AdapterWordsKits.this.listCategories.get(clickedPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textID;
        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textID = itemView.findViewById(R.id.textIDinvisible);
            cardView = itemView.findViewById(R.id.recycleShowFlashcard);
        }
    }
}
