package com.example.cow.Uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cow.R;

public class Update extends AppCompatActivity {


    private EditText addTagNum,addName,addSex,addBOD,addBreed,addAge,addJoinedDate,addHowObt,addDamTagNum,addSireNameTag;
    private Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        addTagNum = findViewById(R.id.tagNumber);
        addName = findViewById(R.id.name);
        addSex = findViewById(R.id.sex);
        addBreed = findViewById(R.id.breed);
        addBOD = findViewById(R.id.Birthday);
        addAge = findViewById(R.id.age);
        addJoinedDate = findViewById(R.id.joinDate);
        addHowObt = findViewById(R.id.howObtain);
        addDamTagNum = findViewById(R.id.damTagNum);
        addSireNameTag = findViewById(R.id.sireNameTag);
        updateBtn = findViewById(R.id.updateBtn);


        final SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = openOrCreateDatabase("Cow_Details", Context.MODE_PRIVATE, null);

        Intent intent = getIntent();
        final String search = intent.getExtras().getString("name");

        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM Cow_Details WHERE TagNumber='" + search + "'", null);
        if (c.moveToFirst()) {


            addTagNum.setText(search);
            addName.setText(c.getString(1));
            addSex.setText(c.getString(2));
            addBreed.setText(c.getString(3));
            addBOD.setText(c.getString(4));
            addAge.setText(c.getString(5));
            addJoinedDate.setText(c.getString(6));
            addHowObt.setText(c.getString(7));
            addDamTagNum.setText(c.getString(8));
            addSireNameTag.setText(c.getString(9));


            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqLiteDatabase.execSQL("UPDATE Cow_Details SET TagNumber='"+addTagNum.getText().toString()+"',Name='"+addName.getText().toString()+"',Sex='"
                            +addSex.getText().toString()+"',Breed='"+addBreed.getText().toString()+"',DOB='"+addBOD.getText().toString()+"',Age='"+addAge.getText().toString()+"',Joined_when='"+
                            addJoinedDate.getText().toString()+"',How_obtained='"+addHowObt.getText().toString()+"',DamTagNumber='"+addDamTagNum.getText().toString()+"',SireNameTag='"+addSireNameTag.getText().toString()+"'");


                    Toast.makeText(Update.this, "Record Updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Update.this, Home.class);
                    startActivity(i);

                }
            });
        }
    }
}
