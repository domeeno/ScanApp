package com.example.scanapp.Functional;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.scanapp.Activity.MenuActivity;
import com.example.scanapp.Activity.ReceiptActivity;
import com.example.scanapp.Connection.DBConnection;
import com.google.zxing.Result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.scanapp.Activity.MainActivity.scanResult;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);
    }

    @Override
    public void handleResult(Result result) {
        scanResult = result.getText();

        Log.i("subString", scanResult.substring(0, 10)+ " " + scanResult.substring(11, 16) + " " + scanResult.substring(17, 28));

        try {
            DBConnection dbConnection = new DBConnection("sql5050.site4now.net", "DB_A512F2_" + scanResult.substring(0, 10), "DB_A512F2_restaurant_" + scanResult.substring(11, 16),
                    "" + scanResult.substring(17, 28));
            dbConnection.establishConnection();
        } catch (Exception e) {
            Log.i("UnableConnect", "Unable to connect");
            Toast.makeText(this, "Unable to connect to the db", Toast.LENGTH_LONG).show();
            onBackPressed();
        }

        Log.i("subString", scanResult.substring(30, 31)+ " " + scanResult.substring(32, 34));

//        try {
//            Statement statement = DBConnection.connect.createStatement();
//            ResultSet rs = statement.executeQuery("Insert into OrderDetails(RestaurantID, TableID, KeyWordID, OrderIsDone) values ('" + scanResult.substring(30, 31) + "', " +
//                    "'"+ scanResult.substring(32, 34) + "', '1', '0')");
//        } catch (SQLException e) {
//            Log.e("trist1", "ce de trsit1");
//        }


        Log.i("testResults", scanResult);

        openMenu();
    }

    @Override
    protected void onPause() {
        super.onPause();

        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }

    public void openMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
