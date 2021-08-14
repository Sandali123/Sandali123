package com.example.cow.Uis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cow.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.util.HashMap;

public class Home extends AppCompatActivity {


    private ImageView addFarmer,addCow,searchCow,report,calculator;
    private Button searchBtn,addBtn,updateAdd,updateBtn,deleteBtn;
    private EditText searchTxt;
    private  LinearLayout calLayout,addLayout;
    private ScrollView srchScroll,addScroll;
    public String key,search;

    private SQLiteDatabase sqLiteDatabase;

    private TextView nameTxt,sexTxt,breedTxt,dobTxt,ageTxt,jDateTxt,howObtTxt,dtnTxt,sntTxt,tagNumber;
    private EditText addTagNum,addName,addSex,addBOD,addBreed,addAge,addJoinedDate,addHowObt,addDamTagNum,addSireNameTag;
    private EditText num1,num2;
    private TextView ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();


        addFarmer = findViewById(R.id.addfarmer);
        addFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent AddFarmer = new Intent(Home.this,AddFarmer.class);
                startActivity(AddFarmer);


            }
        });


        addCow = findViewById(R.id.addCow);
        addCow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddCow = new Intent(Home.this,AddCow.class);
                startActivity(AddCow);

            }
        });


        searchCow = findViewById(R.id.searchCow);
        searchCow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent SearchIntent = new Intent(Home.this,SearchCow.class);
                startActivity(SearchIntent);


            }
        });


        calculator = findViewById(R.id.calculator);
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Calculator = new Intent(Home.this,Calculator.class);
                startActivity(Calculator);

            }
        });


//        profit calculation

//        num1= findViewById(R.id.num1);
//        num2 = findViewById(R.id.num2);
//        ans = findViewById(R.id.ansTxt);
//        ans.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int profit;
//                String income;
//                String expenses;
//                income = num1.getText().toString();
//                expenses = num2.getText().toString();
//                profit = Integer.parseInt(income) - Integer.parseInt(expenses);
//                ans.setText(profit);
//            }
//        });


//
////Search
//        nameTxt = findViewById(R.id.nameSrch);
//        sexTxt = findViewById(R.id.sexSrch);
//        breedTxt = findViewById(R.id.breedNumSrch);
//        dobTxt = findViewById(R.id.BirthdaySrch);
//        ageTxt = findViewById(R.id.ageSrch);
//        jDateTxt = findViewById(R.id.joinDateSrch);
//        howObtTxt = findViewById(R.id.howObtainSrch);
//        sntTxt = findViewById(R.id.sireNameTagSrch);
//        calLayout = findViewById(R.id.calLayout);
//        srchScroll = findViewById(R.id.srcScroll);
//
////ADD
//        addTagNum =findViewById(R.id.tagAdd);
//        addName = findViewById(R.id.nameAdd);
//        addSex = findViewById(R.id.sexAdd);
//        addBOD = findViewById(R.id.BirthdayAdd);
//        addBreed = findViewById(R.id.breedAdd);
//        addAge = findViewById(R.id.ageAdd);
//        addJoinedDate = findViewById(R.id.joinDateAdd);
//        addHowObt = findViewById(R.id.howObtainAdd);
//        addDamTagNum =findViewById(R.id.damTagNumAdd);
//        addSireNameTag = findViewById(R.id.sireNameTagAdd);
//        addLayout = findViewById(R.id.addLayout);
//        addScroll = findViewById(R.id.addScroll);
//        updateAdd = findViewById(R.id.updateAdd);

//
//        sqLiteDatabase = openOrCreateDatabase("Cow_Details", Context.MODE_PRIVATE, null);
//        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Cow_details (TagNumber VARCHAR,Name VARCHAR, Sex VARCHAR, Breed VARCHAR, DOB VARCHAR, Age VARCHAR, Joined_when VARCHAR, How_obtained VARCHAR, DamTagNumber VARCHAR, SireNameTag VARCHAR);");
//
//
////        Add new Cow details
//        addBtn = findViewById(R.id.addBtn);
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                calLayout.setVisibility(View.GONE);
//                addScroll.setVisibility(View.VISIBLE);
//                searchBtn.setVisibility(View.GONE);
//                searchTxt.setVisibility(View.GONE);
//
//
//            }
//        });
//
//        updateAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
////                todo:validation
//
//
//                sqLiteDatabase.execSQL("INSERT INTO Cow_Details VALUES ('" + addTagNum.getText().toString() + "','" + addName.getText().toString() + "','" +
//                        addSex.getText().toString() + "','" + addBreed.getText().toString() + "','" + addBOD.getText().toString() + "','" + addAge.getText().toString() + "','" +
//                        addJoinedDate.getText().toString() + "','" + addHowObt.getText().toString() + "','" + addDamTagNum.getText().toString() + "','" + addSireNameTag.getText().toString() + "')");
//
//
//
//                Toast.makeText(Home.this, "Record Added", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(Home.this,Home.class);
//                startActivity(i);
//
//
//            }
//        });
//
////        Search cow details
//        searchTxt = findViewById(R.id.searchTagNumTxt);
//        searchBtn = findViewById(R.id.searchBtn);
//        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//
//                search = searchTxt.getText().toString();
//                if (search.isEmpty()){
//                    Toast.makeText(Home.this, "Enter Tag Number", Toast.LENGTH_SHORT).show();
//
//                }else {
//
//                    Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM Cow_Details WHERE TagNumber='"+search+"'",null);
//                    if (c.moveToFirst()) {
//
//                        calLayout.setVisibility(View.GONE);
//                        srchScroll.setVisibility(View.VISIBLE);
//
//                        nameTxt.setText(c.getString(0));
//                        sexTxt.setText(c.getString(1));
//                        breedTxt.setText(c.getString(2));
//                        dobTxt.setText(c.getString(3));
//                        ageTxt.setText(c.getString(4));
//                        jDateTxt.setText(c.getString(5));
//                        howObtTxt.setText(c.getString(6));
////                        dtnTxt.setText(c.getString(7));
//                        sntTxt.setText(c.getString(8));
//
//                    }else {
//                        Toast.makeText(Home.this, "No match Found ", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//
//            }
//        });
//
//        //Update Intent Change
//        updateBtn = findViewById(R.id.updateBtn);
//        updateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent i = new Intent(Home.this, Update.class);
//                i.putExtra("name", search);
//                startActivity(i);
//
//            }
//        });
//
//        deleteBtn = findViewById(R.id.deleteBtn);
//        deleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                sqLiteDatabase.execSQL("DELETE FROM Cow_Details WHERE TagNumber='"+search+"'");
//                Toast.makeText(Home.this, "Record Deleted", Toast.LENGTH_SHORT).show();
//
//                Intent homeIntent = new Intent(Home.this,Home.class);
//                startActivity(homeIntent);
//
//
//            }
//        });
//
//
//
    }
}
