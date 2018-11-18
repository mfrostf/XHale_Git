package com.example.teamonce.xhale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.teamonce.xhale.Model.DoctorAccount;

import org.w3c.dom.Text;

public class HomeDoctor extends AppCompatActivity {
    TextView txtDoctorName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedoctor);

        txtDoctorName = (TextView) findViewById(R.id.lblDoctorName);
        txtDoctorName.setText("Dr. "+DoctorAccount.doctorAccount.LastName+", "+DoctorAccount.doctorAccount.FirstName);
    }
}
