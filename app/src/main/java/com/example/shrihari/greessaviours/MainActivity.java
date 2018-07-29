package com.example.shrihari.greessaviours;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav ;
    private FrameLayout  mMainFrame ;
    private String memberName , phone ;
    private HomeFragment mHomeFragment ;
    private FragmentHomeTwo mFragmentHomeTwo ;
    private NotificationFragment mNotificationFragment ;
    private AdminFragment mAdminFragment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
        setFragment(mFragmentHomeTwo);
        clickListners();

    }

    private void clickListners() {
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 switch (item.getItemId()){
                     case R.id.nav_home :
                         setFragment(mFragmentHomeTwo);
                         return true;
                     case R.id.nav_notif:
                         setFragment(mNotificationFragment);
                         return true ;
                     case R.id.nav_admin :
                         setFragment(mAdminFragment);
                             return true ;
                             default: return  false;
                 }
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame , fragment);
        ft.commit();
    }

    private void getData() {
        mMainFrame = findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.main_nav);

        mHomeFragment = new HomeFragment();
        mNotificationFragment = new NotificationFragment();
        mAdminFragment = new AdminFragment();
        mFragmentHomeTwo = new FragmentHomeTwo();


        SharedPreferences sharedPref = getSharedPreferences("memberInfo",MODE_PRIVATE);
        memberName = sharedPref.getString("name","");
        phone = sharedPref.getString("phone","");
        Log.d("chk","name "+memberName+" phone "+phone);
        if((memberName.equals("")) || phone.equals("")){
            Log.d("chk","Registration not done");
            Toast.makeText(MainActivity.this , "Register To The GreenSavior App",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this ,Register.class);
           startActivity(intent);
        }

    }
}
