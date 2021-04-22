package com.example.harjoitustyo_uusiyritys;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SeeExercisesFragment extends Fragment {
    Button buttonExercise;
    TextView textviewExercise;
    String sFile = "exercisedata.txt";
    String sLine = "";
    String sAllLines = "";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){



        return inflater.inflate(R.layout.fragment_seeexercises, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        buttonExercise = (Button)view.findViewById(R.id.buttonExerciseHistory);
        textviewExercise = (TextView)view.findViewById(R.id.textViewExerciseHistory);
        buttonExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textviewExercise.setText("TÄMÄ TOIMI");

                try{
                    FileInputStream data = getActivity().openFileInput(sFile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(data));
                    sAllLines = "";
                    while ((sLine = br.readLine()) != null){
                        sAllLines = sAllLines + sLine + "\n";
                        System.out.println("TÄHÄN ASTI");
                    }
                    textviewExercise.setText(sAllLines);
                } catch (IOException e) {
                    Log.e("IOException", "Virhe syötteessä");
                } finally {
                    System.out.println("KIRJOITETTU");
                }

            }
        });

    }

}
