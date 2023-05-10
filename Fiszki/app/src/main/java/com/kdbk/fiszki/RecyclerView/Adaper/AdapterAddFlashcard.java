package com.kdbk.fiszki.RecyclerView.Adaper;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kdbk.fiszki.RecyclerView.Model.ModelAddFlashcard;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerAddFlashcard;

import java.util.ArrayList;

public class AdapterAddFlashcard extends RecyclerView.Adapter<AdapterAddFlashcard.MyViewHolder>{

    private ArrayList<ModelAddFlashcard> listFlashcards;
    private SelectListenerAddFlashcard listener;
    private OnEditTextChangeListener mListener;


    public AdapterAddFlashcard(ArrayList<ModelAddFlashcard> listFlashcards, SelectListenerAddFlashcard listener){
        this.listFlashcards = listFlashcards;
        this.listener = listener;
    }

    public AdapterAddFlashcard(ArrayList<ModelAddFlashcard> listFlashcards, SelectListenerAddFlashcard listener, OnEditTextChangeListener listenerEditText){
        this.listFlashcards = listFlashcards;
        this.listener = listener;
        this.mListener = listenerEditText;
    }

    @NonNull
    @Override
    public AdapterAddFlashcard.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_add_flashcard, parent, false);
        AdapterAddFlashcard.MyViewHolder myHolder = new AdapterAddFlashcard.MyViewHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAddFlashcard.MyViewHolder holder, int position) {
        ModelAddFlashcard currentItem = listFlashcards.get(position);
        holder.nameSetting.setText(currentItem.getNameSettings());
        holder.editFlashcard.setText(currentItem.getEditFlashcard());
        holder.image.setImageResource(currentItem.getImageResource());

        holder.editFlashcard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mListener.onEditTextChanged(currentItem.getcardId(), s.toString());
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                listener.onItemClicked(AdapterAddFlashcard.this.listFlashcards.get(clickedPosition));
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

    public interface OnEditTextChangeListener {
        void onEditTextChanged(int cardId, String newText);
    }

}
