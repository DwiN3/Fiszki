package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class YourKits extends AppCompatActivity implements SelectListenerKits{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_kits);

        ArrayList<ModelKits> modelKitsArray = new ArrayList<>();
        modelKitsArray.add(new ModelKits("Zestaw 1","ILOSC FISZEK", "30"));
        modelKitsArray.add(new ModelKits("Zestaw 2","ILOSC FISZEK", "18"));
        modelKitsArray.add(new ModelKits("Zestaw 3","ILOSC FISZEK", "25"));
        modelKitsArray.add(new ModelKits("Zestaw 4","ILOSC FISZEK", "30"));
        modelKitsArray.add(new ModelKits("Zestaw 5","ILOSC FISZEK", "11"));

        mRecyclerView = findViewById(R.id.kitsRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterKit(modelKitsArray, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClicked(ModelKits modelCategories) {
        //Toast.makeText(this, modelCategories.getTextNumberKit(), Toast.LENGTH_LONG).show();
        openActivity(LearningScreen.class);
    }

    private void openActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}