package com.example.harjoitustyo_uusiyritys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.*;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.signup).setOnClickListener(this);
        findViewById(R.id.buttonBackToSignIn).setOnClickListener(this);
    }

    private void registerUser(){
        EditText editTextAccount = findViewById(R.id.editTextAccountR);
        EditText editTextAge = findViewById(R.id.editTextAgeR);
        EditText editTextCity = findViewById(R.id.editTextCityR);
        EditText editTextEmail = findViewById(R.id.editTextEmailR);
        EditText editTextPassword = findViewById(R.id.editTextPasswordR);
        EditText editTextPasswordAgain = findViewById(R.id.editTextPasswordAgainR);
        EditText editTextHeight = findViewById(R.id.editTextHeightR);
        EditText editTextWeight = findViewById(R.id.editTextWeightR);
        EditText editTextBirth = findViewById(R.id.editTextBirthR);
        EditText editTextSex = findViewById(R.id.editTextSexR);
        String sAccount = editTextAccount.getText().toString();
        String sCity = editTextCity.getText().toString();
        String sEmail = editTextEmail.getText().toString();
        String sPassword = editTextPassword.getText().toString();
        int iAge = 0;
        float fWeight = 0;
        int iHeight = 0;
        String sPasswordAgain = editTextPasswordAgain.getText().toString();
        String sBirthday = editTextBirth.getText().toString();
        String sSex = editTextSex.getText().toString();

        PasswordValid ps = new PasswordValid();
        if (sAccount.equals("")) {
            editTextAccount.setError("Käyttäjätunnus ei kelpaa");
            return;
        }
        try {
            iAge = Integer.parseInt(editTextAge.getText().toString());
        }catch (NumberFormatException e) {
            editTextAccount.setError("Anna ikä numerona");
            return;
        }
        if (sCity.equals("")) {
            editTextCity.setError("Kaupunki ei kelpaa");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            editTextEmail.setError("Sähköposti ei kelpaa");
            return;
        }
        if (!ps.isValid(sPassword)) {
            editTextPassword.setError("Salasana ei kelpaa");
            return;
        }
        if (!sPassword.equals(sPasswordAgain)) {
            editTextPasswordAgain.setError("Salasanat eivät ole samanlaiset");
            return;
        }
        try {
            iHeight = Integer.parseInt(editTextHeight.getText().toString());
        }catch (NumberFormatException e) {
            editTextHeight.setError("Anna pituus numerona");
            return;
        }
        try {
            fWeight = Float.parseFloat(editTextWeight.getText().toString());
        }catch (NumberFormatException e) {
            editTextWeight.setError("Anna paino numerona");
            return;
        }

        if (sBirthday.equals("")){
            editTextBirth.setError("Syntymäaika ei kelpaa");
            return;
        }



        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Käyttäjän luominen onnistui", Toast.LENGTH_SHORT);
                    toast.show();

                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("users").child(user_id);
                    Map newPost = new HashMap();
                    newPost.put("Account", sAccount);
                    //newPost.put("age", iAge);
                    newPost.put("City", sCity);
                    newPost.put("Email", sEmail);
                    newPost.put("Password", sPassword);
                    //newPost.put("Height", iHeight);
                    //newPost.put("Weight", fWeight);
                    newPost.put("Birth", sBirthday);
                    newPost.put("Male", sSex);
                    currentUser.setValue(newPost);


                    Intent intent = new Intent (SignUpActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Sähköpostilla on jo käyttäjä", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.signup:
                registerUser();
                break;

            case R.id.buttonBackToSignIn:
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                // this.finish();
                //super.onBackPressed();
                break;

        }

    }


}