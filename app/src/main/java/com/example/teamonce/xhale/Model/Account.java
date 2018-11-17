package com.example.teamonce.xhale.Model;

public class Account {
    public int ID;
    public String Username;
    public String Password;
    public String FirstName;
    public String MiddleName;
    public String LastName;
    public String Contact;
    public String AccessLevel;

    public Account(int ID, String username, String password, String firstName, String middleName, String lastName, String contact, String accessLevel) {
        this.ID = ID;
        Username = username;
        Password = password;
        FirstName = firstName;
        MiddleName = middleName;
        LastName = lastName;
        Contact = contact;
        AccessLevel = accessLevel;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAccessLevel() {
        return AccessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        AccessLevel = accessLevel;
    }
}
