package com.example.teamonce.xhale;

import java.util.Calendar;
import java.util.StringTokenizer;

public class Computation {

    public Computation() {

    }

    public static String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }

    public static String getMonth(int month){
        switch (month){
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "";
        }
    }

    public static String dateFormat(String birthdate){
        String day="",month="",year="";
        String[]token = birthdate.split(" ");
        day = token[1].substring(0,2);
        month = getMonthNumber(token[0]);
        year = token[2];
        return day+"/"+month+"/"+year;
    }

    public static String getMonthNumber(String month){
        switch (month){
            case "January": return "01";
            case "February": return "02";
            case "March": return "03";
            case "April": return "04";
            case "May": return "05";
            case "June": return "06";
            case "July": return "07";
            case "August": return "08";
            case "September": return "09";
            case "October": return "10";
            case "November": return "11";
            case "December": return "12";
            default: return "";
        }
    }
}
