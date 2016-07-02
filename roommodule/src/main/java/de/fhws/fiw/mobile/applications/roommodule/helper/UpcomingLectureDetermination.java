package de.fhws.fiw.mobile.applications.roommodule.helper;

import java.util.Calendar;

import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 02.07.2016.
 */

//Man erhält die nächste Vorlesung, die noch NICHT begonnen hat.
public class UpcomingLectureDetermination {

    private Room room;

    private Calendar currentTime;

    public UpcomingLectureDetermination(Room room){
        this.room = room;
        this.currentTime = TimeFormatter.getCurrentTimeAsCalendar();
    }

    public Lecture getUpcomingLecture(){

        Lecture result = null;

        for(Lecture lecture : this.room.getListOfLectures()){

            Calendar startTimeOfLecture = Calendar.getInstance();
            startTimeOfLecture.setTime(lecture.getStartOfLecture());

            if(startTimeOfLecture.after(this.currentTime)){
                result = lecture;
                break;
            }
        }

        return result;
    }
}