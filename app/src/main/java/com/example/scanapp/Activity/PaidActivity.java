package com.example.scanapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.scanapp.Connection.DBConnection;
import com.example.scanapp.R;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid);

//        try {
//            Statement statement = DBConnection.connect.createStatement();
//            ResultSet rs = statement.executeQuery("Insert OrderDishes");
//        } catch (SQLException) {
//
//        }
    }
}
