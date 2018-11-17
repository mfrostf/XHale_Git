package com.example.teamonce.xhale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class testxml extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_acctsettings, frameLayout);
        setTitle("testxml");
    }
}
