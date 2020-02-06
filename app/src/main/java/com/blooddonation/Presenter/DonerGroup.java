package com.blooddonation.Presenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blooddonation.Main.R;
import com.blooddonation.View.DonerList;

public class DonerGroup extends AppCompatActivity implements View.OnClickListener {

    private CardView O_Positive, O_Negative, A_Positive, A_Negative,
            B_Positive, B_Negative, AB_Positive, AB_Negative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doner_group);

        O_Positive = findViewById(R.id.O_PositiveId);
        O_Negative = findViewById(R.id.O_NegativeId);
        A_Positive = findViewById(R.id.A_positiveId);
        A_Negative = findViewById(R.id.A_NegativeId);
        B_Positive = findViewById(R.id.B_PositiveId);
        B_Negative = findViewById(R.id.B_NegativeId);
        AB_Positive = findViewById(R.id.AB_PositiveId);
        AB_Negative = findViewById(R.id.AB_NegativeId);

        // Add_Listner

        O_Positive.setOnClickListener(this);
        O_Negative.setOnClickListener(this);
        A_Positive.setOnClickListener(this);
        A_Negative.setOnClickListener(this);
        B_Positive.setOnClickListener(this);
        B_Negative.setOnClickListener(this);
        AB_Positive.setOnClickListener(this);
        AB_Negative.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent DonerGroup_Intent = new Intent(DonerGroup.this, DonerList.class);

        if (v.getId() == R.id.O_PositiveId) {

            DonerGroup_Intent.putExtra("BloodGroup", "O Positive");
            startActivity(DonerGroup_Intent);
        } else if (v.getId() == R.id.O_NegativeId) {

            DonerGroup_Intent.putExtra("BloodGroup", "O Negative");
            startActivity(DonerGroup_Intent);
        } else if (v.getId() == R.id.A_positiveId) {

            DonerGroup_Intent.putExtra("BloodGroup", "A Positive");
            startActivity(DonerGroup_Intent);
        } else if (v.getId() == R.id.A_NegativeId) {

            DonerGroup_Intent.putExtra("BloodGroup", "A Negative");
            startActivity(DonerGroup_Intent);
        } else if (v.getId() == R.id.B_PositiveId) {

            DonerGroup_Intent.putExtra("BloodGroup", "B Positive");
            startActivity(DonerGroup_Intent);
        } else if (v.getId() == R.id.B_NegativeId) {

            DonerGroup_Intent.putExtra("BloodGroup", "B Negative");
            startActivity(DonerGroup_Intent);
        } else if (v.getId() == R.id.AB_PositiveId) {

            DonerGroup_Intent.putExtra("BloodGroup", "AB Positive");
            startActivity(DonerGroup_Intent);
        } else {

            DonerGroup_Intent.putExtra("BloodGroup", "AB Negative");
            startActivity(DonerGroup_Intent);
        }

        finish();

    }
}
