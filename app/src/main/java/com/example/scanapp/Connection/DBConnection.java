package com.example.scanapp.Connection;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private String ip, db, un, password;
    static public Connection connect;

    public DBConnection(String ip, String db, String un, String password){
        this.ip = ip;
        this.un = un;
        this.password = password;
        this.db = db;
    }

    public void establishConnection(){
        connect = CONN(un, password, db, ip);

        try {
            connect = CONN(un, password, db, ip);
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
        String connURL;
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            //ConnURL
            connURL = "jdbc:jtds:sqlserver://" + _server + ";database=" + _DB + ";user=" + _user + ";password=" + _pass + ";";
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


}
