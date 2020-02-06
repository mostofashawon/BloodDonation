package com.blooddonation.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blooddonation.Presenter.DonerGroup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText LoginEmail, LoginPassword;
    private Button LoginButton;
    private TextView SignUp_Text;
    private Intent LoginIntent;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();

        LoginEmail = (EditText) findViewById(R.id.LoginEmailId);
        LoginPassword = (EditText) findViewById(R.id.LoginPassId);
        LoginButton = (Button) findViewById(R.id.LoginButtonId);
        SignUp_Text = (TextView) findViewById(R.id.LoginTextId2);
        progressBar = findViewById(R.id.progressBar);

        // Add_Listner

        LoginButton.setOnClickListener(this);
        SignUp_Text.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        progressBar.setVisibility(View.VISIBLE);

        if (v.getId() == R.id.LoginButtonId) {

            CheekValidation();
            StartLogin();
        }

        if (v.getId() == R.id.LoginTextId2) {

            progressBar.setVisibility(View.GONE);
            LoginIntent = new Intent(Login.this, Confirmation.class);
            startActivity(LoginIntent);
            finish();
        }
    }

    private void CheekValidation() {

        progressBar.setVisibility(View.GONE);
        String Get_LoginEmail = LoginEmail.getText().toString().trim();
        String Get_LoginPassword = LoginPassword.getText().toString().trim();

        if (Get_LoginEmail.isEmpty() && Get_LoginPassword.isEmpty()) {
            LoginEmail.setError("Enter Email");
            LoginEmail.requestFocus();
            LoginPassword.setError("Enter Password");
            LoginPassword.requestFocus();
        }

        if (Get_LoginEmail.isEmpty() || Get_LoginPassword.isEmpty()) {

            Toast.makeText(Login.this, "Please fill all the feild", Toast.LENGTH_LONG).show();
        }

    }

    private void StartLogin() {

        String Get_LoginEmail = LoginEmail.getText().toString().trim();
        String Get_LoginPassword = LoginPassword.getText().toString().trim();

        if (!Get_LoginEmail.isEmpty() && !Get_LoginPassword.isEmpty()) {

            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(Get_LoginEmail, Get_LoginPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                if (mAuth.getCurrentUser().isEmailVerified()) {

                                    progressBar.setVisibility(View.GONE);
                                    LoginIntent = new Intent(Login.this, DonerGroup.class);
                                    startActivity(LoginIntent);
                                    finish();

                                } else {

                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(Login.this, "Please verify your email", Toast.LENGTH_LONG).show();
                                }

                            } else {

                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Login.this, "Enter Correct Email & Password", Toast.LENGTH_LONG).show();

                            }
                        }


                    });

        }

    }

}
