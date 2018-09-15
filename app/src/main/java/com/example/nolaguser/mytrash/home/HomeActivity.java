package com.example.nolaguser.mytrash.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nolaguser.mytrash.R;
import com.example.nolaguser.mytrash.report.ReportActivity;
import com.example.nolaguser.mytrash.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private Button logOut;
    private Button report;

    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fAuth = FirebaseAuth.getInstance();

        logOut = (Button) findViewById(R.id.logout_home_button);
        logOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sendToLogin();
            }
        });

        report = (Button) findViewById(R.id.report_home_button);
        report.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startCamera();
            }
        });

    }

    private void startCamera() {
        startActivity(new Intent(getApplicationContext(),ReportActivity.class));
    }

    private void sendToLogin() {
        fAuth.signOut();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null)
        {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
