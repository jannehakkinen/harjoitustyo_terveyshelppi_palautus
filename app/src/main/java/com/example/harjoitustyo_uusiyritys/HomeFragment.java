package com.example.harjoitustyo_uusiyritys;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class HomeFragment extends Fragment {
    float fHeight;
    float fWeight;
    float fBmi;
    double dBmi;
    String sSex;
    String wBmi;
    String mBmi;
    View view;
    boolean bMale;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //Editing textView is done after creating
        TextView sText = view.findViewById(R.id.textViewUser);
        TextView sText1 = view.findViewById(R.id.textViewBmi);
        TextView sText2 = view.findViewById(R.id.textViewText);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            //Reading the document and creating nodelist
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String url = "https://apps.who.int/gho/athena/api/GHO/NCD_BMI_MEAN?filter=COUNTRY:FIN;YEAR:2016";
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();


            NodeList nList = doc.getDocumentElement().getElementsByTagName("Observation");


            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);


                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    //Saving info about average BMI from API
                    if (i == 1) {
                        wBmi = element.getElementsByTagName("Display").item(0).getTextContent();
                        wBmi = wBmi.substring(0, Math.min(wBmi.length(),4));

                    }
                    if (i == 2) {
                        mBmi = element.getElementsByTagName("Display").item(0).getTextContent();
                        mBmi = mBmi.substring(0, Math.min(mBmi.length(),4));

                    }
                }
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
            System.out.println("Epäonnistui :(");
        }
        try {
            String[] arg = getArguments().getStringArray("userinfoH");
            fWeight = Float.parseFloat(arg[6]);
            fHeight = Float.parseFloat(arg[5]);
            bMale = Boolean.parseBoolean(arg[8]);
            fHeight = fHeight/100;
            fBmi = (fWeight/(fHeight * fHeight));
            dBmi = Math.round(fBmi * 100.0) / 100.0;
            sText.setText("Sinun painoindeksisi on: " + dBmi );
            if(bMale){
                sText1.setText("Miesten keskiarvopainoindeksi on Suomessa: " + mBmi);
            }else{
                sText1.setText("Naisten keskiarvopainoindeksi on Suomessa: " + wBmi);
            }

            sText2.setText("Voisit mennä treenaamaan tänään! :)");

        }catch (NullPointerException e) {
            sText.setText("Käyttäjätietoja ei saatavilla");

        }





        //      if (user = woman)



       /* if (user = man){
            sText.setText("Your Body Mass Index: " + bmi );
            sText1.setText("Average BMI for men in Finland is " + mBmi);

        }*/


    }
}