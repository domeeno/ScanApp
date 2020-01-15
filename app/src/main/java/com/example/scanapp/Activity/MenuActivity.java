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
import com.example.scanapp.Functional.ExecuteQuery;
import com.example.scanapp.Functional.GroupItems;
import com.example.scanapp.R;
import com.example.scanapp.Visual.ExpandedListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity {


    ExpandableListView expandableListView;

    ArrayList<GroupItems> dishCategory;
    ArrayList<String> tempDishCategory;
    HashMap<String, List<String>> dish;
    ExpandableListAdapter listAdapter;
    String query = "Select CategoryName from DishCategories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        ExecuteQuery dishCathegoryQuery = new ExecuteQuery(dishCategory, query, DBConnection.connect);

        expandableListView = findViewById(R.id.expandableListView);
        fillExpandableList();

        listAdapter = new ExpandedListAdapter(this, tempDishCategory, dish);

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
        tempDishCategory = new ArrayList<>();
        dish = new HashMap<>();

        tempDishCategory.add("Pizza");
        tempDishCategory.add("Tea");

        List<String> pizza = new ArrayList<>();
        List<String> tea = new ArrayList<>();

        pizza.add("Peperoni");
        pizza.add("Neapolitana");

        tea.add("Ceai Bergamot");
        tea.add("Ceai Verde");

        dish.put(tempDishCategory.get(0), pizza);
        dish.put(tempDishCategory.get(1), tea);
    }
}
