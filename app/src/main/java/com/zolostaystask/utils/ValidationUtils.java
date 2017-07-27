package com.zolostaystask.utils;

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
        if (pwd.length() < 5) {
            validateMsg = "Password must have atleast 5 characters!";
        }
        return validateMsg;
    }

    public static String validateEmail(String email) {
        validateMsg = null;
        Pattern emailPattern = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+");

        if (!emailPattern.matcher(email).matches()) {
            validateMsg = "Email is invalid!";
        }
        return validateMsg;
    }

    public static String validateName(String name) {
        validateMsg = null;
        if (name.length() == 0) {
            validateMsg = "Please enter your name!";
        }
        return validateMsg;
    }
}