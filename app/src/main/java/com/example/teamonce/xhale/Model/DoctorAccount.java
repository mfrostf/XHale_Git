package com.example.teamonce.xhale.Model;

public class DoctorAccount extends Account {
    public int DoctorID;
    public int AccountID;
    public String Email;
    public static DoctorAccount doctorAccount;

    public DoctorAccount(int ID, String username, String password, String firstName, String middleName, String lastName, String contact, String accessLevel, int doctorID, int accountID, String email) {
        super(ID, username, password, firstName, middleName, lastName, contact, accessLevel);
        DoctorID = doctorID;
        AccountID = accountID;
        Email = email;
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(int doctorID) {
        DoctorID = doctorID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
