package com.example.teamonce.xhale;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teamonce.xhale.Data.RetrofitClient;
import com.example.teamonce.xhale.Model.PatientAccount;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPatient  extends BaseDrawerActivityDoctor {
    EditText txtFirstName,txtMiddleName,txtLastName,txtAddress,txtContact,txtBday;
    RadioButton rMale;
    Spinner copdLevel;
    String username;
    String contactPattern = "\\d{11}";
    String bdayPattern = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_addpatient, frameLayout);
        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtMiddleName = (EditText) findViewById(R.id.txtMiddleName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtContact = (EditText) findViewById(R.id.txtContact);
        txtBday = (EditText) findViewById(R.id.txtBday);
        rMale = (RadioButton) findViewById(R.id.radioMale);
        copdLevel = (Spinner) findViewById(R.id.spinnerCopdLevel);

    }

    public void cmdAdd(View view){
        if(Check()){
            if(ValidatePattern()) {
                username = txtFirstName.getText().toString().charAt(0) + "" + txtMiddleName.getText().toString().charAt(0) + "" + txtLastName.getText().toString();
                String gender = "";
                if (rMale.isChecked())
                    gender = "Male";
                else
                    gender = "Female";
                SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date getDate = new Date();
                String dateStr = timeStampFormat.format(getDate);
                PatientAccount patientAccount = new PatientAccount(1, username.toLowerCase(), "changeme", txtFirstName.getText().toString(), txtMiddleName.getText().toString(), txtLastName.getText().toString(), txtContact.getText().toString(), "PATIENT", 1, 1, dateStr, gender, txtBday.getText().toString(), txtAddress.getText().toString(), copdLevel.getSelectedItem().toString());
                Call<Boolean> call = RetrofitClient.getInstance().getAPI().AddPatient(patientAccount);
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.body()) {
                            Toast.makeText(AddPatient.this, "Patient Added. Username: " + username.toLowerCase() + " Password: changeme.", Toast.LENGTH_LONG).show();
                            txtFirstName.getText().clear();
                            txtMiddleName.getText().clear();
                            txtLastName.getText().clear();
                            txtAddress.getText().clear();
                            txtContact.getText().clear();
                            txtBday.getText().clear();
                            copdLevel.setSelection(0);
                            rMale.setChecked(true);
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            }
        }else{
            Toast.makeText(this,"Fill the required fields.",Toast.LENGTH_LONG).show();
        }
    }

    public boolean ValidatePattern(){
        if (!txtContact.getText().toString().matches(contactPattern)) {
            Toast.makeText(this, "Incorrect pattern for Contact. ex. Contact: 09123456789 or +639123456789.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!txtBday.getText().toString().matches(bdayPattern)) {
            Toast.makeText(this, "Incorrect pattern for Birthdate. ex. Birthdate: dd/mm/yyyy.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean Check(){
        if(!txtFirstName.getText().toString().isEmpty()&&!txtFirstName.getText().toString().equals(" ")&&!txtMiddleName.getText().toString().isEmpty()&&!txtMiddleName.getText().toString().equals(" ")&&!txtLastName.getText().toString().isEmpty()&&!txtLastName.getText().toString().equals(" ")&&!txtAddress.getText().toString().isEmpty()&&!txtAddress.getText().toString().equals(" ")&&!txtContact.getText().toString().isEmpty()&&!txtContact.getText().toString().equals(" ")&&!txtBday.getText().toString().isEmpty()&&!txtBday.getText().toString().equals(" ")){
            return true;
        }
        return false;
    }
}
