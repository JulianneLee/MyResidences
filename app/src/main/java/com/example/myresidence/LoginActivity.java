package com.example.myresidence;

import android.content.Intent;
import android.os.Bundle;
import android.os.HardwarePropertiesManager;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myresidence.model.Applicant;
import com.example.myresidence.model.DatabaseHandler;
import com.example.myresidence.model.HousingOfficer;

public class LoginActivity extends AppCompatActivity {
    Applicant applicant;
    HousingOfficer officer;
    EditText username, password;
    TextView register, rAdmin;
    Button loginBtn;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        loginBtn = findViewById(R.id.btnLogin);
        databaseHandler = new DatabaseHandler(this);

//        validation();

        register = findViewById(R.id.lnkRegister);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
            }
        });

        rAdmin = findViewById(R.id.lnkAdmin);
        rAdmin.setMovementMethod(LinkMovementMethod.getInstance());
        rAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LoginActivity.this,
                        RegisterAdminActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Welcome Back, ", Toast.LENGTH_LONG).show();
                applicant = databaseHandler.getApplicant(username.getText().toString());
                officer = databaseHandler.getOfficer(username.getText().toString());

                if (username.getText().toString().equals(applicant.getUsername())) {
                    Toast.makeText(LoginActivity.this, "Welcome Back, " + username.getText().toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, ViewApplicationApplicantActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                } else if (username.getText().toString().trim().equals(officer.getUsername())) {
                    Toast.makeText(LoginActivity.this, "Welcome Back, " + username.getText().toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, ViewApplicationActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                } else if (username.getText().toString().trim().equals(officer.getStaffID())){
                    Toast.makeText(LoginActivity.this, "Welcome Back, " + username.getText().toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, ViewApplicationActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Username/Staff ID or Password!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void validation(){
        if(isEmpty(username)){
            username.setError("Username is required!");
        }
        if(isEmpty(password)){
            password.setError("Password is required!");
        }
    }

    public void checkLogin(){
        if(databaseHandler.getApplicant(username.getText().toString().trim()) == null){
            databaseHandler.getOfficer(username.getText().toString().trim());
            String officer_username = username.getText().toString().trim();
            Toast.makeText(this, "Welcome back " + officer_username, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, ViewApplicationActivity.class);
            startActivity(intent);
        } else {
            databaseHandler.getApplicant(username.getText().toString().trim());
            String applicant_username = username.getText().toString().trim();
            Toast.makeText(this, "Welcome back " + applicant_username, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, ViewApplicationActivity.class);
            startActivity(intent);
        }
    }
}