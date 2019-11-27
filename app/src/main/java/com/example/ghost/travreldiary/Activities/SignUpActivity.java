package com.example.ghost.travreldiary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import com.example.ghost.travreldiary.Models.BackgroundTask;
import com.example.ghost.travreldiary.Models.User;
import com.example.ghost.travreldiary.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^"+
            "(?=.*[a-zA-Z])" +
           // "(?=.*[@#$%^&+=])" +
            "(?=\\S+$)" +
            ".{4,}" +
            "$");
            ;

    TextInputLayout  editFullName,editEmail,editPassword;
    RadioButton radioButtonMale;
    Button btnCreateUser;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        editFullName = (TextInputLayout) findViewById(R.id.editFullName);
        editEmail = (TextInputLayout) findViewById(R.id.editEmail);
        editPassword = (TextInputLayout) findViewById(R.id.editPassword);
        radioButtonMale = (RadioButton) findViewById(R.id.radioBtnMale);
        btnCreateUser = (Button) findViewById(R.id.createUser);

    }


        public void click_confirm (View view){

        if (!validateEmail()|!validatePassword()|!validateName()){
          return;
        }


            String fullName = editFullName.getEditText().getText().toString();
            String email = editEmail.getEditText().getText().toString();
            String password = editPassword.getEditText().getText().toString();
            String gender;
            if (radioButtonMale.isChecked()) {
                gender = "Male";
            } else gender = "Female";

            user = new User(fullName, email, password, gender);

            String type ="register";

            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(type,fullName,email,password);

            //Intent intent = new Intent(this, MyTripsActivity.class);
            //startActivity(intent);


        }

    public boolean validateName(){
        String nameInput = editFullName.getEditText().getText().toString().trim();
        if(nameInput.isEmpty()){
            editFullName.setError("Filed can't be empty");
            return false;
        }else {
            editFullName.setError(null);
            return true;
        }

    }

        public boolean validateEmail(){
            String emailInput = editEmail.getEditText().getText().toString().trim();
            if(emailInput.isEmpty()){
                editEmail.setError("Filed can't be empty");
                return false;
            }else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
                editEmail.setError("Please enter a valid email address");
                return false;
            }else {
                editEmail.setError(null);
                return true;
            }

        }

    public boolean validatePassword(){
        String passwordInput = editPassword.getEditText().getText().toString().trim();
        if(passwordInput.isEmpty()){
            editPassword.setError("Filed can't be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            editPassword.setError("Password too weak");
            return false;
        } else
         {
            editPassword.setError(null);
            return true;
        }

    }
    }