package com.zolostaystask.utils;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static String validateMsg = null;

    public static String validatePhone(String phone) {
        validateMsg = null;
        if (phone.length() < 10) {
            validateMsg = "Phone number is invalid!";
        }
        return validateMsg;
    }

    public static String validatePwd(String pwd) {
        validateMsg = null;
        Pattern pattern = Pattern.compile("\\d+");

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(pwd);

        while (m.find()) {
            Log.e("mk", "password-->" + m.group());
        }

        /*if (pwd.length() < 5) {
            validateMsg = "Password is too short. Minimum 5 characters required!";
        }*/
        if (pattern.matcher(pwd).matches()) {
            validateMsg = "Atleast one digit required!";
        }
        /*if (pwd.matches("((?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{5,10})")) {
            validateMsg = "Password requires one digit, one lowercase letter, one uppercase letter, one special character, minimum length 5 and maximum length 10!";
        }*/
        return validateMsg;
    }

    public static boolean validateEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}