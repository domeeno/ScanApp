package com.example.scanapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scanapp.Connection.DBConnection;
import com.example.scanapp.R;
import com.example.scanapp.Visual.ExpandedListAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DishInformationActivity extends AppCompatActivity {

    TextView dishName;
    TextView dishPrice;
    TextView dishIngredients;
    ImageView dishImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_information);

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

        dishName = findViewById(R.id.dishInfoName);
        dishIngredients = findViewById(R.id.dishInfoIngredients);
        dishPrice = findViewById(R.id.dishInfoPrice);

        Log.i("Info", ""+ ExpandedListAdapter.dishCurrentInfo.getDishName());

        Statement statement = null;
        try {
            statement = DBConnection.connect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rs = null;
        try {
            rs = statement.executeQuery("select DishName, DishPrice, DishIngredients \n" +
                    "from DishDetails d1 inner join Dishes d2 \n" +
                    "on d1.DishID = d2.DishID\n" +
                    "where DishName = '" + ExpandedListAdapter.dishCurrentInfo.getDishName() + "';");
        } catch (SQLException e) {
            Log.i("1", "1");
            e.printStackTrace();

        }

//        try {
//            dishName.setText(rs.getString("DishName"));
//        } catch (SQLException e) {
//            Log.i("2", "1");
//            e.printStackTrace();
//        }
//        try {
//            dishPrice.setText(rs.getString("DishPrice"));
//        } catch (SQLException e) {
//            Log.i("3", "1");
//            e.printStackTrace();
//        }
//        try {
//            dishIngredients.setText(rs.getString("DishIngredients"));
//        } catch (SQLException e) {
//            Log.i("4", "1");
//            e.printStackTrace();
//        }
        try {
            if (rs != null) {
                while (rs.next()) {
                    dishName.setText(rs.getString("DishName"));
                    dishIngredients.setText(rs.getString("DishIngredients"));
                    dishPrice.setText((rs.getString("DishPrice")));
                }
            } else {
                Log.e("NoData", "No data found, check connection or the db");
            }
        } catch (SQLException e){
            Log.e("NoData", "No data found, check connection or the db");
        }

    }
}
