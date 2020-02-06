package com.blooddonation.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blooddonation.Model.SignUp;

import java.util.concurrent.atomic.AtomicReference;

public class Confirmation extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup DecisionGroup;
    private Button FinalButton;
    private Intent ConfirmIntent;
    private int Result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);

        DecisionGroup = findViewById(R.id.RadioGroupId);
        FinalButton = findViewById(R.id.ConfirmButtonId);

        FinalButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Result = DecisionGroup.getCheckedRadioButtonId();

        if (Result == R.id.YesButtonId) {

            ConfirmIntent = new Intent(Confirmation.this, SignUp.class);
            startActivity(ConfirmIntent);
            finish();
        }

        if (Result == R.id.NoButtonId) {

            ConfirmIntent = new Intent(Confirmation.this, Login.class);
            startActivity(ConfirmIntent);
            finish();

        }

    }
}
