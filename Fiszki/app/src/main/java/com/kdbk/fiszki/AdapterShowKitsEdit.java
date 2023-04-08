package com.kdbk.fiszki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterShowKitsEdit extends RecyclerView.Adapter<AdapterShowKitsEdit.MyViewHolder> {

    private ArrayList<ModelShowKitsEdit> listCategories;
    private SelectListenerShowKitsEdit listener;

    public AdapterShowKitsEdit(ArrayList<ModelShowKitsEdit> listCategories, SelectListenerShowKitsEdit listener){
        this.listCategories = listCategories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterShowKitsEdit.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_show_kits, parent, false);
        AdapterShowKitsEdit.MyViewHolder myHolder = new AdapterShowKitsEdit.MyViewHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShowKitsEdit.MyViewHolder holder, int position) {
        ModelShowKitsEdit currentItem = listCategories.get(position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                listener.onItemClicked(AdapterShowKitsEdit.this.listCategories.get(clickedPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.recycleShowFlashcard);
        }
    }
}
