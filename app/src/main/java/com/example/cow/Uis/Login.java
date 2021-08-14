package com.example.cow.Uis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.cow.R;
import com.example.cow.Uis.Home;
import com.example.cow.helpers.NetworkManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kaopiz.kprogresshud.KProgressHUD;

public class Login extends AppCompatActivity implements TextWatcher {

    private EditText userName;
    private EditText password;
    private Button loginBtn,signInBtn;

    private TextInputLayout loginInput, passInput;
    private ProgressBar progressBar;
    private boolean isUserValid, isPassValid;


    private FirebaseAuth mAuth;
    private FirebaseUser user;








//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        Intent HomeIntent = new Intent(Login.this, Home.class);
//        startActivity(HomeIntent);
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginInput = findViewById(R.id.loginIdTextInputLayout);
        passInput = findViewById(R.id.passwordTextInputLayout);


        userName = findViewById(R.id.loginId);
        password = findViewById(R.id.password);


        progressBar = findViewById(R.id.webViewProgressBar2);
        loginBtn = findViewById(R.id.button_login);
        signInBtn = findViewById(R.id.button_SignIn);

        userName.addTextChangedListener(this);
        password.addTextChangedListener(this);
        mAuth = FirebaseAuth.getInstance();


        final KProgressHUD kProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                kProgressHUD.isShowing();
                if (NetworkManager.isAvailable(Login.this)) {


                    final String loginIDSrt = userName.getText().toString().trim();
                    final String passwordStr = password.getText().toString().trim();

                    mAuth.signInWithEmailAndPassword(loginIDSrt, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                kProgressHUD.dismiss();

                                Toast.makeText(Login.this, "Done", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Login.this, Home.class);
                                startActivity(i);
                            } else {
                                kProgressHUD.dismiss();
                                Toast.makeText(Login.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });



        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegistrationIntent = new Intent(Login.this, Registration.class);
                startActivity(RegistrationIntent);
            }
        });



    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null){
//            Intent HomeActivity = new Intent(Login.this, Home.class);
//            startActivity(HomeActivity);}else {
//            Intent RegistrationIntent = new Intent(Login.this, Registration.class);
//            startActivity(RegistrationIntent);
//
//        }
//    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
