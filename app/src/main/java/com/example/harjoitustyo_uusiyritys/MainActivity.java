package com.example.harjoitustyo_uusiyritys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    AppUser user;
    String[] userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        user = new AppUser("lare66", 22, "LPR", "lare.pare", "bemmionAuto1!", 169, 120, "7.9.1999");
        userInfo = user.getList();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StartFragment()).commit();

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_user:
                Fragment fragment = new UserFragment();
                Bundle bundle = new Bundle();
                bundle.putStringArray("userinfo", user.getList());
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                       fragment).commit();
                break;
            case R.id.nav_water:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WaterFragment()).commit();
                break;
            case R.id.nav_corona:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CoronaFragment()).commit();
                break;
            case R.id.nav_excercise:
                Fragment fragmentE = new ExerciseFragment();
                Bundle bundleE = new Bundle();
                bundleE.putFloat("userWeight", user.getfWeight());
                fragmentE.setArguments(bundleE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragmentE).commit();
                break;
            case R.id.nav_seeexercises:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SeeExercisesFragment()).commit();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    public void refreshUser(View view) {
        TextView text = findViewById(R.id.editText);
        TextView text2 = findViewById(R.id.editText2);
        TextView text3 = findViewById(R.id.editText3);
        TextView text4 = findViewById(R.id.editText4);
        TextView text5 = findViewById(R.id.editText5);
        TextView text6 = findViewById(R.id.editText6);
        TextView text7 = findViewById(R.id.editText7);
        TextView text8 = findViewById(R.id.editText8);
        TextView text9 = findViewById(R.id.editText9);
        TextView text10 = findViewById(R.id.textView10);
        String sAccount = text.getText().toString();
        int iAge = Integer.parseInt(text2.getText().toString());
        String sTown = text3.getText().toString();
        String sEmail = text4.getText().toString();
        String sPassword = text5.getText().toString();
        String sPasswordAgain = text6.getText().toString();
        int iHeight = Integer.parseInt(text7.getText().toString());
        float iWeight = Float.parseFloat(text8.getText().toString());
        String sBirthday = text9.getText().toString();

        PasswordValid ps = new PasswordValid();
        if (!sPassword.equals(sPasswordAgain)) {
            text10.setText("Salasanat eivät ole samanlaiset");
        } else if (!ps.isValid(sPassword)) {
            text10.setText("Salasana ei kelpaa");
        }else if (sAccount.equals("")){
            text10.setText("Käyttäjätunnus ei kelpaa");
        }else if (sEmail.equals("")){
            text10.setText("Sähköposti ei kelpaa");
        }else if (sTown.equals("")){
            text10.setText("Kaupunki ei kelpaa");
        }else if (sBirthday.equals("")){
            text10.setText("Syntymäaika ei kelpaa");
        }
        else {

            user.refreshUserinfo(text.getText().toString(), Integer.parseInt(text2.getText().toString()), text3.getText().toString(),
                    text4.getText().toString(), text5.getText().toString(), Integer.parseInt(text7.getText().toString()),
                    Float.parseFloat(text8.getText().toString()), text9.getText().toString());
            userInfo = user.getList();
            text10.setText("Tiedot päivitetty!");
        }
    }


}