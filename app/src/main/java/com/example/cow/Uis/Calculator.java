package com.example.cow.Uis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cow.R;

public class Calculator extends AppCompatActivity {

    private EditText num1,num2;
    private Button ansBtn;
    private TextView ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        ansBtn = findViewById(R.id.ansBtn);
        ans = findViewById(R.id.ansTxtView);

        ansBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ansBtn.setVisibility(View.GONE);
                ans.setVisibility(View.VISIBLE);
                int nu1 = Integer.parseInt(num1.getText().toString());
                int nu2 = Integer.parseInt(num2.getText().toString());
                int Ans = nu1-nu2;
                String fAns = String.valueOf(Ans);
                ans.setText("Profit = "+fAns);
            }
        });

    }
}
