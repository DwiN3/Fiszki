package com.kdbk.fiszki.RecyclerView.Adaper;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kdbk.fiszki.RecyclerView.Model.ModelEditFlashcard;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerEditFlashcard;

import java.util.ArrayList;

public class AdapterEditFlashcard extends RecyclerView.Adapter<AdapterEditFlashcard.MyViewHolder>{

    private ArrayList<ModelEditFlashcard> listEditFlashcards;
    private SelectListenerEditFlashcard listener;
    private AdapterEditFlashcard.OnEditTextChangeListener mListener;


    public AdapterEditFlashcard(ArrayList<ModelEditFlashcard> listEditFlashcards, SelectListenerEditFlashcard listener){
        this.listEditFlashcards = listEditFlashcards;
        this.listener = listener;
    }

    public AdapterEditFlashcard(ArrayList<ModelEditFlashcard> listEditFlashcards, SelectListenerEditFlashcard listener, AdapterEditFlashcard.OnEditTextChangeListener listenerEditText){
        this.listEditFlashcards = listEditFlashcards;
        this.listener = listener;
        this.mListener = listenerEditText;
    }

    @NonNull
    @Override
    public AdapterEditFlashcard.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_edit_flashcard, parent, false);
        AdapterEditFlashcard.MyViewHolder myHolder = new AdapterEditFlashcard.MyViewHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEditFlashcard.MyViewHolder holder, int position) {
        ModelEditFlashcard currentItem = listEditFlashcards.get(position);
        holder.editWord.setText(currentItem.getEditWord());
        holder.image.setImageResource(currentItem.getImageResource());

        holder.editWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mListener.onEditTextChanged(currentItem.getCardId(), s.toString());
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                listener.onItemClicked(AdapterEditFlashcard.this.listEditFlashcards.get(clickedPosition));
            }
        });
    }





    @Override
    public int getItemCount() {
        return listEditFlashcards.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView editWord;
        public ImageView image;
        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            editWord = itemView.findViewById(R.id.editTextEditFlashcard);
            image = itemView.findViewById(R.id.imageEditFlashcard);
            cardView = itemView.findViewById(R.id.recycleEditFlashcard);
        }
    }

    public interface OnEditTextChangeListener {
        void onEditTextChanged(int cardId, String newText);
    }

}