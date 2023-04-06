package com.kdbk.fiszki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterKit extends RecyclerView.Adapter<AdapterKit.MyViewHolder> {
    private ArrayList<ModelKits> listKits;
    private SelectListenerKits listener;
    private static int infoLayout;

    public AdapterKit(ArrayList<ModelKits> listKits, SelectListenerKits listener, int infoLayout){
        this.listKits = listKits;
        this.listener = listener;
        this.infoLayout = infoLayout;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(infoLayout, parent, false);
        MyViewHolder myHolder = new MyViewHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelKits currentItem = listKits.get(position);
        holder.numberKit.setText(currentItem.getTextNumberKit());
        holder.textTEXTflashcards.setText(currentItem.getTextTEXTflashcards());
        holder.numberOfCards.setText(currentItem.getTextNumberOfCards());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                listener.onItemClicked(AdapterKit.this.listKits.get(clickedPosition));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listKits.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView numberKit, textTEXTflashcards, numberOfCards;
        public CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            if(infoLayout == R.layout.recycler_view_kits){
                numberKit = itemView.findViewById(R.id.textNumberKit);
                textTEXTflashcards = itemView.findViewById(R.id.TextTEXTflashcards);
                numberOfCards = itemView.findViewById(R.id.textNumberOfCards);
                cardView = itemView.findViewById(R.id.recycleKits);
            } else if (infoLayout == R.layout.recycler_view_kits_small) {
                numberKit = itemView.findViewById(R.id.textNumberKitSmall);
                textTEXTflashcards = itemView.findViewById(R.id.TextTEXTflashcardsSmall);
                numberOfCards = itemView.findViewById(R.id.textNumberOfCardsSmall);
                cardView = itemView.findViewById(R.id.recycleKitsSmall);
            }
        }
    }
}
