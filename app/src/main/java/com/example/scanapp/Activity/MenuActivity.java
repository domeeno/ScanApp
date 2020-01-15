package com.example.scanapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.scanapp.Connection.DBConnection;
import com.example.scanapp.Functional.FetchDataQuery;
import com.example.scanapp.R;
import com.example.scanapp.Visual.ExpandedListAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity {


    ExpandableListView expandableListView;

    ArrayList<String> dishCategory = new ArrayList<String>();
    HashMap<String, List<String>> dish = new HashMap<>();
    ExpandableListAdapter listAdapter;
    String query = "Select CategoryName from DishCategories";

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

        try {
            FetchDataQuery dishCathegoryQuery = new FetchDataQuery(dishCategory, dish, query, DBConnection.connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
