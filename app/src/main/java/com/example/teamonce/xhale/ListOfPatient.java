package com.example.teamonce.xhale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ListOfPatient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofpatients);

    }

    public void cmdAddPatient(View view){
        Intent i = new Intent(ListOfPatient.this, AddPatient.class);
        startActivity(i);
    }
}
