package com.example.harjoitustyo_uusiyritys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("moi käyttäjäHomeFragment");
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TextView text = view.findViewById(R.id.editTextAccount);
        TextView text2 = view.findViewById(R.id.editTextAge);
        TextView text3 = view.findViewById(R.id.editTextCity);
        TextView text4 = view.findViewById(R.id.editTextEmail);
        TextView text5 = view.findViewById(R.id.editTextPassword);
        TextView text6 = view.findViewById(R.id.editTextPasswordAgain);
        TextView text7 = view.findViewById(R.id.editTextHeight);
        TextView text8 = view.findViewById(R.id.editTextWeight);
        TextView text9 = view.findViewById(R.id.editTextBirth);

        try {
            String[] arg = getArguments().getStringArray("userinfo");
            System.out.println(arg[0]); //account
            text.setText(arg[0]);
            text2.setText(arg[1]);
            text3.setText(arg[2]);
            text4.setText(arg[3]);
            text5.setText(arg[4]);
            text6.setText(arg[4]);
            text7.setText(arg[5]);
            text8.setText(arg[6]);
            text9.setText(arg[7]);

        } catch (NullPointerException e) {

            text.setText("ei käyttäjätietoja");
        }
    }
}
