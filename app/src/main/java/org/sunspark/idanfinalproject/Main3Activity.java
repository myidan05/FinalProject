package org.sunspark.idanfinalproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SigninActivity extends AppCompatActivity {
    Button btnSignin, btnNotRegistered;
    private EditText ed_user,ed_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        btnNotRegistered = (Button) findViewById(R.id.btn_not_registered);
        ed_user = (EditText) findViewById(R.id.username);
        ed_pass = (EditText) findViewById(R.id.password);
        btnSignin = (Button) findViewById(R.id.btn_signin);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        btnNotRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SigninActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = ed_user.getText().toString();
                String password = ed_pass.getText().toString();
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Toast.makeText(SigninActivity.this, "Successful Login, Welcome back" + username + "!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(SigninActivity.this,MainActivity.class);
                            startActivity(i);
                        } else {
                            ParseUser.logOut();
                            Toast.makeText(SigninActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                });
            }
        });
    }
}