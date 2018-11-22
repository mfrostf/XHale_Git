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
    EditText txtAddress,txtContact, txtBday,txtAge,txtUsername;
    TextView lblFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_acctsettings, frameLayout);
        cmdEdit = (Button) findViewById(R.id.btnUpdateInfo);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtContact = (EditText) findViewById(R.id.txtContact);
        txtBday = (EditText) findViewById(R.id.txtBday);
        txtAge = (EditText) findViewById(R.id.txtAge);
    }

    public void cmdChangePassword(View view){
        Intent i = new Intent(AcctSettings.this, ChangePassword.class);
        startActivity(i);
    }

    public void cmdEdit(View view){
        if(cmdEdit.getText().toString().equals("Edit")){
            txtAddress.setFocusable(true);
            txtContact.setFocusable(true);
            txtBday.setFocusable(true);
            cmdEdit.setText("Save");
        }else if(cmdEdit.getText().toString().equals("Save")){
            final PatientAccount patientAccount = PatientAccount.patientAccount;
            patientAccount.setAddress(txtAddress.getText().toString());
            patientAccount.setBirthdate(txtBday.getText().toString());
            patientAccount.setContact(txtContact.getText().toString());
            Call<Boolean> call = RetrofitClient.getInstance().getAPI().UpdatePatient(patientAccount);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if(response.body()){
                        Toast.makeText(AcctSettings.this, "Info Updated.", Toast.LENGTH_SHORT).show();
                        cmdEdit.setText("Edit");
                        PatientAccount.patientAccount = patientAccount;
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
