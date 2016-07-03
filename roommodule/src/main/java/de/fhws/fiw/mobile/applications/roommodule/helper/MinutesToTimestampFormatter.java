package de.fhws.fiw.mobile.applications.roommodule.helper;

/**
 * Created by Patrick Müller on 02.07.2016.
 */
public class MinutesToTimestampFormatter {

    public static String toTimestamp(int minutes){

        String result = "";

        int hours = 0;

        while(minutes > 60){
            hours++;
            minutes -= 60;
        }

        if(hours == 0){
            result += minutes + " Min";
        }else{
            result += hours + " H " + minutes + " Min";
        }

        return result;
    }
}
