package de.fhws.fiw.mobile.applications.roommodule.helper;

import java.util.Calendar;
import java.util.Queue;

import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 02.07.2016.
 */
public class RoomFreeForMinutesCalculation {

    private Room room;

    private Calendar currentTime;

    public RoomFreeForMinutesCalculation(Room room) {
        this.room = room;
        this.currentTime = TimeFormatter.getCurrentTimeAsCalendar();
    }

    public long calculateTimeRoomIsFreeForMinutes() {

        long resultInMinutes = 0;

        Lecture upcomingLecture = new UpcomingLectureDetermination(this.room).getUpcomingLecture();

        Calendar startOfUpcomingLecture = Calendar.getInstance();

        if(thereIsNoUpcomingLecture(upcomingLecture)){

            resultInMinutes = timeUntilMidnightInMinutes();

            if(!this.room.roomIsFree()){
                Lecture currentLecture = new CurrentLectureDetermination(this.room).getCurrentLecture();

                Calendar endOfCurrentLecture = Calendar.getInstance();
                endOfCurrentLecture.setTime(currentLecture.getEndOfLecture());

                resultInMinutes = TimeFormatter.differenceInMinutes(endOfCurrentLecture, getMidnightAsCalendar());
            }
        }
        else{

            startOfUpcomingLecture.setTime(upcomingLecture.getStartOfLecture());

            if (this.room.roomIsFree()) {
                //Dann bis zum Beginn der nächsten Vorlesung

                resultInMinutes = TimeFormatter.differenceInMinutes(this.currentTime, startOfUpcomingLecture);

            } else {
                //Dann vom Ende der aktuellen Vorlesung bis Beginn der nächsten Vorlesung

                Lecture currentLecture = new CurrentLectureDetermination(this.room).getCurrentLecture();

                Calendar endOfCurrentLecture = Calendar.getInstance();
                endOfCurrentLecture.setTime(currentLecture.getEndOfLecture());

                resultInMinutes = TimeFormatter.differenceInMinutes(endOfCurrentLecture, startOfUpcomingLecture);
            }
        }

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

    private Calendar getMidnightAsCalendar(){
        Calendar todayMidnight = Calendar.getInstance();
        todayMidnight.set(Calendar.HOUR_OF_DAY, 23);
        todayMidnight.set(Calendar.MINUTE, 59);
        todayMidnight.set(Calendar.SECOND, 59);
        todayMidnight.set(Calendar.MILLISECOND, 0);

        return todayMidnight;
    }
}