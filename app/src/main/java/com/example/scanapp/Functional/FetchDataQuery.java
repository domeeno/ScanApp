package com.example.scanapp.Functional;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FetchDataQuery {
    private ArrayList<String> list;
    private String query;
    private HashMap<String, List<String>> subList;
    private Connection connect;

    public FetchDataQuery(ArrayList<String> list, HashMap<String, List<String>> subList, String query, Connection connect) throws SQLException {
        this.list = list;
        this.query = query;
        this.subList = subList;
        this.connect = connect;

        executeQuery();
    }

    public void executeQuery() throws SQLException {
        try {
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs != null) {
                while (rs.next()) {
                    list.add(rs.getString("CategoryName"));
                }
            } else {
                Log.e("NoData", "No data found, check connection or the db");
            }

            List<List<String>> array = new ArrayList<List<String>>();

            for (int i = 0; i < list.size(); i++) {
                array.add(new ArrayList<String>());
            }

            Log.i("ArraySize", "Array size" + array.get(0).size());

            for ( int i = 0; i < list.size(); i++) {
                    Statement stmt = connect.createStatement();
                    ResultSet rs1 = stmt.executeQuery("select Dishname from Dishes D1 left join  DishDetails d2\n" +
                            "on d1.DishID = d2.DishID\n" +
                            "WHERE CategoryID = (Select CategoryID from DishCategories where CategoryName = '" + list.get(i) + "');");

                    if (rs1 != null) {
                        while (rs1.next()) {
                            array.get(i).add(rs1.getString("DishName"));
                        }
                    } else {
                        Log.e("NoData", "No data found, check connection or the db");
                    }
                subList.put(list.get(i), array.get(i));
            }

        } catch (SQLException e) {
            Log.e("ConnectionFailure", "Connection Failure " + query);
            throw e;
        }
    }

}