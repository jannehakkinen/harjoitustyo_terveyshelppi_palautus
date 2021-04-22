package com.example.harjoitustyo_uusiyritys;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class ExerciseFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener{

    public EditText durationInput;
    public int iValue;
    public String currentDateString;
    Context context = null;
    TextView textViewDatePrint;
    SeekBar seekBarExercise;
    Spinner spinnerChooseWorkout;
    Button buttonChooseDate;
    Button buttonSaveExercise;
    Float userWeight;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("moi koti");
        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerChooseWorkout = view.findViewById(R.id.spinnerChooseWorkout);
        buttonChooseDate = view.findViewById(R.id.buttonChooseDate);
        textViewDatePrint = (TextView) view.findViewById(R.id.textViewDatePrint);
        buttonChooseDate.setOnClickListener(this);
        buttonSaveExercise = view.findViewById(R.id.buttonSave);
        buttonSaveExercise.setOnClickListener(this);
        durationInput = view.findViewById(R.id.EditTextDuration);
        seekBarExercise = view.findViewById(R.id.seekBarWorkout);
        context = getActivity();
        userWeight = getArguments().getFloat("userWeight");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        System.out.println("moi");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());


        textViewDatePrint.setText(currentDateString);
        this.setExercise();

    }
    public void saveExercise(View v){
        try {
            int iDuration = Integer.parseInt(durationInput.getText().toString());

            int seekBarValue = seekBarExercise.getProgress();
            Exercise exercise = new Exercise();

            if (iValue == 1) {
                exercise.insertAerobicExercise(iDuration, currentDateString, seekBarValue, context, userWeight);
            } else if (iValue == 2) {
                exercise.insertStrengthExercise(iDuration, currentDateString, seekBarValue, context, userWeight);
            } else if (iValue == 3) {
                System.out.println(iDuration + currentDateString + seekBarValue);
                exercise.insertFlexibilityExercise(iDuration, currentDateString, seekBarValue, context, userWeight);

            }
        } catch (NumberFormatException e) {

        }
    }
    public void setExercise(){

        ArrayAdapter<String> adapterExercise = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Workouts));
        adapterExercise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChooseWorkout.setAdapter(adapterExercise);

        spinnerChooseWorkout.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    iValue = 1;
                } else if (position == 2) {
                    iValue = 2;
                } else if (position == 3) {
                    iValue = 3;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonChooseDate) {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.setTargetFragment(ExerciseFragment.this, 0);
            datePicker.show(getFragmentManager(), "date picker");
        }else if (v.getId() == R.id.buttonSave){
            saveExercise(v);
            System.out.println("olemme tässä");
        }

    }
}
