package com.example.harjoitustyo_uusiyritys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CoronaFragment extends Fragment implements View.OnClickListener{

    TextView textViewCorona;
    String sPrint = "";
    Button buttonCoronaUpdate;
    String sDefaultPrint= "COVID-19 on maailmanlaajuinen pandemia, joka vaikuttaa eri ihmisiin eri tavoin. Koronaviruksen tavallisimpia oireita ovat kuume, kuiva yskä ja väsymys.\nMikäli epäilet altistuneesi virukselle, ole välittömästi yhteydessä asuinkuntasi terveydenhuoltoon.\n\n";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("moi vesi");

        return inflater.inflate(R.layout.fragment_corona, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewCorona = (TextView) view.findViewById(R.id.textViewKorona);
        textViewCorona.setText(sDefaultPrint);
        buttonCoronaUpdate = view.findViewById(R.id.buttonGetCorona);
        buttonCoronaUpdate.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        CoronaUpdate coronaUpdate = new CoronaUpdate();
        textViewCorona.setText(sDefaultPrint + "Ladataan...");
        sPrint = "";
        sPrint = coronaUpdate.readXML();
        System.out.println(sPrint);
        textViewCorona.setText(sDefaultPrint + sPrint);


    }
}
