package com.example.harjoitustyo_uusiyritys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StartFragment extends Fragment {
    Button loginButton;
    Button registerButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*loginButton = view.findViewById(R.id.button_login);
        registerButton = view.findViewById(R.id.button_register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getChildFragmentManager().beginTransaction().replace(R.id.fragment_start,
                        new LoginFragment()).commit();

            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getChildFragmentManager().beginTransaction().replace(R.id.fragment_start,
                        new RegisterFragment()).commit();

            }
        });*/



    }

    public void register(View view) {

    }
    public void signIn(View view) {

    }
}
