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

import com.example.myresidence.model.Applicant;
import com.example.myresidence.model.DatabaseHandler;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;


import java.util.List;


public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty
    @Length(min=3, max=10)
    private EditText usernameEditText;

    @NotEmpty
    @Password
    @Pattern(regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")
    private EditText password;

    @NotEmpty
    private EditText fullname;

    @NotEmpty
    @Email
    private EditText email;

    @NotEmpty
    private EditText monthlyIncome;

    private Validator validator;
    TextView login;
    private Button regBtn;
    double income;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Applicant applicant;
    DatabaseHandler databaseHandler;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        databaseHandler = new DatabaseHandler(RegisterActivity.this);

        usernameEditText = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        fullname = findViewById(R.id.fullnameEditText);
        email = findViewById(R.id.emailEditText);
        monthlyIncome = findViewById(R.id.montlyIncomeEditText);
        regBtn = findViewById(R.id.btnLogin);

        login = findViewById(R.id.lnkLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                intent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        });

        validator = new Validator(this);
        validator.setValidationListener(this);

        regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                button_onClick(v);
            }
        });

    }

    private void button_onClick(View view) {
        validator.validate();
        String username = usernameEditText.getText().toString();
        if (username.equalsIgnoreCase("pmk")) {
            usernameEditText.setError(getText(R.string.username_already_exists));
        }
    }

    @Override
    public void onValidationSucceeded() {
        applicant = new Applicant();
        applicant.setUsername(usernameEditText.getText().toString().trim());
        applicant.setPassword(password.getText().toString().trim());
        applicant.setFullName(fullname.getText().toString().trim());
        applicant.setEmail(email.getText().toString().trim());
        applicant.setMonthlyIncome(income);
        databaseHandler.addApplicant(applicant);
        Toast.makeText(this, "Successfully Registered! Please login.", Toast.LENGTH_SHORT).show();
        intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void validation(){
        if(isEmpty(usernameEditText)){
            usernameEditText.setError("Username is required!");
        }
        if(isEmpty(password)){
            password.setError("Password is required!");
        }
        if(isEmpty(fullname)){
            fullname.setError("Full name is required!");
        }
        if(isEmpty(email)){
            email.setError("Email is required!");
        } else {
            if (email.getText().toString().trim().matches(emailPattern)){
                Toast.makeText(this, "Valid Email Address!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid Email Address!",
                        Toast.LENGTH_SHORT).show();
            }
        }
        if(isEmpty(monthlyIncome)){
            monthlyIncome.setError("Monthly Income is required!");
        }
    }

    public void register(){
        if(!(monthlyIncome.getText().toString().matches(""))){
            income = Double.parseDouble(monthlyIncome.getText().toString());
        } else {
            Toast.makeText(this, "Cannot Proceed!", Toast.LENGTH_SHORT).show();
        }
        applicant = new Applicant();
        applicant.setUsername(usernameEditText.getText().toString().trim());
        applicant.setPassword(password.getText().toString().trim());
        applicant.setFullName(fullname.getText().toString().trim());
        applicant.setEmail(email.getText().toString().trim());
        applicant.setMonthlyIncome(income);
        databaseHandler.addApplicant(applicant);
        Toast.makeText(this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
        intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}

