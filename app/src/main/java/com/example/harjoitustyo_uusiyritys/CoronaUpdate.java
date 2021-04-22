package com.example.harjoitustyo_uusiyritys;



import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static android.text.TextUtils.substring;

public class CoronaUpdate {
    String sCoronaCountry;
    String sCoronaCases;
    String sCoronaWeek;
    String sCoronaYear;
    String sCoronaTests;
    String sCoronaTestsPositive;
    String sReturn = "";


    public String readXML(){
        try{
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://opendata.ecdc.europa.eu/covid19/testing/xml/";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getDocumentElement().getElementsByTagName("fme:Sheet1");

            for (int i = 0; i <nList.getLength(); i++){
                Node node = nList.item(i);


                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    if ((element.getElementsByTagName("fme:country").item(0).getTextContent()).equals("Finland")){
                        sCoronaCountry = element.getElementsByTagName("fme:country").item(0).getTextContent();
                        sCoronaCases = element.getElementsByTagName("fme:new_cases").item(0).getTextContent();
                        sCoronaWeek = (element.getElementsByTagName("fme:year_week").item(0).getTextContent()).substring(6, 8);
                        sCoronaYear = (element.getElementsByTagName("fme:year_week").item(0).getTextContent()).substring(0, 4);
                        sCoronaTests = element.getElementsByTagName("fme:tests_done").item(0).getTextContent();
                        sCoronaTestsPositive = element.getElementsByTagName("fme:positivity_rate").item(0).getTextContent();

                    }
                }
            }
        sReturn = "Suomessa on vuoden " + sCoronaYear + " viikolla "+ sCoronaWeek + " todettu " + sCoronaCases + " uutta koronavirustartuntaa. Testejä tehtiin " + sCoronaTests + " kappaletta, joista "+ sCoronaTestsPositive.substring(0, 4) + "% oli positiivisia.";


        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException");
            e.printStackTrace();
        } catch (SAXException e) {
            System.out.println("SAXException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        } finally{
            return sReturn;

        }

    }

}
