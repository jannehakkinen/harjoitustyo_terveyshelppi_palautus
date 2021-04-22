package com.example.harjoitustyo_uusiyritys;

public class Settings {

    private static Settings single_instance = null;
    private boolean bLogin = false;
    private Settings(){}
    public static Settings getInstance() {
        if (single_instance == null) {
            single_instance = new Settings();
        }
        return single_instance;
    }

    public boolean getBLogin() { return bLogin; }

    public void setbLogin(boolean login) { this.bLogin = login; }
}
