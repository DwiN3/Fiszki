package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class PanelKits extends AppCompatActivity implements SelectListenerKits, View.OnClickListener {
    NextActivity nextActivity = new NextActivity(this);

    private Button edit, del, menu;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_kits);
        setID();

        edit.setOnClickListener(this);
        del.setOnClickListener(this);
        menu.setOnClickListener(this);

        ArrayList<ModelKits> modelKitsArray = new ArrayList<>();
        modelKitsArray.add(new ModelKits("Zestaw 1","ILOSC FISZEK", "30"));
        modelKitsArray.add(new ModelKits("Zestaw 2","ILOSC FISZEK", "18"));
        modelKitsArray.add(new ModelKits("Zestaw 3","ILOSC FISZEK", "25"));
        modelKitsArray.add(new ModelKits("Zestaw 4","ILOSC FISZEK", "30"));
        modelKitsArray.add(new ModelKits("Zestaw 5","ILOSC FISZEK", "11"));

        mRecyclerView = findViewById(R.id.kitsPanelRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new AdapterKit(modelKitsArray, this, R.layout.recycler_view_kits_small);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonEditKitPanel:

                break;
            case R.id.buttonDeleteKitPanel:

                break;
            case R.id.buttonBackToMenuPanel:
                nextActivity.openActivity(MainMenu.class);
                break;
        }
    }

    @Override
    public void onItemClicked(ModelKits modelKit) {
        Toast.makeText(this, modelKit.getTextNumberKit(), Toast.LENGTH_LONG).show();
    }

    private void setID() {
        edit = findViewById(R.id.buttonEditKitPanel);
        del = findViewById(R.id.buttonDeleteKitPanel);
        menu = findViewById(R.id.buttonBackToMenuPanel);
    }
}