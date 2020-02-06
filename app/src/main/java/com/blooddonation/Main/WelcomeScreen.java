package com.blooddonation.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.blooddonation.Presenter.DonerGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private Intent WelcomeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        GetStart();
    }


    private void GetStart() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    Thread.sleep(2000);

                    if (user != null && user.isEmailVerified()) {

                        WelcomeIntent = new Intent(WelcomeScreen.this, DonerGroup.class);
                        startActivity(WelcomeIntent);
                        finish();
                    } else {


                        WelcomeIntent = new Intent(WelcomeScreen.this, Login.class);

                        startActivity(WelcomeIntent);
                        finish();
                    }
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "The Exception" + e, Toast.LENGTH_LONG).show();
                }
            }
        });

        thread.start();

    }

}
