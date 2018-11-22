package com.example.teamonce.xhale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class DoctorSettings extends BaseDrawerActivityDoctor {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_doctorsettings, frameLayout);
    }

    public void cmdChangePassword(View v){
        Intent i = new Intent(DoctorSettings.this, ChangePassword.class);
        startActivity(i);
    }
}
