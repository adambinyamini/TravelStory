package com.example.ghost.travreldiary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ghost.travreldiary.Models.BackgroundTask;
import com.example.ghost.travreldiary.Models.UserSP;
import com.example.ghost.travreldiary.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout etEmail,etPassword;
    private UserSP userSP;
    private TextView txt_loginFiled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        etEmail = (TextInputLayout) findViewById(R.id.et_Email);
        etPassword = (TextInputLayout) findViewById(R.id.et_Password);
        txt_loginFiled = (TextView)findViewById(R.id.txt_loginFiled);
        userSP = new UserSP(this);

    }

    public void logIn_click(View view) {
        if(!validateEmail()|!validatePassword()){
            return;
        }
        String login_name = etEmail.getEditText().getText().toString();
        String login_pass = etPassword.getEditText().getText().toString();
        userSP.setName(login_name);
        String method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,login_name,login_pass);
        txt_loginFiled.setText("Login Failed..... Try Again");



    }
    public void signUp_click(View view) {

        Intent intent  = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public boolean validateEmail(){
        String emailInput = etEmail.getEditText().getText().toString().trim();
        if(emailInput.isEmpty()){
            etEmail.setError("Filed can't be empty");
            return false;
        }else {
            etEmail.setError(null);
            return true;
        }

    }

    public boolean validatePassword() {
        String passwordInput = etPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            etPassword.setError("Filed can't be empty");
            return false;
        } else {
            etPassword.setError(null);
            return true;
        }
    }}
