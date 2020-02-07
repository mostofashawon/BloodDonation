package com.blooddonation.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.blooddonation.Adapter.CustomAdapter;
import com.blooddonation.Main.Login;
import com.blooddonation.Main.R;
import com.blooddonation.Model.Information;
import com.blooddonation.Presenter.DonerGroup;
import com.blooddonation.Presenter.DonerRecord;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class DonerList extends AppCompatActivity implements View.OnClickListener {


    private Button SignOutButton;
    private ImageButton BackButton;
    private RecyclerView recyclerView;
    private Intent DonerList_Intent;
    private List<Information> GetInfo_List;
    private String Get_BloodGroup;
    private CustomAdapter customAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doner_list);

        BackButton = findViewById(R.id.BackButtonId);
        SignOutButton = findViewById(R.id.SignOutButton_Id);
        recyclerView = findViewById(R.id.RecyclerId);

        Get_BloodGroup = getIntent().getExtras().getString("BloodGroup");

        GetInfo_List = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);

        GetInfo_List = new DonerRecord().ShowList(Get_BloodGroup);

        customAdapter = new CustomAdapter(DonerList.this,GetInfo_List);
        recyclerView.setAdapter(customAdapter);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(linearLayoutManager);

        customAdapter.setOnClick(new CustomAdapter.ClickListner() {
            @Override
            public void onItemListner(int postion, View v) {

                DonerList_Intent = new Intent(DonerList.this,Message.class);
                startActivity(DonerList_Intent);
            }
        });

        BackButton.setOnClickListener(this);
        SignOutButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.BackButtonId) {

            DonerList_Intent = new Intent(DonerList.this, DonerGroup.class);
            startActivity(DonerList_Intent);
            finish();
        }

        if (v.getId() == R.id.SignOutButton_Id) {

            FirebaseAuth.getInstance().signOut();
            DonerList_Intent = new Intent(DonerList.this, Login.class);
            startActivity(DonerList_Intent);
            finish();
        }
    }
}
