package com.example.cow.Uis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cow.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class AddFarmer extends AppCompatActivity  {

    private EditText name, age, mobile;
    double latitude,longitude;
    String country,locality;
    private  FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmer);

        final SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = openOrCreateDatabase("Farmer_Details", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Cow_details (FarmerName VARCHAR,Age VARCHAR, Mobile VARCHAR, lat VARCHAR, long VARCHAR, Country VARCHAR, City VARCHAR);");


        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        mobile = findViewById(R.id.mobileNumber);
        final Button addRecord = findViewById(R.id.addfarmerBtn);

       fusedLocationProviderClient  = LocationServices.getFusedLocationProviderClient(this);



        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(AddFarmer.this
                ,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){

                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            if (location != null){
                                Geocoder geocoder = new Geocoder(AddFarmer.this,
                                        Locale.getDefault());
                                try {

                                    List<Address> addresses = geocoder.getFromLocation(
                                            location.getLatitude(),location.getLongitude(),1
                                    );

                                    latitude = addresses.get(0).getLatitude();
                                    longitude = addresses.get(0).getLongitude();
                                    country = addresses.get(0).getCountryName();
                                    locality = addresses.get(0).getLocality();

                                    sqLiteDatabase.execSQL("INSERT INTO Cow_Details VALUES ('" + name.getText().toString() + "','" + age.getText().toString() + "','" +
                                            mobile.getText().toString() + "','" + latitude + "','" + longitude + "','" + country +"','" + locality + "')");

                                    Toast.makeText(AddFarmer.this, "Record Added", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(AddFarmer.this,Home.class);
                                    startActivity(i);


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });


                }else {
                    ActivityCompat.requestPermissions(AddFarmer.this
                    ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }



            }
        });








}}
