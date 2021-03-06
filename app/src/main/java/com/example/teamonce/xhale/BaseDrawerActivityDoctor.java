package com.example.teamonce.xhale;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.teamonce.xhale.Data.RetrofitClient;
import com.example.teamonce.xhale.Model.DoctorAccount;
import com.example.teamonce.xhale.Model.PatientAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseDrawerActivityDoctor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;
    int defHeight = 566, defWidth = 383, ScreenHeight, ScreenWidth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basedrawerlayoutdoctor);

        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        navigationView = (NavigationView) findViewById(R.id.navItems);
        setDrawerParams();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(item.isChecked()){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                }
                switch (id){
                    case R.id.navD_Home : startActivity(new Intent(getApplicationContext(), HomeDoctor.class)); break;
                    case R.id.navD_Settings : startActivity(new Intent(getApplicationContext(), DoctorSettings.class)); break;
                    case R.id.navD_AddPatient : startActivity(new Intent(getApplicationContext(), AddPatient.class)); break;
                    default: break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    public void cmdLogout(View view){
        Call<Boolean> call = RetrofitClient.getInstance().getAPI().Logout(DoctorAccount.doctorAccount.ID);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                try{
                    if(response.body()){
                        DoctorAccount.doctorAccount = null;
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
                catch(Exception e){
                    Toast.makeText(BaseDrawerActivityDoctor.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(item.isChecked()){
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }
        switch (id){
//            case R.id.navigationa: startActivity(new
//                    Intent(getApplicationContext(), Home_Menu.class)); break;
//            case R.id.navigationg: startActivity(new
//                    Intent(getApplicationContext(), Settings_Menu.class)); break;
            default: break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public int PxToDp(int a){
        a = (int)(a/ Resources.getSystem().getDisplayMetrics().density);
        return a;
    }

    public int Scale(float margin, float size, float scale){
        margin = size*(margin/scale);
        margin = (int)(margin * Resources.getSystem().getDisplayMetrics().density);
        return (int)margin;
    }

    public void setDrawerParams()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ViewGroup.LayoutParams layoutParams = navigationView.getLayoutParams();
        layoutParams.width = size.x/2;
        navigationView.setLayoutParams(layoutParams);
    }

    public void NavigateD(View v){
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }


}
