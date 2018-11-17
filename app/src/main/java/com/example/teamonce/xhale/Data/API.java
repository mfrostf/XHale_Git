package com.example.teamonce.xhale.Data;

import com.example.teamonce.xhale.Model.Account;
import com.example.teamonce.xhale.Model.DoctorAccount;
import com.example.teamonce.xhale.Model.PatientAccount;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    String BASE_URL ="https://x-hale.azurewebsites.net/api/";

    @POST("Account/Login")
    Call<Account> Login(@Query("username") String username, @Query("password") String password);

    @GET("Account/GetDoctorLogin")
    Call<DoctorAccount> GetDoctorLogin(@Query("accountID") int accountID);

    @GET("Account/GetPatientLogin")
    Call<PatientAccount> GetPatientLogin(@Query("accountID") int accountID);
}
