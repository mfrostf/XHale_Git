package com.example.teamonce.xhale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.teamonce.xhale.Model.PatientAccount;

public class HomePatient extends AppCompatActivity {
    TextView txtFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepatient);

        txtFullName = (TextView) findViewById(R.id.lblFullName);
        txtFullName.setText(PatientAccount.patientAccount.LastName+", "+PatientAccount.patientAccount.FirstName+" "+PatientAccount.patientAccount.MiddleName.charAt(0)+".");
    }
}
