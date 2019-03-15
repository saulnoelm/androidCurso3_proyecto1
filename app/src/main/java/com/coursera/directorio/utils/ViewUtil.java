package com.coursera.directorio.utils;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewUtil {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static String currencyFormat(Double monto) {
        return NumberFormat.getCurrencyInstance(new Locale("es", "MX")).format(monto);
    }

    public static boolean validaFormatNumbers(String monto) {
        try {
            if ("".equals(monto)) {
                monto = "0";
            }
            Double.valueOf(monto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public static boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() < 6 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;
            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }

}
