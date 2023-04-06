package com.kdbk.fiszki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFlashcard extends RecyclerView.Adapter<AdapterFlashcard.MyViewHolder>{

    private ArrayList<ModelFlashcard> listFlashcards;
    private SelectListenerFlashcard listener;

    public AdapterFlashcard(ArrayList<ModelFlashcard> listFlashcards, SelectListenerFlashcard listener){
        this.listFlashcards = listFlashcards;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterFlashcard.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_add_flashcard, parent, false);
        AdapterFlashcard.MyViewHolder myHolder = new AdapterFlashcard.MyViewHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFlashcard.MyViewHolder holder, int position) {
        ModelFlashcard currentItem = listFlashcards.get(position);
        holder.nameSetting.setText(currentItem.getNameSettings());
        holder.editFlashcard.setText(currentItem.getEditFlashcard());
        holder.image.setImageResource(currentItem.getImageResource());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                listener.onItemClicked(AdapterFlashcard.this.listFlashcards.get(clickedPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFlashcards.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nameSetting;
        public ImageView image;
        public EditText editFlashcard;
        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameSetting = itemView.findViewById(R.id.textAddFlashcard);
            image = itemView.findViewById(R.id.imageAddFlashcard);
            editFlashcard = itemView.findViewById(R.id.editTextAddFlashcard);
            cardView = itemView.findViewById(R.id.recycleAddFlashcard);
        }
    }
}
