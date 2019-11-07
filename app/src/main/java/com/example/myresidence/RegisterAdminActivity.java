package com.example.myresidence;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myresidence.model.DatabaseHandler;
import com.example.myresidence.model.HousingOfficer;

public class RegisterAdminActivity extends AppCompatActivity {
    EditText username, password, fullName, staffID;
    TextView register;
    Button adminBtn;
    DatabaseHandler databaseHandler;
    HousingOfficer housingOfficer;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_admin);

        databaseHandler = new DatabaseHandler(RegisterAdminActivity.this);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        fullName = findViewById(R.id.fullnameEditText);
        staffID = findViewById(R.id.staffIDEditText);
        adminBtn = findViewById(R.id.btnRegister);

        validation();

        register = findViewById(R.id.lnkLogin);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(RegisterAdminActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        adminBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                register();
            }
        });
    }

    boolean isEmpty(EditText text){
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
        if(isEmpty(fullName)){
            fullName.setError("Full name is required!");
        }
        if(isEmpty(staffID)){
            staffID.setError("Staff ID is required!");
        }
    }

    public void register(){
        housingOfficer = new HousingOfficer();

        housingOfficer.setUsername(username.getText().toString().trim());
        housingOfficer.setPassword(password.getText().toString().trim());
        housingOfficer.setFullName(fullName.getText().toString().trim());
        housingOfficer.setStaffID(staffID.getText().toString().trim());
        databaseHandler.addOfficer(housingOfficer);
        Toast.makeText(this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
        intent = new Intent(RegisterAdminActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}