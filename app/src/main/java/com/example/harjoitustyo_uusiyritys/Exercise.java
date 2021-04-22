package com.example.harjoitustyo_uusiyritys;

import android.content.Context;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Exercise {
    String sExerciseType;
    int iDurationMinutes;
    String sDate;
    int iSeekBarValue;
    float iCalories;
    float iWeight;
    String sFile = "exercisedata.txt";

    public void insertAerobicExercise(int iDuration, String currentDateString, int seekBarValue, Context context, Float userWeight){
        sExerciseType = "Aerobinen treeni";
        iDurationMinutes = iDuration;
        sDate = currentDateString;
        iSeekBarValue = seekBarValue;
        iWeight = userWeight;
        iCalories = iDurationMinutes*(4*iSeekBarValue*iWeight)/200;

        this.logExercise(sExerciseType, iDurationMinutes, sDate, iCalories, context);

    }

    public void insertStrengthExercise(int iDuration, String currentDateString, int seekBarValue, Context context, Float userWeight){
        sExerciseType = "Voimaharjoittelu";
        iDurationMinutes = iDuration;
        sDate = currentDateString;
        iSeekBarValue = seekBarValue;
        iWeight = userWeight;
        iCalories = iDurationMinutes*(4*iSeekBarValue*iWeight)/200;

        this.logExercise(sExerciseType, iDurationMinutes, sDate, iCalories, context);

        System.out.println("GYMtreeni");

    }

    public void insertFlexibilityExercise(int iDuration, String currentDateString, int seekBarValue, Context context, Float userWeight){
        sExerciseType = "Liikkuvuus harjoittelu";
        iWeight = userWeight;
        iDurationMinutes = iDuration;
        sDate = currentDateString;
        iSeekBarValue = seekBarValue;
        iCalories = iDurationMinutes*(2*iSeekBarValue*iWeight)/200;
        System.out.println("FLEXAUS");

        this.logExercise(sExerciseType, iDurationMinutes, sDate, iCalories, context);


    }

    public void logExercise(String sExerciseType, int iDurationMinutes, String sDate, float iCalories, Context context){
        try {
            OutputStreamWriter writeExercise = new OutputStreamWriter(context.openFileOutput(sFile, Context.MODE_APPEND));

            writeExercise.append("Päivämäärä: "+ sDate+"\nTreeni: "+ sExerciseType +"\nKesto: "+ iDurationMinutes+ "\nKaloreja poltettu: "+ iCalories + "\n\n");

            writeExercise.close();

            System.out.println("Tiedoston paikka:  " + context.getFilesDir());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}