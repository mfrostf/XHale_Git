package com.example.teamonce.xhale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AddPatient  extends BaseDrawerActivityDoctor {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_addpatient, frameLayout);

    }
}
