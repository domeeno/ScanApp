package com.example.scanapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.scanapp.R;
import com.example.scanapp.Functional.Scanner;
import com.example.scanapp.Visual.MyPagerAdapter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {

    public static String scanResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.view_pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this);
        viewPager.setAdapter(myPagerAdapter);


        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(MainActivity.this, "Camera is needed to scan the QR", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

            }
        }).check();

        //Button to the next page
        Button scanButton = findViewById(R.id.scan_btn);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanner();
                Log.i("ActivityChange", "Switch to Scanner activity was successful");
            }
        });
    }

    public void openScanner() {
        Intent intent = new Intent(this, Scanner.class);
        startActivity(intent);
    }
}