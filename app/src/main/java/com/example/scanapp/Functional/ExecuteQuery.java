package com.example.scanapp.Functional;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExecuteQuery {
    private ArrayList<GroupItems> list;
    private String query;
    private Connection connect;

    public ExecuteQuery(ArrayList<GroupItems> list, String query, Connection connect) {
        this.list = list;
        this.query = query;
        this.connect = connect;

        executeQuery();
    }

    public void executeQuery(){
        try{
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if(rs != null){
                while(rs.next()){
                    list.add(new GroupItems(rs.getString("CategoryName"), rs.getString("ImageData")));
                }
            } else {
                Log.e("NoData", "No data found, check connection or the db");
            }


        } catch (SQLException e) {
            Log.e("ConnectionFailure", "Connection Failure");
        }
    }

}