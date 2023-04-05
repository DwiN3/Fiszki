package com.kdbk.fiszki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.MyViewHolder> {
    private ArrayList<ModelCategories> listCategories;

    public AdapterCategories(ArrayList<ModelCategories> listCategories){
        this.listCategories = listCategories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_categories, parent, false);
        MyViewHolder myHolder = new MyViewHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelCategories currentItem = listCategories.get(position);
        holder.nameCategory.setText(currentItem.getNameCategory());
        holder.imageCategory.setImageResource(currentItem.getImageResource());
    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nameCategory;
        public ImageView imageCategory;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCategory = itemView.findViewById(R.id.nameCategory);
            imageCategory = itemView.findViewById(R.id.imageCategory);
        }
    }
}
