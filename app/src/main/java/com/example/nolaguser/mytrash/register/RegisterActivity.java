package com.example.nolaguser.mytrash.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nolaguser.mytrash.R;
import com.example.nolaguser.mytrash.User;
import com.example.nolaguser.mytrash.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements IRegisterActivityView {

    private EditText email;
    private EditText fullname;
    private EditText password;
    private EditText confirmpassword;
    private ProgressDialog progressDialog;
    Button signUpButton;
    TextView loginText;

    IRegisterActivityPresenter registerPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerPresenter = new RegisterActivityPresenter(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creating Account..");

        password = (EditText) findViewById(R.id.emailadd_register_textbox);
        fullname = (EditText) findViewById(R.id.fullname_register_textbox);
        password = (EditText) findViewById(R.id.password_register_textbox);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword_register_textbox);
        email = (EditText) findViewById(R.id.emailadd_register_textbox);
        signUpButton = (Button) findViewById(R.id.signup_register_button);
        loginText = (TextView) findViewById(R.id.login_register_textView);
        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickSignUp();
            }
        });
        loginText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ onClickLogin(); }});
    }


    @Override
    public void showValidationError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setProgressVisibility(boolean visibility) {
        if (visibility) {
            progressDialog.show();
        }
        else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onClickLogin() {
        gotoLoginPage();
    }

    @Override
    public void onClickSignUp() {
        String passwordText = password.getText().toString();
        String confirmPasswordText = confirmpassword.getText().toString();
        String nameText = fullname.getText().toString();
        String emailText = email.getText().toString();

        registerPresenter.setCredentials(nameText,emailText,passwordText,confirmPasswordText);
        registerPresenter.attemptRegister();
    }

    @Override
    public void onCreateAccountSuccess() {
        gotoLoginPage();
    }

    private void gotoLoginPage(){
        Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(loginIntent);
    }


}
