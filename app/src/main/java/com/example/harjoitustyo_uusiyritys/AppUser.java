package com.example.harjoitustyo_uusiyritys;

public class AppUser {
    private String sAccount = "";
    private int iAge = 0;
    private String sTown = "";
    private String sEmail = "";
    private String sPassword = "";
    private int iHeight = 0;
    private float fWeight = 0;
    private String sBirthday = "";
    private boolean bMale = true;

    //If user does not want to change their weight, they are AppUser
    public AppUser(String account, int age, String town, String email, String password, int height, float weight, String birthday, boolean male) {
        sAccount = account;
        iAge = age;
        sTown = town;
        sEmail = email;
        sPassword = password;
        iHeight = height;
        fWeight = weight;
        sBirthday = birthday;
        bMale = male;
    }
    public AppUser(){}

    public void refreshUserinfo(String account, int age, String town, String email, String password, int height, float weight, String birthday, boolean male) {
        sAccount = account;
        iAge = age;
        sTown = town;
        sEmail = email;
        sPassword = password;
        iHeight = height;
        fWeight = weight;
        sBirthday = birthday;
        bMale = male;
    }
    public String[] getList() {
        String[] array = {sAccount, Integer.toString(iAge), sTown, sEmail, sPassword, Integer.toString(iHeight), Float.toString(fWeight), sBirthday, Boolean.toString(bMale)};
        return array;
    }

    public String getsAccount(){
        return sAccount;
    }
    public int getiAge(){
        return iAge;
    }
    public String getsTown(){
        return sTown;
    }
    public String getsEmail(){
        return sEmail;
    }
    public String getsPassword(){
        return sPassword;
    }
    public int getiHeight(){
        return iHeight;
    }
    public float getfWeight(){
        return fWeight;
    }
    public String getsBirthday(){
        return sBirthday;
    }
    public boolean getbMale() {return bMale; }
    public void setsEmail(String email) {this.sEmail = email;}


}
