package com.example.scanapp.Functional;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FetchData {
    private ArrayList<Category> list;
    private String query;
    private HashMap<Category, List<Dish>> subList;
    private Connection connect;

    public FetchData(ArrayList<Category> list, HashMap<Category, List<Dish>> subList, String query, Connection connect) {
        this.list = list;
        this.query = query;
        this.subList = subList;
        this.connect = connect;

    }

    public void executeQueryExpandableListView() throws SQLException {
        try {
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    Category c = new Category(rs.getString("CategoryName"));
                    list.add(c);
                    Log.i("phh", c.getCategoryName());
                }
            } else {
                Log.e("NoData", "No data found, check connection or the db");
            }

            List<List<Dish>> array = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                array.add(new ArrayList<Dish>());
            }

            for ( int i = 0; i < list.size(); i++) {
                    Statement stmt = connect.createStatement();
                    ResultSet rs1 = stmt.executeQuery("select Dishname, DishPrice from Dishes D1 left join  DishDetails d2\n" +
                            "on d1.DishID = d2.DishID\n" +
                            "WHERE CategoryID = (Select CategoryID from DishCategories where CategoryName = '" + list.get(i).getCategoryName() + "');");

                    if (rs1 != null) {
                        while (rs1.next()) {
                            Dish d = new Dish(rs1.getString("DishName"), rs1.getString("DishPrice"));
                            array.get(i).add(d);
                        }
                    } else {
                        Log.e("NoData", "No data found, check connection or the db");
                    }
                subList.put(list.get(i), array.get(i));
            }

            Log.i("yeah", "yeah");

        } catch (SQLException e) {
            Log.e("ConnectionFailure", "Connection Failure " + query);
            throw e;
        }
    }
}