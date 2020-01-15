package com.example.scanapp.Functional;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.scanapp.Activity.MenuActivity;
import com.example.scanapp.Activity.ReceiptActivity;
import com.example.scanapp.Connection.DBConnection;
import com.google.zxing.Result;

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

        DBConnection dbConnection = new DBConnection("sql5050.site4now.net", "DB_A512F2_restaurant", "DB_A512F2_restaurant_admin",
                "dominic1234");
        dbConnection.establishConnection();
        
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
