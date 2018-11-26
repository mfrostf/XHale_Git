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
    String contactPattern = "\\d{11}";
    String bdayPattern = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";

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
        String[] birthday = PatientAccount.patientAccount.getBirthdate().split("/");
        String[] dateOfRegistration = PatientAccount.patientAccount.getDateOfRegistration().split("/");
        lblFullName.setText(PatientAccount.patientAccount.getLastName()+", "+PatientAccount.patientAccount.getFirstName()+" "+PatientAccount.patientAccount.getMiddleName().charAt(0)+".");
        txtUsername.setText(PatientAccount.patientAccount.getUsername());
        txtRegistrationDate.setText(Computation.getMonth(Integer.parseInt(dateOfRegistration[1]))+" "+dateOfRegistration[0]+", "+dateOfRegistration[2]);
        txtAddress.setText(PatientAccount.patientAccount.getAddress());
        txtContact.setText(PatientAccount.patientAccount.getContact());
        txtBday.setText(Computation.getMonth(Integer.parseInt(birthday[1]))+" "+birthday[0]+", "+birthday[2]);
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
            txtBday.setText(Computation.dateFormat(txtBday.getText().toString()));
            cmdEdit.setText("Save");
        }else {
            if(Check()) {
                if(ValidatePattern()) {
                    patientAccount = PatientAccount.patientAccount;
                    patientAccount.setAddress(txtAddress.getText().toString());
                    patientAccount.setBirthdate(txtBday.getText().toString());
                    patientAccount.setContact(txtContact.getText().toString());
                    Call<Boolean> call = RetrofitClient.getInstance().getAPI().UpdatePatient(patientAccount);
                    call.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (response.body()) {
                                Toast.makeText(AcctSettings.this, "Info Updated.", Toast.LENGTH_SHORT).show();
                                cmdEdit.setText("Edit");
                                PatientAccount.patientAccount = patientAccount;
                                setFocusable();
                                setVariables();
                            } else {
                                Toast.makeText(AcctSettings.this, "There was a problem with the server.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                        }
                    });
                }
            }else{
                Toast.makeText(this,"Fields cannot be null.",Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean ValidatePattern(){
        if (!txtContact.getText().toString().matches(contactPattern)) {
            Toast.makeText(this, "Incorrect pattern for Contact. ex. Contact: 09123456789.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!txtBday.getText().toString().matches(bdayPattern)) {
            Toast.makeText(this, "Incorrect pattern for Birthdate. ex. Birthdate: dd/mm/yyyy.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean Check() {
        if(!txtAddress.getText().toString().isEmpty()&&!txtAddress.getText().toString().equals(" ")&&!txtContact.getText().toString().isEmpty()&&!txtContact.getText().toString().equals(" ")&&!txtBday.getText().toString().isEmpty()&&!txtBday.getText().toString().equals(" ")){
            return true;
        }
        return false;
    }
}
