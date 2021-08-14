package com.example.cow.Uis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cow.R;

import java.util.Calendar;

public class AddCow extends AppCompatActivity {

    private EditText addTagNum,addName,addSex,addBreed,addAge,addJoinedDate,addHowObt,addDamTagNum,addSireNameTag;
    private Button AddDetails;
    private RadioButton radioMale, radioFemale;
    private RadioGroup radioGroup;
    String radioTxt,dobPicker;
    private DatePicker datePicker;
    private TextView addBOD;
    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cow);

        addTagNum = findViewById(R.id.tagNumber);
        addName = findViewById(R.id.name);
        addBreed = findViewById(R.id.breed);
        addBOD = findViewById(R.id.Birthday);
        addAge = findViewById(R.id.age);
        addJoinedDate = findViewById(R.id.joinDate);
        addHowObt = findViewById(R.id.howObtain);
        addDamTagNum = findViewById(R.id.damTagNum);
        addSireNameTag = findViewById(R.id.sireNameTag);
        AddDetails = findViewById(R.id.updateBtn);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);


        final SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = openOrCreateDatabase("Cow_Details", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Cow_details (TagNumber VARCHAR,Name VARCHAR, Sex VARCHAR, Breed VARCHAR, DOB VARCHAR, Age VARCHAR, Joined_when VARCHAR, How_obtained VARCHAR, DamTagNumber VARCHAR, SireNameTag VARCHAR);");

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioMale:
                        // do operations specific to this selection
                         radioTxt = "Male";
                        break;
                    case R.id.radioFemale:
                        // do operations specific to this selection
                        radioTxt = "Female";
                        break;

                }
            }
        });



        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);



        final DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                        // TODO Auto-generated method stub
                        // arg1 = year
                        // arg2 = month
                        // arg3 = day


                        dobPicker = arg3+"/"+arg2+"/"+arg1;
                        addBOD.setText(dobPicker);
                    }
                };

        addBOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddCow.this, myDateListener, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        AddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                sqLiteDatabase.execSQL("INSERT INTO Cow_Details VALUES ('" + addTagNum.getText().toString() + "','" + addName.getText().toString() + "','" +
                        radioTxt + "','" + addBreed.getText().toString() + "','" +dobPicker + "','" + addAge.getText().toString() + "','" +
                        addJoinedDate.getText().toString() + "','" + addHowObt.getText().toString() + "','" + addDamTagNum.getText().toString() + "','" + addSireNameTag.getText().toString() + "')");



                Toast.makeText(AddCow.this, "Record Added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddCow.this,Home.class);
                startActivity(i);

            }
        });


    }
}
