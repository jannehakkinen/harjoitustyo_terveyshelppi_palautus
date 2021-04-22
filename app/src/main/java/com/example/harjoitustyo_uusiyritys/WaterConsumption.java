package com.example.harjoitustyo_uusiyritys;


import android.widget.TextView;

public class WaterConsumption {

    public void addWaterToday(float fWaterAmount, int iWaterGoal, TextView textViewWater) {

        if (fWaterAmount < iWaterGoal) {
            float fwaterdifference = iWaterGoal - fWaterAmount;
            textViewWater.setText("Olet juonut " + (fWaterAmount / 0.25) + " lasia vettä. \nSinun täytyy juoda " + fwaterdifference + " litraa vettä päästäksesi tavoitteeseesi.");

        } else {
            float fwaterdifference = fWaterAmount - iWaterGoal;
            textViewWater.setText("Onneksi olkoon, olet tavoitteessasi!\nOlet juonut " + (fWaterAmount / 0.25) + " lasia vettä. \nOlet juonut " + fwaterdifference + " litraa yli tavoitteeseesi.");
        }

    }
}