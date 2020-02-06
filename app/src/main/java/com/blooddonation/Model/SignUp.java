package com.blooddonation.Model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.blooddonation.Main.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText SignUp_Email, SignUp_Password, User_Name, User_Phone;
    private Spinner User_BloodGroup, User_Division;
    private Button SignUP_Button;
    private ArrayAdapter<String> SignUpBloodGroup_ArrayAdapter, SignUpDivision_ArrayAdapter;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mAuth = FirebaseAuth.getInstance();

        SignUp_Email = (EditText) findViewById(R.id.SignUpEmailId);
        SignUp_Password = (EditText) findViewById(R.id.SignUpPassId);
        User_Name = (EditText) findViewById(R.id.UsernameId);
        User_Phone = (EditText) findViewById(R.id.UserphoneId);
        progressBar = findViewById(R.id.progressBar2);

        User_BloodGroup = findViewById(R.id.UserBloodId);
        User_Division = findViewById(R.id.UserdivisionId);

        SignUP_Button = (Button) findViewById(R.id.SignUpButtonId);

        String[] GetBloodGroup = getResources().getStringArray(R.array.BloodGroup);
        String[] GetDivision = getResources().getStringArray(R.array.Division_Name);

        // Set_Adapter

        SignUpBloodGroup_ArrayAdapter = new ArrayAdapter<String>(SignUp.this, R.layout.demo_layoutbloodgroup, R.id.demolayout1, GetBloodGroup);
        User_BloodGroup.setAdapter(SignUpBloodGroup_ArrayAdapter);

        SignUpDivision_ArrayAdapter = new ArrayAdapter<String>(SignUp.this, R.layout.demo_layoutdivision, R.id.demolayout12, GetDivision);
        User_Division.setAdapter(SignUpDivision_ArrayAdapter);


        // Add_Listner

        SignUP_Button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        progressBar.setVisibility(View.VISIBLE);
        CheekValidation();
        StartAuthentication();
    }

    // Cheek_Input_Validation

    private void CheekValidation() {

        progressBar.setVisibility(View.GONE);
        String Get_SignUpEmail = SignUp_Email.getText().toString();
        String Get_SignUpPassword = SignUp_Password.getText().toString();
        String Get_UserName = User_Name.getText().toString();
        String Get_UserPhone = User_Phone.getText().toString();

        if (Get_SignUpEmail.isEmpty()) {

            SignUp_Email.setError("Please Enter Email");
            SignUp_Email.requestFocus();

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Get_SignUpEmail).matches()) {

            SignUp_Email.setError("Email is not Valid");
            SignUp_Email.requestFocus();
        }

        if (Get_SignUpPassword.isEmpty()) {

            SignUp_Password.setError("Please Enter Password");
            SignUp_Password.requestFocus();

        }

        if (Get_SignUpPassword.length() < 6) {

            SignUp_Password.setError("Password Should Be 6 Digits at Least");
            SignUp_Password.requestFocus();

        }

        if (Get_UserName.isEmpty()) {

            User_Name.setError("Enter Your Name");
            User_Name.requestFocus();
        }

        if (Get_UserPhone.isEmpty()) {

            User_Phone.setError("Enter Your Phone Number");
            User_Phone.requestFocus();
        }

        if (Get_UserPhone.length() < 11) {

            User_Phone.setError("Phone Number Should Be 11 Digits At Least");
            User_Phone.requestFocus();
        }

        if (Get_SignUpEmail.isEmpty() && Get_SignUpPassword.isEmpty() && Get_UserName.isEmpty() && Get_UserPhone.isEmpty()) {

            Toast.makeText(SignUp.this, "Please fill all the registration feild", Toast.LENGTH_LONG).show();
        }

    }

    // User_Authentication

    private void StartAuthentication() {

        String Get_SignUpEmail = SignUp_Email.getText().toString();
        String Get_SignUpPassword = SignUp_Password.getText().toString();
        String Get_UserName = User_Name.getText().toString();
        String Get_UserPhone = User_Phone.getText().toString();

        if (!Get_SignUpEmail.isEmpty() && !Get_SignUpPassword.isEmpty() && !Get_UserName.isEmpty() && !Get_UserPhone.isEmpty()) {

            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(Get_SignUpEmail, Get_SignUpPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {


                                        if (task.isSuccessful()) {

                                            progressBar.setVisibility(View.GONE);
                                            StoreInformation();
                                            Toast.makeText(SignUp.this, "Register successful,verify email", Toast.LENGTH_LONG).show();
                                        }
                                        else {

                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(SignUp.this, "Register not successful", Toast.LENGTH_LONG).show();

                                        }

                                    }

                                });

                            } else {


                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(SignUp.this, "Already Registered", Toast.LENGTH_LONG).show();
                                } else {

                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(SignUp.this, "Error Occuer", Toast.LENGTH_LONG).show();
                                }
                            }


                        }
                    });


        }

    }

    // Save_Doner_Information


    private void StoreInformation() {

        String Get_UserName = User_Name.getText().toString();
        String Get_UserPhone = User_Phone.getText().toString();
        String Get_UserBloodGroup = User_BloodGroup.getSelectedItem().toString();
        String Get_UserDivision = User_Division.getSelectedItem().toString();


        Information donerinfo = new Information(Get_UserName, Get_UserPhone, Get_UserBloodGroup, Get_UserDivision);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User_Info");

        String Hold_Key = databaseReference.push().getKey();

        databaseReference.child(Hold_Key).setValue(donerinfo);

    }

}





