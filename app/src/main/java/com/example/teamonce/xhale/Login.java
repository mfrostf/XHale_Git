package com.example.teamonce.xhale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamonce.xhale.Data.RetrofitClient;
import com.example.teamonce.xhale.Model.Account;
import com.example.teamonce.xhale.Model.DoctorAccount;
import com.example.teamonce.xhale.Model.PatientAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }

    public void cmdLogin(View v){
        if(Check()){
            Call<Account> call = RetrofitClient.getInstance().getAPI().Login(txtUsername.getText().toString(),txtPassword.getText().toString());
            call.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    try {
                        Account account = response.body();
                        if (account != null) {
                            GetAccount(account);
                        } else {
                            Toast.makeText(Login.this, "Login Failed.", Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(Login.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                    txtUsername.getText().clear();
                    txtPassword.getText().clear();
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }else{
            Toast.makeText(this,"Fill the required fields.", Toast.LENGTH_LONG).show();
        }
    }

    public boolean Check(){
        if(!txtUsername.getText().toString().isEmpty()&&!txtUsername.getText().toString().equals(" ")&&!txtPassword.getText().toString().isEmpty()&&!txtPassword.getText().toString().equals(" ")){
            return true;
        }
        return false;
    }

    public void GetAccount(Account account){
        if(account.AccessLevel.equals("DOCTOR")){
            Call<DoctorAccount> call = RetrofitClient.getInstance().getAPI().GetDoctorLogin(account.ID);
            call.enqueue(new Callback<DoctorAccount>() {
                @Override
                public void onResponse(Call<DoctorAccount> call, Response<DoctorAccount> response) {
                    DoctorAccount.doctorAccount = response.body();
                    Intent i = new Intent(Login.this, HomeDoctor.class);
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<DoctorAccount> call, Throwable t) {

                }
            });
        }else if(account.AccessLevel.equals("PATIENT")){
            Call<PatientAccount> call = RetrofitClient.getInstance().getAPI().GetPatientLogin(account.ID);
            call.enqueue(new Callback<PatientAccount>() {
                @Override
                public void onResponse(Call<PatientAccount> call, Response<PatientAccount> response) {
                    PatientAccount.patientAccount = response.body();
                    Intent i = new Intent(Login.this, HomePatient.class);
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<PatientAccount> call, Throwable t) {

                }
            });
        }
    }
}
