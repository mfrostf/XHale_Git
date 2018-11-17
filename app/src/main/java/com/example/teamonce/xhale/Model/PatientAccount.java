package com.example.teamonce.xhale.Model;

public class PatientAccount extends Account {
    public int PatientID;
    public int AccountID;
    public String DateOfRegistration;
    public String Gender;
    public String Birthdate;
    public String Address;
    public String COPDLevel;
    public static PatientAccount patientAccount;

    public PatientAccount(int ID, String username, String password, String firstName, String middleName, String lastName, String contact, String accessLevel, int patientID, int accountID, String dateOfRegistration, String gender, String birthdate, String address, String COPDLevel) {
        super(ID, username, password, firstName, middleName, lastName, contact, accessLevel);
        PatientID = patientID;
        AccountID = accountID;
        DateOfRegistration = dateOfRegistration;
        Gender = gender;
        Birthdate = birthdate;
        Address = address;
        this.COPDLevel = COPDLevel;
    }

    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int patientID) {
        PatientID = patientID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public String getDateOfRegistration() {
        return DateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        DateOfRegistration = dateOfRegistration;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(String birthdate) {
        Birthdate = birthdate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCOPDLevel() {
        return COPDLevel;
    }

    public void setCOPDLevel(String COPDLevel) {
        this.COPDLevel = COPDLevel;
    }
}
