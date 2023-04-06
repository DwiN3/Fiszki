package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AddFlashcard extends AppCompatActivity implements SelectListenerFlashcard {

    NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelFlashcard> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);

        list.add(new ModelFlashcard(R.drawable.arrow,"NUMER ZESTAWU","1" ,1));
        list.add(new ModelFlashcard(R.drawable.flagpl,"WPROWADŹ SŁOWO:","pies", 2));
        list.add(new ModelFlashcard(R.drawable.flagang,"DODAJ TŁUMACZENIE:","dog", 3));
        list.add(new ModelFlashcard(R.drawable.flagpl,"PRZYKŁADOWE ZDANIE","Mój pies lubi kości",4));
        list.add(new ModelFlashcard(R.drawable.flagang,"PRZYKŁADOWE ZDANIE","My dog likes bones",5));


        mRecyclerView = findViewById(R.id.addFlashcardlRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterFlashcard(list, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onItemClicked(ModelFlashcard modelFlashcard) {

    }
}