package com.example.harjoitustyo_uusiyritys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        EditText editTextAccount = view.findViewById(R.id.editTextAccount);
        EditText editTextAge = view.findViewById(R.id.editTextAge);
        EditText editTextCity = view.findViewById(R.id.editTextCity);
        EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        EditText editTextPassword = view.findViewById(R.id.editTextPassword);
        EditText editTextPasswordAgain = view.findViewById(R.id.editTextPasswordAgain);
        EditText editTextHeight = view.findViewById(R.id.editTextHeight);
        EditText editTextWeight = view.findViewById(R.id.editTextWeight);
        EditText editTextBirth = view.findViewById(R.id.editTextBirth);
        //EditText editTextSex = view.findViewById(R.id.editTextSex);
        Switch switchSex = view.findViewById(R.id.switchSex);

        try {
            String[] arg = getArguments().getStringArray("userinfo");
            System.out.println(arg[0]); //account
            editTextAccount.setText(arg[0]);
            editTextAge.setText(arg[1]);
            editTextCity.setText(arg[2]);
            editTextEmail.setText(arg[3]);
            //editTextPassword.setText(arg[4]);
            //editTextPasswordAgain.setText(arg[4]);
            editTextHeight.setText(arg[5]);
            editTextWeight.setText(arg[6]);
            editTextBirth.setText(arg[7]);
            //editTextSex.setText(arg[8]);
            if(Boolean.parseBoolean(arg[8]) == true) {
                switchSex.setChecked(true);
            }else{
                switchSex.setChecked(false);
            }

        } catch (NullPointerException e) {

            editTextEmail.setText("ei käyttäjätietoja");
        }
    }
}
