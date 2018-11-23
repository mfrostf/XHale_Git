package com.example.teamonce.xhale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.teamonce.xhale.Data.RetrofitClient;
import com.example.teamonce.xhale.Model.Account;
import com.example.teamonce.xhale.Model.DoctorAccount;
import com.example.teamonce.xhale.Model.PatientAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePatient extends BaseDrawerActivity {
    TextView txtFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_homepatient, frameLayout);

        txtFullName = (TextView) findViewById(R.id.lblFullName);
        txtFullName.setText(PatientAccount.patientAccount.getLastName()+", "+PatientAccount.patientAccount.getFirstName()+" "+PatientAccount.patientAccount.getMiddleName().charAt(0)+".");
        //GetAccount();
    }

    public void GetAccount(){
        Call<PatientAccount> call = RetrofitClient.getInstance().getAPI().GetPatientLogin(Account.account.ID);
        call.enqueue(new Callback<PatientAccount>() {
            @Override
            public void onResponse(Call<PatientAccount> call, Response<PatientAccount> response) {
                PatientAccount.patientAccount = response.body();
                txtFullName.setText(PatientAccount.patientAccount.LastName+", "+PatientAccount.patientAccount.FirstName+" "+PatientAccount.patientAccount.MiddleName.charAt(0)+".");
            }

            @Override
            public void onFailure(Call<PatientAccount> call, Throwable t) {

                }
            });
    }
}
