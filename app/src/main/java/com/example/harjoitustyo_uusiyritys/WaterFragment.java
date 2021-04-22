package com.example.harjoitustyo_uusiyritys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WaterFragment extends Fragment implements View.OnClickListener{
    Spinner spinnerAmount;
    Spinner spinnerUnit;
    float waterAmount;
    int waterUnit;
    public EditText waterGoal;
    TextView textViewWater;
    Button buttonSaveWater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_water, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerAmount = view.findViewById(R.id.spinnerWater);
        spinnerUnit = view.findViewById(R.id.spinnerUnit);
        waterGoal = (EditText) view.findViewById(R.id.inputWaterGoal);
        textViewWater = (TextView) view.findViewById(R.id.textViewPrintWater);
        setSpinnerWater();
        setSpinnerUnit();

        buttonSaveWater = view.findViewById(R.id.buttonSaveWater);
        buttonSaveWater.setOnClickListener(this);



    }

    public void setSpinnerWater(){

        ArrayAdapter<String> adapterWater = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.WaterAmounts));
        adapterWater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAmount.setAdapter(adapterWater);
        spinnerAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                waterAmount = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void setSpinnerUnit(){

        ArrayAdapter<String> adapterUnit = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.WaterUnits));
        adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(adapterUnit);
        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                waterUnit = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    @Override
    public void onClick(View v) {
        try {
            int iWaterGoal = Integer.parseInt(waterGoal.getText().toString());


            WaterConsumption waterconsumption = new WaterConsumption();
            if (waterUnit == 0) {
                float WaterAmountinL = waterAmount / 4;
                System.out.println(WaterAmountinL);
                waterconsumption.addWaterToday(WaterAmountinL, iWaterGoal, textViewWater);
            } else if (waterUnit == 1) {
                waterconsumption.addWaterToday(waterAmount, iWaterGoal, textViewWater);
            }
        } catch (NumberFormatException e) {

        }

    }
}
