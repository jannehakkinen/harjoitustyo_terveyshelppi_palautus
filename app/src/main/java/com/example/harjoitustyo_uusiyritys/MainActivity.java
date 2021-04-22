package com.example.harjoitustyo_uusiyritys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


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

        user = new AppUser("lare66", 22, "LPR", "lare.pare", "bemmionAuto1!", 169, 120, "7.9.1999", false);
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
        Settings settings = Settings.getInstance();

        if (settings.getBLogin() == false) {
            startActivity(new Intent(this, SignInActivity.class));
        }

        if (savedInstanceState == null) {
            Fragment fragmentH = new HomeFragment();
            Bundle bundleH = new Bundle();
            bundleH.putStringArray("userinfoH", user.getList());
            fragmentH.setArguments(bundleH);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    fragmentH).commit();

        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Fragment fragmentH = new HomeFragment();
                Bundle bundleH = new Bundle();
                bundleH.putStringArray("userinfoH", user.getList());
                fragmentH.setArguments(bundleH);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragmentH).commit();
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
            case R.id.nav_logout:
                //TYHJENNÄ KÄYTTÄJÄTIEDOT JA MERKITSE ETTÄ EI KÄYTTÄJÄÄ SISÄÄNKIRJAUTUNEENA
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(this, SignInActivity.class));
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

        EditText editTextAccount = findViewById(R.id.editTextAccount);
        EditText editTextAge = findViewById(R.id.editTextAge);
        EditText editTextCity = findViewById(R.id.editTextCity);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        EditText editTextPasswordAgain = findViewById(R.id.editTextPasswordAgain);
        EditText editTextHeight = findViewById(R.id.editTextHeight);
        EditText editTextWeight = findViewById(R.id.editTextWeight);
        EditText editTextBirth = findViewById(R.id.editTextBirth);
        String sAccount = editTextAccount.getText().toString();
        String sCity = editTextCity.getText().toString();
        String sEmail = editTextEmail.getText().toString();
        String sPassword = editTextPassword.getText().toString();
        String sPasswordAgain = editTextPasswordAgain.getText().toString();
        String sBirthday = editTextBirth.getText().toString();
        boolean canUpdate = true;

        PasswordValid ps = new PasswordValid();
        if (sAccount.equals("")) {
            editTextAccount.setError("Käyttäjätunnus ei kelpaa");
            canUpdate = false;
        }
        try {
            int iAge = Integer.parseInt(editTextAge.getText().toString());
        }catch (NumberFormatException e) {
            editTextAccount.setError("Anna ikä numerona");
            canUpdate = false;
        }
        if (sCity.equals("")) {
            editTextCity.setError("Kaupunki ei kelpaa");
            canUpdate = false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            editTextEmail.setError("Sähköposti ei kelpaa");
            canUpdate = false;
        }
        if (!ps.isValid(sPassword)) {
            editTextPassword.setError("Salasana ei kelpaa");
            canUpdate = false;
        }
        if (!sPassword.equals(sPasswordAgain)) {
            editTextPasswordAgain.setError("Salasanat eivät ole samanlaiset");
            canUpdate = false;
        }
        try {
            int iHeight = Integer.parseInt(editTextHeight.getText().toString());
        }catch (NumberFormatException e) {
            editTextHeight.setError("Anna pituus numerona");
            canUpdate = false;
        }
        try {
            float iWeight = Float.parseFloat(editTextWeight.getText().toString());
        }catch (NumberFormatException e) {
            editTextWeight.setError("Anna paino numerona");
            canUpdate = false;
        }

        if (sBirthday.equals("")){
            editTextBirth.setError("Syntymäaika ei kelpaa");
            canUpdate = false;
        }
        else {
            if(canUpdate) {
                user.refreshUserinfo(editTextAccount.getText().toString(), Integer.parseInt(editTextAge.getText().toString()), editTextCity.getText().toString(),
                        editTextEmail.getText().toString(), editTextPassword.getText().toString(), Integer.parseInt(editTextHeight.getText().toString()),
                        Float.parseFloat(editTextWeight.getText().toString()), editTextBirth.getText().toString());
                userInfo = user.getList();
                Toast.makeText(getApplicationContext(), "Tiedot päivitetty!", Toast.LENGTH_LONG).show();
            }
        }
    }


}