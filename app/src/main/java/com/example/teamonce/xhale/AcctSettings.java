package com.example.teamonce.xhale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamonce.xhale.Data.RetrofitClient;
import com.example.teamonce.xhale.Model.PatientAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcctSettings extends BaseDrawerActivity {
    Button cmdEdit;
    EditText txtAddress,txtContact, txtBday,txtAge,txtUsername,txtGender,txtRegistrationDate;
    TextView lblFullName;
    PatientAccount patientAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_acctsettings, frameLayout);
        cmdEdit = (Button) findViewById(R.id.btnUpdateInfo);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtContact = (EditText) findViewById(R.id.txtContact);
        txtBday = (EditText) findViewById(R.id.txtBday);
        txtAge = (EditText) findViewById(R.id.txtAge);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtGender = (EditText) findViewById(R.id.txtGender);
        txtRegistrationDate = (EditText) findViewById(R.id.txtDoRegistration);
        lblFullName = (TextView) findViewById(R.id.lblFullName);
        setVariables();
        setFocusable();
    }

    public void setVariables(){
        lblFullName.setText(PatientAccount.patientAccount.getLastName()+", "+PatientAccount.patientAccount.getFirstName()+" "+PatientAccount.patientAccount.getMiddleName().charAt(0)+".");
        txtUsername.setText(PatientAccount.patientAccount.getUsername());
        txtRegistrationDate.setText(Computation.getMonth(Integer.parseInt(PatientAccount.patientAccount.getDateOfRegistration().substring(1,2)))+" "+PatientAccount.patientAccount.getDateOfRegistration().substring(0,2)+", "+PatientAccount.patientAccount.getDateOfRegistration().substring(6,10));
        txtAddress.setText(PatientAccount.patientAccount.getAddress());
        txtContact.setText(PatientAccount.patientAccount.getContact());
        txtBday.setText(Computation.getMonth(Integer.parseInt(PatientAccount.patientAccount.getBirthdate().substring(1,2)))+" "+PatientAccount.patientAccount.getBirthdate().substring(0,2)+", "+PatientAccount.patientAccount.getBirthdate().substring(6,10));
        txtAge.setText(Computation.getAge(Integer.parseInt(PatientAccount.patientAccount.getBirthdate().substring(6,10)),Integer.parseInt(PatientAccount.patientAccount.getBirthdate().substring(1,2)),Integer.parseInt(PatientAccount.patientAccount.getBirthdate().substring(1,2))) );
        txtGender.setText(PatientAccount.patientAccount.getGender());

    }

    public void setFocusable(){
        txtUsername.setFocusable(false);
        txtRegistrationDate.setFocusable(false);
        txtAddress.setFocusable(false);
        txtContact.setFocusable(false);
        txtBday.setFocusable(false);
        txtAge.setFocusable(false);
        txtGender.setFocusable(false);
    }

    public void cmdChangePassword(View view){
        Intent i = new Intent(AcctSettings.this, ChangePassword.class);
        startActivity(i);
    }

    public void cmdEdit(View view){
        if(cmdEdit.getText().toString().equals("Edit")){
            txtAddress.setFocusableInTouchMode(true);
            txtContact.setFocusableInTouchMode(true);
            txtBday.setFocusableInTouchMode(true);
            cmdEdit.setFocusableInTouchMode(true);
            cmdEdit.setFocusableInTouchMode(true);
            cmdEdit.setText("Save");
        }else {
            patientAccount = PatientAccount.patientAccount;
            patientAccount.setAddress(txtAddress.getText().toString());
            patientAccount.setBirthdate(Computation.dateFormat(txtBday.getText().toString()));
            patientAccount.setContact(txtContact.getText().toString());
            Call<Boolean> call = RetrofitClient.getInstance().getAPI().UpdatePatient(patientAccount);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if(response.body()){
                        Toast.makeText(AcctSettings.this, "Info Updated.", Toast.LENGTH_SHORT).show();
                        cmdEdit.setText("Edit");
                        PatientAccount.patientAccount = patientAccount;
                        setFocusable();
                        setVariables();
                    }else{
                        Toast.makeText(AcctSettings.this, "There was a problem with the server.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                }
            });
        }

    }
}
