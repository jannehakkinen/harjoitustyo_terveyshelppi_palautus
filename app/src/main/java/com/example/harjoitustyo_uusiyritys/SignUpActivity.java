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

        EditText editTextEmail = findViewById(R.id.editTextEmailR);
        EditText editTextPassword = findViewById(R.id.editTextPasswordR);


        String sEmail = editTextEmail.getText().toString();
        String sPassword = editTextPassword.getText().toString();

        PasswordValid ps = new PasswordValid();


        if(!Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            editTextEmail.setError("S??hk??posti ei kelpaa");
            return;
        }
        if (!ps.isValid(sPassword)) {
            editTextPassword.setError("Salasana ei kelpaa");
            return;
        }

        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "K??ytt??j??n luominen onnistui", Toast.LENGTH_SHORT);
                    toast.show();

                    Intent intent = new Intent (SignUpActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "S??hk??postilla on jo k??ytt??j??", Toast.LENGTH_SHORT).show();
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