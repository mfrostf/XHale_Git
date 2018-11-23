package com.example.teamonce.xhale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamonce.xhale.Data.RetrofitClient;
import com.example.teamonce.xhale.Model.DoctorAccount;
import com.example.teamonce.xhale.Model.PatientAccount;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorSettings extends BaseDrawerActivityDoctor {
    TextView lblFullName;
    EditText txtUsername,txtEmail,txtContact;
    Button cmdEdit;
    DoctorAccount doctorAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_doctorsettings, frameLayout);

        lblFullName = (TextView) findViewById(R.id.lblFullName);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtContact = (EditText) findViewById(R.id.txtContact);
        cmdEdit = (Button) findViewById(R.id.btnUpdateInfo);

        setFocusable();
        setVariables();
    }

    public void setVariables(){
        lblFullName.setText("Dr. "+DoctorAccount.doctorAccount.getLastName()+", "+DoctorAccount.doctorAccount.getFirstName()+" "+DoctorAccount.doctorAccount.getMiddleName().charAt(0)+".");
        txtUsername.setText(DoctorAccount.doctorAccount.getUsername());
        txtContact.setText(DoctorAccount.doctorAccount.getContact());
        txtEmail.setText(DoctorAccount.doctorAccount.getEmail());
    }

    public void setFocusable(){
        txtUsername.setFocusable(false);
        txtContact.setFocusable(false);
        txtEmail.setFocusable(false);
    }

    public void cmdChangePassword(View v){
        Intent i = new Intent(DoctorSettings.this, ChangePassword.class);
        startActivity(i);
    }

    public void cmdEdit(View view){
        if(cmdEdit.getText().toString().equals("Edit")){
            txtContact.setFocusableInTouchMode(true);
            txtEmail.setFocusableInTouchMode(true);
            cmdEdit.setFocusableInTouchMode(true);
            cmdEdit.setText("Save");
        }else {
            doctorAccount = DoctorAccount.doctorAccount;
            doctorAccount.setContact(txtContact.getText().toString());
            doctorAccount.setEmail(txtEmail.getText().toString());
            Call<Boolean> call = RetrofitClient.getInstance().getAPI().UpdateDoctor(doctorAccount);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if(response.body()){
                        Toast.makeText(DoctorSettings.this, "Info Updated.", Toast.LENGTH_SHORT).show();
                        cmdEdit.setText("Edit");
                        DoctorAccount.doctorAccount = doctorAccount;
                        setFocusable();
                        setVariables();
                    }else{
                        Toast.makeText(DoctorSettings.this, "There was a problem with the server.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {

                }
            });
        }
    }
}
