package com.example.scanapp.Functional;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FetchDataListView {
    private ArrayList<Dish> orders;
    private String query;
    private Connection connect;

    public FetchDataListView(ArrayList<Dish> orders, String query, Connection connect) throws SQLException {
        this.orders = orders;
        this.query = query;
        this.connect = connect;

        executeQuery();
    }

    private void executeQuery() throws SQLException {
        Statement statement = connect.createStatement();
        ResultSet rs = statement.executeQuery("la");


        if (rs != null) {
            while (rs.next()) {
                Dish c = new Dish(rs.getString("CategoryName"), rs.getString("DishPrice"));
                orders.add(c);
            }
        } else {
            Log.e("NoData", "No data found, check connection or the db");
        }

    }
}
