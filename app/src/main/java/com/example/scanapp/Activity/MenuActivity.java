package com.example.scanapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import com.example.scanapp.Connection.DBConnection;
import com.example.scanapp.Functional.Category;
import com.example.scanapp.Functional.Dish;
import com.example.scanapp.Functional.FetchData;
import com.example.scanapp.R;
import com.example.scanapp.Visual.ExpandedListAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class MenuActivity extends AppCompatActivity {


    ExpandableListView expandableListView;

    ArrayList<Category> dishCategory = new ArrayList<>();
    HashMap<Category, List<Dish>> dish = new HashMap<>();
    public static ArrayList<Dish> orders = new ArrayList<>();

    ExpandableListAdapter listAdapter;
    String query = "Select CategoryName from DishCategories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        expandableListView = findViewById(R.id.expandableListView);
        fillExpandableList();

        listAdapter = new ExpandedListAdapter(this, dishCategory, dish, orders);
        expandableListView.setAdapter(listAdapter);


        Button seeOrderButton = findViewById(R.id.see_order_btn);
        final Context context = getApplicationContext();
        seeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orders.size() != 0){
                    openMenu();
                    Log.i("Visual", "The switch was successful");
                } else {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, "No Orders", duration);
                    toast.show();
                }
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
            FetchData fetchData = new FetchData(dishCategory, dish, query, DBConnection.connect);
            fetchData.executeQueryExpandableListView();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
