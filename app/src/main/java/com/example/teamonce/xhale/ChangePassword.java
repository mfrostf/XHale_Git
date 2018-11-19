package com.example.teamonce.xhale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ChangePassword extends AppCompatActivity {
    EditText txtOldPass,txtNewPass,txtConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
    }

    public void cmdConfirm(View v){

    }
}
