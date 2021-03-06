package com.example.teamonce.xhale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.teamonce.xhale.Data.RetrofitClient;
import com.example.teamonce.xhale.Model.Account;
import com.example.teamonce.xhale.Model.DoctorAccount;
import com.example.teamonce.xhale.Model.PatientAccount;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeDoctor extends BaseDrawerActivityDoctor {
    TextView txtDoctorName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_homedoctor, frameLayout);

        txtDoctorName = (TextView) findViewById(R.id.lblDoctorName);
        txtDoctorName.setText("Dr. "+DoctorAccount.doctorAccount.getLastName()+", "+DoctorAccount.doctorAccount.getFirstName()+" "+DoctorAccount.doctorAccount.getMiddleName().charAt(0));
        //GetAccount();
    }

    public void cmdAddPatient(View view){
        Intent i = new Intent(HomeDoctor.this, AddPatient.class);
        startActivity(i);
    }

    public void cmdViewListPatients(View view){
        Intent i = new Intent(HomeDoctor.this, ListOfPatient.class);
        startActivity(i);
    }

    public void GetAccount(){
        Call<DoctorAccount> call = RetrofitClient.getInstance().getAPI().GetDoctorLogin(Account.account.ID);
        call.enqueue(new Callback<DoctorAccount>() {
            @Override
            public void onResponse(Call<DoctorAccount> call, Response<DoctorAccount> response) {
                DoctorAccount.doctorAccount = response.body();
                txtDoctorName.setText("Dr. "+DoctorAccount.doctorAccount.LastName+", "+DoctorAccount.doctorAccount.FirstName);
            }

            @Override
            public void onFailure(Call<DoctorAccount> call, Throwable t) {

            }
        });
    }
}
