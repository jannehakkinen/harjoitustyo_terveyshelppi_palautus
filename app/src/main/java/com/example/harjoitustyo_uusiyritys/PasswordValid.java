package com.example.harjoitustyo_uusiyritys;

public class PasswordValid {
    public static boolean isValid(String password) {

        if(password.length()<12) {
            System.out.println("Lyhyt");
            return false;

        }
        if (password.contains(" ")) {
            System.out.println("välilyönti");
            return false;

        }
        if (true){
            int count = 0;

            for (int i = 0; i <= 9; i++) {
                String str = Integer.toString(i);
                if (password.contains(str)) {
                    count = 1;
                }
            }
            if (count == 0) {
                System.out.println("ei numeroa");
                return false;
            }
        }
        if (!(password.contains("@") || password.contains("#")
                || password.contains("!") || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("(")
                || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains(".")
                || password.contains(", ") || password.contains("<")
                || password.contains(">") || password.contains("?")
                || password.contains("|"))) {
            System.out.println("ei erikoismerkkiä");
            return false;
        }
        if (true) {
            int count = 0;

            // checking capital letters
            for (int i = 65; i <= 90; i++) {

                // type casting
                char c = (char)i;

                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                System.out.println("Ei isoa kirjainta");
                return false;
            }
        }

        if (true) {
            int count = 0;

            // checking small letters
            for (int i = 90; i <= 122; i++) {

                // type casting
                char c = (char)i;
                String str1 = Character.toString(c);

                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                System.out.println("Ei pientä kirjainta");
                return false;
            }
        }

        // if all conditions fails
        return true;

    }
}


