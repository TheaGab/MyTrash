package com.example.nolaguser.mytrash.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nolaguser.mytrash.home.HomeActivity;
import com.example.nolaguser.mytrash.R;
import com.example.nolaguser.mytrash.register.RegisterActivity;


/**
 * Created by KRN on 09/08/2018.
 */

public class LoginActivity extends AppCompatActivity implements ILoginActivityView{

    private ILoginActivityPresenter loginPresenter ;
    private ProgressDialog progressDialog;

    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;
    private TextView signUpText;
    private TextView forgotPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initLogin();

    }

    private void initLogin() {

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging in...");

        emailText = (EditText) findViewById(R.id.username_login_editText);
        passwordText = (EditText) findViewById(R.id.password_login_editText);
        loginButton = (Button) findViewById(R.id.login_login_button);
        signUpText = (TextView) findViewById(R.id.signup_login_textView);
        forgotPasswordText = (TextView) findViewById(R.id.forgotPassword_login_textView);

        loginPresenter = new LoginActivityPresenter();
        loginPresenter.attachView(this);
        loginPresenter.checkLogin();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLogin();
            }
        });
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignIn();
            }
        });
        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onClickForgotPassword();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    @Override
    public void onClickLogin() {
        String email_text = emailText.getText().toString().trim();
        String password_text = passwordText.getText().toString().trim();
        loginPresenter.setEmailAndPassword(email_text,password_text);
        loginPresenter.attemptLogin();
    }

    @Override
    public void onClickSignIn() {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

    @Override
    public void onClickForgotPassword() {

    }

    @Override
    public void showValidationError(String message) {
        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        finish();
    }

    @Override
    public void loginError(String message) {
        Toast.makeText(LoginActivity.this,"Problem: " + message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setProgressVisibility(boolean visibility) {
        if (visibility)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }

    @Override
    public void isLogin(boolean isLogin) {
        if (isLogin) {
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            finish();
        }
    }


}
