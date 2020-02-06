package com.blooddonation.Presenter;

import com.blooddonation.Model.Information;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class DonerRecord {

     List<Information> info_list = new ArrayList<Information>();

     ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            info_list.clear();

            if (dataSnapshot != null) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Information information = snapshot.getValue(Information.class);
                    info_list.add(information);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public List<Information> ShowList(String Group) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User_Info");

        String GetBloodGroup = Group;


        if (GetBloodGroup.equals("O Positive")) {

            databaseReference.orderByChild("doner_BloodGroup").equalTo("O Positive").addValueEventListener(valueEventListener);
        }
        else if (GetBloodGroup.equals("O Negative")) {

            databaseReference.orderByChild("doner_BloodGroup").equalTo("O Negative").addValueEventListener(valueEventListener);
        }
        else if (GetBloodGroup.equals("A Positive")) {

            databaseReference.orderByChild("doner_BloodGroup").equalTo("A Positive").addValueEventListener(valueEventListener);
        }
        else if (GetBloodGroup.equals("A Negative")) {

            databaseReference.orderByChild("doner_BloodGroup").equalTo("A Negative").addValueEventListener(valueEventListener);
        }
        else if (GetBloodGroup.equals("B Positive")) {

            databaseReference.orderByChild("doner_BloodGroup").equalTo("B Positive").addValueEventListener(valueEventListener);
        }
        else if (GetBloodGroup.equals("B Negative")) {

            databaseReference.orderByChild("doner_BloodGroup").equalTo("B Negative").addValueEventListener(valueEventListener);
        }
        else if (GetBloodGroup.equals("AB Positive")) {

            databaseReference.orderByChild("doner_BloodGroup").equalTo("AB Positive").addValueEventListener(valueEventListener);
        }
        else {

            databaseReference.orderByChild("doner_BloodGroup").equalTo("AB Negative").addValueEventListener(valueEventListener);
        }

        return info_list;

    }

}
