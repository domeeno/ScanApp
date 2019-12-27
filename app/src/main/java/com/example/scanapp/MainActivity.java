package com.example.scanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    //for DB
    String ip, db, un, passwords;
    Connection connect;
//    PreparedStatement stmt;
//    ResultSet rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button to the next page
        Button scanButton = findViewById(R.id.scan_btn);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
                Log.i("Visual", "The switch was successful");
            }
        });


        //db connection
        ip = "localhost";
        un = "DESKTOP-QPH6I1J/user";
        passwords = "";
        db = "restaurant";

        connect = CONN(un, passwords, db, ip);
        String query = "select * from countries";

        try {
            connect = CONN(un, passwords, db, ip);
            Log.i("SuccessfulConnection", "The connection with the database has been established");
        } catch (Exception e) {
            Log.e("ConnectionFailure", "Unable to connect to the Database");
        }
    }

    //MSSQLSERVER - instance name

    @SuppressLint("NewApi")
    private Connection CONN(String _user, String _pass, String _DB, String _server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String connURL = null;
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            //ConnURL
            connURL = "jdbc:jtds:sqlserver://sql5050.site4now.net;database=DB_A512F2_restaurant;user=DB_A512F2_restaurant_admin;password=dominic1234";
            conn = DriverManager.getConnection(connURL);
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