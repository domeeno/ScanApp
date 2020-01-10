package com.example.scanapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.scanapp.R;
import com.example.scanapp.Visual.ExpandedListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {


    ExpandableListView expandableListView;

    List<String> dishCategory;
    Map<String, List<String>> dish;
    ExpandableListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        expandableListView = findViewById(R.id.expandableListView);
        fillExpandableList();

        listAdapter = new ExpandedListAdapter(this, dishCategory, dish);

        expandableListView.setAdapter(listAdapter);

        Button scanButton = findViewById(R.id.see_order_btn);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
                Log.i("Visual", "The switch was successful");
            }
        });
    }

    public void openMenu() {
        Intent intent = new Intent(this, ReceiptActivity.class);
        startActivity(intent);
    }

    public void fillExpandableList(){
        dishCategory = new ArrayList<>();
        dish = new HashMap<>();


    }
}
