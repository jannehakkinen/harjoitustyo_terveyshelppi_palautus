package com.example.harjoitustyo_uusiyritys;


import android.widget.TextView;

public class WaterConsumption {

    public String addWaterToday(float fWaterAmount, int iWaterGoal, TextView textViewWater) {
        String s = "";
        if (fWaterAmount < iWaterGoal) {
            float fwaterdifference = iWaterGoal - fWaterAmount;
            s = "Olet juonut " + (fWaterAmount / 0.25) + " lasia vettä. \nSinun täytyy juoda " + fwaterdifference + " litraa vettä päästäksesi tavoitteeseesi.";
            return s;

        } else {
            float fwaterdifference = fWaterAmount - iWaterGoal;
            s = "Onneksi olkoon, olet tavoitteessasi!\nOlet juonut " + (fWaterAmount / 0.25) + " lasia vettä. \nOlet juonut " + fwaterdifference + " litraa yli tavoitteeseesi.";
            return s;
        }

    }
}