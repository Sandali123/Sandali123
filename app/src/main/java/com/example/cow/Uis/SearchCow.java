package com.example.cow.Uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cow.R;

public class SearchCow extends AppCompatActivity {

    private TextView nameTxt,sexTxt,breedTxt,dobTxt,ageTxt,jDateTxt,howObtTxt,dtnTxt,sntTxt,tagNumber;
    public String key,search;
    private Button updateBtn,deleteBtn,addBtn;
    private EditText searchTxt;
    private SQLiteDatabase sqLiteDatabase;
    private ScrollView srchScroll;
    private LinearLayout noRecords,addRecordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cow);

        nameTxt = findViewById(R.id.nameSrch);
        sexTxt = findViewById(R.id.sexSrch);
        breedTxt = findViewById(R.id.breedNumSrch);
        dobTxt = findViewById(R.id.BirthdaySrch);
        ageTxt = findViewById(R.id.ageSrch);
        jDateTxt = findViewById(R.id.joinDateSrch);
        howObtTxt = findViewById(R.id.howObtainSrch);
        sntTxt = findViewById(R.id.sireNameTagSrch);
        srchScroll =findViewById(R.id.srcScroll);
        noRecords = findViewById(R.id.noRecordTxt);
        addRecordBtn = findViewById(R.id.addRecordBtn);


        searchTxt = findViewById(R.id.searchTagNumTxt);
        sqLiteDatabase = openOrCreateDatabase("Cow_Details", Context.MODE_PRIVATE, null);


        searchTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    search = searchTxt.getText().toString();
                    if (search.isEmpty()){
                        Toast.makeText(SearchCow.this, "Enter Tag Number", Toast.LENGTH_SHORT).show();

                    }else {

                        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM Cow_Details WHERE TagNumber='"+search+"'",null);
                        if (c.moveToFirst()) {

                            noRecords.setVisibility(View.GONE);
                            addRecordBtn.setVisibility(View.GONE);
                            srchScroll.setVisibility(View.VISIBLE);

                            nameTxt.setText(c.getString(0));
                            sexTxt.setText(c.getString(1));
                            breedTxt.setText(c.getString(2));
                            dobTxt.setText(c.getString(3));
                            ageTxt.setText(c.getString(4));
                            jDateTxt.setText(c.getString(5));
                            howObtTxt.setText(c.getString(6));
//                        dtnTxt.setText(c.getString(7));
                            sntTxt.setText(c.getString(8));

                        }else {

                            noRecords.setVisibility(View.VISIBLE);
                            addRecordBtn.setVisibility(View.VISIBLE);
                        }

                    }
                    return true;
                }
                return false;
            }
        });

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SearchCow.this, AddCow.class);
                startActivity(i);
            }
        });


        //Update Intent Change
        updateBtn = findViewById(R.id.updateBtn);

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(SearchCow.this, Update.class);
                        i.putExtra("name", search);
                        startActivity(i);

                    }
                });

                deleteBtn = findViewById(R.id.deleteBtn);
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sqLiteDatabase.execSQL("DELETE FROM Cow_Details WHERE TagNumber='"+search+"'");
                        Toast.makeText(SearchCow.this, "Record Deleted", Toast.LENGTH_SHORT).show();

                        Intent homeIntent = new Intent(SearchCow.this,Home.class);
                        startActivity(homeIntent);


                    }
                });


        }

}
