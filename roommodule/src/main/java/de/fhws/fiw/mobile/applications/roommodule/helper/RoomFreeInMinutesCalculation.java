package de.fhws.fiw.mobile.applications.roommodule.helper;

import java.util.Calendar;

import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 02.07.2016.
 */
public class RoomFreeInMinutesCalculation {

    private Room room;

    private Calendar currentTime;

    public RoomFreeInMinutesCalculation(Room room){
        this.room = room;
        this.currentTime = TimeFormatter.getCurrentTimeAsCalendar();
    }

    public long calculateTimeRoomWillBeFreeInMinutes(){

        long resultInMinutes = 0;

//        Lecture upcomingLecture = new UpcomingLectureDetermination(this.room).getUpcomingLecture();
//
//        Calendar startOfUpcomingLecture = Calendar.getInstance();
//
//        if(thereIsNoUpcomingLecture(upcomingLecture)){
//
//            resultInMinutes = timeUntilMidnightInMinutes();
//        }
//        else{

//            startOfUpcomingLecture.setTime(upcomingLecture.getStartOfLecture());

            if (!this.room.roomIsFree()) {
                //Dann müssen wir bis ende dieses Kurses warten, dass er frei wird

                Lecture currentCourse = new CurrentLectureDetermination(this.room).getCurrentLecture();

                Calendar endOfCurrentCourse = Calendar.getInstance();
                endOfCurrentCourse.setTime(currentCourse.getEndOfLecture());

                resultInMinutes = TimeFormatter.differenceInMinutes(this.currentTime, endOfCurrentCourse);

            } else {
                //Dann ist der Raum sowieso schon frei

                resultInMinutes = 0;
            }
//        }

        return resultInMinutes;
    }

    private boolean thereIsNoUpcomingLecture(Lecture lecture){

        boolean result = false;

        if(lecture == null){
            result = true;
        }

        return result;
    }

    private long timeUntilMidnightInMinutes(){

        Calendar todayMidnight = Calendar.getInstance();
        todayMidnight.set(Calendar.HOUR_OF_DAY, 23);
        todayMidnight.set(Calendar.MINUTE, 59);
        todayMidnight.set(Calendar.SECOND, 59);
        todayMidnight.set(Calendar.MILLISECOND, 0);

        return TimeFormatter.differenceInMinutes(this.currentTime, todayMidnight);
    }
}