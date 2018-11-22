package com.example.teamonce.xhale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamonce.xhale.Data.RetrofitClient;
import com.example.teamonce.xhale.Model.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {
    EditText txtOldPass,txtNewPass,txtConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);

        txtOldPass = (EditText) findViewById(R.id.txtOldPass);
        txtNewPass = (EditText) findViewById(R.id.txtNewPass);
        txtConfirmPass = (EditText) findViewById(R.id.txtConfirmNewPass);
    }

    public void cmdConfirm(View v){
        if(Check()){
            if(txtNewPass.getText().toString().equals(txtConfirmPass.getText().toString())){
                Call<Boolean> call = RetrofitClient.getInstance().getAPI().ChangePassword(Account.account.ID,txtOldPass.getText().toString(),txtNewPass.getText().toString());
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.body()){
                            Toast.makeText(ChangePassword.this,"Password Changed.", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            }else{
                Toast.makeText(this,"Passwords do not match.", Toast.LENGTH_LONG).show();
            }
        }else
            Toast.makeText(this,"Fill the required fields.",Toast.LENGTH_LONG).show();
    }

    public boolean Check(){
        if(!txtOldPass.getText().toString().isEmpty()&&!txtOldPass.getText().toString().equals(" ")&&!txtNewPass.getText().toString().isEmpty()&&!txtNewPass.getText().toString().equals(" ")&&!txtConfirmPass.getText().toString().isEmpty()&&!txtConfirmPass.getText().toString().equals(" ")){
            return true;
        }
        return false;
    }

    public void cmdCancel(View view){
        finish();
    }
}
