package com.example.scanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    GridView gridview;
    ArrayList<String> arrayList;

    //for DB
    String ip, db, un, passwords;
    Connection connect;
    PreparedStatement stmt;
    ResultSet rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button scanButton = findViewById(R.id.scan_btn);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
                Log.i("Visual", "The switch was successful");
            }
        });

        ip = "localhost";
        un = "appUser";
        passwords = "1234";
        db = "Restaurant";
        gridview = (GridView) findViewById(R.id.gridview);

        connect = CONN(un, passwords, db, ip);
        String query = "select * from countries";

        try {
            connect = CONN(un, passwords, db, ip);
            Statement statement = connect.createStatement();
            rs = statement.executeQuery(query);
            List<Map<String, String>> data;
            data = new ArrayList<>();

            while (rs.next()) {
                Map<String, String> datanum = new HashMap<>();
                datanum.put("A", rs.getString("CustomerID"));
                datanum.put("B", rs.getString("TableNo"));

                data.add(datanum);
            }

            String[] from = {"A", "B"};
            int[] views = {R.id.txtcountry, R.id.txtcontinent};
            final SimpleAdapter ADA = new SimpleAdapter(MainActivity.this,
                    data, R.layout.templateforgrid, from, views);
            gridview.setAdapter(ADA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NewApi")
    private Connection CONN(String _user, String _pass, String _DB,
                            String _server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL;
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnURL = "jdbc:jtds:sqlserver://" + _server + ";"
                    + "databaseName=" + _DB + ";user=" + _user + ";password="
                    + _pass + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }


    public void openMenu() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}