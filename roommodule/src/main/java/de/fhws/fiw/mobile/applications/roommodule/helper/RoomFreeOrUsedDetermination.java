package de.fhws.fiw.mobile.applications.roommodule.helper;

import java.util.Calendar;
import java.util.Date;

import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 02.07.2016.
 */
public class RoomFreeOrUsedDetermination {

    private Room room;

    private Calendar currentDayTime;

    public RoomFreeOrUsedDetermination(Room room){
        this.room = room;
        this.currentDayTime = TimeFormatter.getCurrentTimeAsCalendar();
    }

    public boolean roomIsFree(){

        boolean result = true;

        for(Lecture lecture : this.room.getListOfLectures()){

            if(lectureIsAtTheMoment(lecture)){
                result = false;
                break;
            }
        }

        return result;
    }

    private boolean lectureIsAtTheMoment(Lecture lecture){

        boolean result = false;

        Calendar startTime = Calendar.getInstance();
        startTime.setTime(lecture.getStartOfLecture());

        Calendar endTime = Calendar.getInstance();
        endTime.setTime(lecture.getEndOfLecture());

        if(this.currentDayTime.after(startTime) && this.currentDayTime.before(endTime)){
            result = true;
        }

        return result;
    }
}