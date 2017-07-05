package com.brandcash.ui;

/**
 * Created by savva.volobuev on 25.06.2017.
 */

public class FormatStringUtil {


    public static String getDependentStringBonus(int count) {
        int last = count % 10;
        if (last == 0 || last == 5 || last == 6 || last == 7 || last == 8 || last == 9) {
            return "БАЛЛОВ";
        } else if (last == 1) {
            return "БАЛЛ";
        } else {
            return "БАЛЛ";
        }
    }


    public static String getDependentStringReceipt(int count) {
        int last = count % 10;
        if (last == 0 || last == 5 || last == 6 || last == 7 || last == 8 || last == 9) {
            return "чеков";
        } else if (last == 1) {
            return "чек";
        } else {
            return "чека";
        }
    }

    public static String getDependentStringRuble(String count) {
        try {
            int temp = Integer.valueOf(count);
            int last = temp % 10;
            if (last == 0 || last == 5 || last == 6 || last == 7 || last == 8 || last == 9) {
                return "рублей";
            } else if (last == 1) {
                return "рубль";
            } else {
                return "рубля";
            }
        } catch (NumberFormatException ex) {
            return "рублей";
        }
    }
}
