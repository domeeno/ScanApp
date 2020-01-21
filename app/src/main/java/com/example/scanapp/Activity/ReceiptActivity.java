package com.example.scanapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.scanapp.Functional.Dish;
import com.example.scanapp.R;
import com.example.scanapp.Visual.CustomAdapter;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.scanapp.Connection.DBConnection.connect;

public class ReceiptActivity extends AppCompatActivity {

    ListView listView;
    TextView totalPrice;
    String textViewPrice;
    int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_pay);

        listView = findViewById(R.id.listView);

//        try {
//            Statement statement = connect.createStatement();
//            ResultSet rs = statement.executeQuery("Select");
//
//            name.setText(rs.getString("DishName"));
//            price.setText(rs.getString("DishPrice"));
//            ingredients.setText(rs.getString("DishIngredients"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        CustomAdapter customAdapter = new CustomAdapter(this, MenuActivity.orders);
        listView.setAdapter(customAdapter);

        totalPrice = findViewById(R.id.totalPrice);

        for (int i = 0; i < MenuActivity.orders.size(); i++) {
            price += (Integer.parseInt(MenuActivity.orders.get(i).getDishPrice())*MenuActivity.orders.get(i).getDishCount());
        }

        textViewPrice = "Total: " + price;
        totalPrice.setText(textViewPrice);

        Button button = findViewById(R.id.make_order);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
    }

    public void openMenu() {
        Intent intent = new Intent(this, PaidActivity.class);
        startActivity(intent);
    }
}
