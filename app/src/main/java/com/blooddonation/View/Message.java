package com.blooddonation.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.blooddonation.Main.Login;
import com.blooddonation.Main.R;
import com.google.firebase.auth.FirebaseAuth;

public class Message extends AppCompatActivity implements View.OnClickListener {


    private Button SignOutButton,SendButton;
    private ImageButton BackButton;
    private EditText Mphone,Msms;
    private Intent Message_Intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        BackButton = findViewById(R.id.BackButtonId);
        SignOutButton = findViewById(R.id.SignOutButton_Id);
        SendButton = findViewById(R.id.button);

        Mphone = findViewById(R.id.msgphone);
        Msms = findViewById(R.id.msgtext);

        BackButton.setOnClickListener(this);
        SignOutButton.setOnClickListener(this);
        SendButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.BackButtonId) {

            Message_Intent = new Intent(Message.this,DonerList.class);
            startActivity(Message_Intent);
            finish();
        }
        if (v.getId() == R.id.SignOutButton_Id) {

            FirebaseAuth.getInstance().signOut();
            Message_Intent = new Intent(Message.this,Login.class);
            startActivity(Message_Intent);
            finish();
        }

        if (v.getId() == R.id.button) {

            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

            if (permission == PackageManager.PERMISSION_GRANTED){

                SendSMS();

            }

            else {

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
            }

        }

    }



    public void SendSMS(){

        String Phone = Mphone.getText().toString().trim();
        String Sms = Msms.getText().toString().trim();

        SmsManager smsManager= SmsManager.getDefault();
        smsManager.sendTextMessage(Phone,null,Sms,null,null);
        Toast.makeText(Message.this,"Sms sent",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case 0:

                if (grantResults.length>=0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

                    SendSMS();

                break;

        }

    }
}
