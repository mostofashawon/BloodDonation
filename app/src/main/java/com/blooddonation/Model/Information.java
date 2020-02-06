package com.blooddonation.Model;

public class Information {

    private String Doner_Name, Doner_Phone, Doner_BloodGroup, Doner_Division;

    Information() {

    }

    Information(String name, String phone, String bloodgroup, String division) {

        Doner_Name = name;
        Doner_Phone = phone;
        Doner_BloodGroup = bloodgroup;
        Doner_Division = division;

    }

    public void setDoner_Name(String doner_Name) {
        Doner_Name = doner_Name;
    }

    public void setDoner_Phone(String doner_Phone) {
        Doner_Phone = doner_Phone;
    }

    public void setDoner_BloodGroup(String doner_BloodGroup) {
        Doner_BloodGroup = doner_BloodGroup;
    }

    public void setDoner_Division(String doner_Division) {
        Doner_Division = doner_Division;
    }

    public String getDoner_Name() {
        return Doner_Name;
    }

    public String getDoner_Phone() {
        return Doner_Phone;
    }

    public String getDoner_BloodGroup() {
        return Doner_BloodGroup;
    }

    public String getDoner_Division() {
        return Doner_Division;
    }

}
