package com.example.cow.Uis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.cow.R;
import com.example.cow.Uis.Login;
import com.example.cow.helpers.NetworkManager;
import com.example.cow.helpers.UIHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.kaopiz.kprogresshud.KProgressHUD;

public class Registration extends AppCompatActivity implements TextWatcher {

    private EditText loginId,password;
    private TextInputLayout loginTxtInput,passTxtInput;
    private Button registerBtn;
    public   boolean isLoginValid,isPassValid,isCnfPassValid;


    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginTxtInput= findViewById(R.id.loginIdTextInputLayout);
        passTxtInput= findViewById(R.id.passwordTextInputLayout);

        loginId = loginTxtInput.getEditText();
        password = passTxtInput.getEditText();

        registerBtn = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.webViewProgressBar);

        loginId.addTextChangedListener(this);
        password.addTextChangedListener(this);

        mAuth = FirebaseAuth.getInstance();

        final KProgressHUD kProgressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f);


        registerBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {


                final String loginIDSrt = loginId.getText().toString().trim();
                final String passwordStr = password.getText().toString().trim();

                if (loginIDSrt.isEmpty() || passwordStr.isEmpty()) {
                    Toast.makeText(Registration.this, "Enter Login ID and Password to Register", Toast.LENGTH_SHORT).show();
                } else {

                    kProgressHUD.show();


                    if (NetworkManager.isAvailable(Registration.this)) {


                        mAuth.createUserWithEmailAndPassword(loginIDSrt, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    kProgressHUD.dismiss();
                                    Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    Intent homeIntent = new Intent(Registration.this, Login.class);
                                    startActivity(homeIntent);

                                    kProgressHUD.dismiss();
                                } else {

                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        kProgressHUD.dismiss();
                                        Toast.makeText(Registration.this, "You are already Registered", Toast.LENGTH_SHORT).show();
                                    } else {

                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG);
                                    }
//                                Toast.makeText(Registration.this, "Some Error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        kProgressHUD.dismiss();
                        Toast.makeText(Registration.this, "No Internet Connection ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (editable.hashCode() != 0 && editable.length() > 1) {
            String enteredText = editable.toString().trim().toLowerCase();
            if (editable.hashCode() == loginId.getText().hashCode()) {
                if (enteredText.length() >= 4 && !TextUtils.isDigitsOnly(enteredText) && loginId.getText().toString().matches(emailPattern)) {
                    isLoginValid = true;
                    loginTxtInput.setErrorEnabled(false);
                }else {
                    isLoginValid = false;

                    loginTxtInput.setError("Enter Valid Email");
                    loginTxtInput.setErrorEnabled(true);
                }
            }else if (editable.hashCode() == password.getText().hashCode()) {
                if (enteredText.length() >= 6 && !TextUtils.isDigitsOnly(enteredText)) {
                    UIHelper.enableElement(registerBtn);
                    isPassValid = true;
                    passTxtInput.setErrorEnabled(false);
                    //  validateFields();
                }else {
                    isLoginValid = false;

                    passTxtInput.setError("Enter password");
                    passTxtInput.setErrorEnabled(true);
                }

            }
    }
}
}
