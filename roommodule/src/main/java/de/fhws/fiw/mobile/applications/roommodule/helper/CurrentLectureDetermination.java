package de.fhws.fiw.mobile.applications.roommodule.helper;

import java.util.Calendar;

import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 02.07.2016.
 */
public class CurrentLectureDetermination {

    private Room room;

    private Calendar currentTime;

    public CurrentLectureDetermination(Room room){
        this.room = room;
        this.currentTime = TimeFormatter.getCurrentTimeAsCalendar();
    }

    public Lecture getCurrentLecture(){

        Lecture result = null;

        for(Lecture lecture : this.room.getListOfLectures()){

            Calendar startTimeOfLecture = Calendar.getInstance();
            startTimeOfLecture.setTime(lecture.getStartOfLecture());

            Calendar endTimeOfLecture = Calendar.getInstance();
            endTimeOfLecture.setTime(lecture.getEndOfLecture());

            if(this.currentTime.after(startTimeOfLecture) && this.currentTime.before(endTimeOfLecture)){
                result = lecture;
                break;
            }
        }

        return result;
    }
}