package com.example.scanapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scanapp.Connection.DBConnection;
import com.example.scanapp.Functional.Dish;
import com.example.scanapp.R;
import com.example.scanapp.Visual.ExpandedListAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.scanapp.Activity.MainActivity.scanResult;

public class DishInformationActivity extends AppCompatActivity {

    TextView dishName;
    TextView dishPrice;
    TextView dishIngredients;
    ImageView dishImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_information);

        dishName = findViewById(R.id.dishInfoName);
        dishIngredients = findViewById(R.id.dishInfoIngredients);
        dishPrice = findViewById(R.id.dishInfoPrice);
        dishImage = findViewById(R.id.dishInfoImage);

        switch (ExpandedListAdapter.dishCurrentInfo.getDishName()) {
            case "Capricioasa":
                dishImage.setImageResource(R.drawable.capricioasa);
                break;
            case "Barbeque":
                dishImage.setImageResource(R.drawable.barbeque);
                break;
            case "4 cheeses":
                dishImage.setImageResource(R.drawable.cheeses4);
                break;
            case "Pepperoni":
                dishImage.setImageResource(R.drawable.pepperoni);
                break;
            case "Rancho":
                dishImage.setImageResource(R.drawable.rancho);
                break;
        }

//        try {
//            Statement statement = DBConnection.connect.createStatement();
//            ResultSet rs = statement.executeQuery();
//            dishName.setText(rs.getString(""));
//            dishPrice.setText(rs.getString(""));
//            dishIngredients.setText(rs.getString(""));
//        } catch (SQLException e) {
//            Log.e("trist1", "ce de trsit1");
//        }

    }
}
