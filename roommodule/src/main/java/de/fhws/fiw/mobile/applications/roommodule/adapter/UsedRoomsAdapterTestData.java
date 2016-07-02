package de.fhws.fiw.mobile.applications.roommodule.adapter;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 02.07.2016.
 */
public class UsedRoomsAdapterTestData {

    private List<Room> usedRooms;

    public UsedRoomsAdapterTestData() {
        this.usedRooms = new LinkedList<>();
        createTestData();
    }

    public List<Room> getTestRoom() {
        return usedRooms;
    }

    private void createTestData() {

        //Raum 1
        List<Lecture> listOfLecturesRoomOne = new LinkedList<>();
          //Lecture
        Lecture lectureOne = new Lecture();
        lectureOne.setLectureName("Erste Vorlesung");

        Calendar beginOfLectureOne = Calendar.getInstance();
        beginOfLectureOne.set(Calendar.HOUR_OF_DAY, 13);
        beginOfLectureOne.set(Calendar.MINUTE, 0);

        Calendar endOfLectureOne = Calendar.getInstance();
        endOfLectureOne.set(Calendar.HOUR_OF_DAY, 15);
        endOfLectureOne.set(Calendar.MINUTE, 30);

        lectureOne.setStartOfLecture(beginOfLectureOne.getTime());
        lectureOne.setEndOfLecture(endOfLectureOne.getTime());

        listOfLecturesRoomOne.add(lectureOne);
          //Lecture
          //Lecture
        Lecture lectureTwo = new Lecture();
        lectureTwo.setLectureName("Erste Vorlesung");

        Calendar beginOfLectureTwo = Calendar.getInstance();
        beginOfLectureTwo.set(Calendar.HOUR_OF_DAY, 16);
        beginOfLectureTwo.set(Calendar.MINUTE, 30);

        Calendar endOfLectureTwo = Calendar.getInstance();
        endOfLectureTwo.set(Calendar.HOUR_OF_DAY, 16);
        endOfLectureTwo.set(Calendar.MINUTE, 45);

        lectureTwo.setStartOfLecture(beginOfLectureTwo.getTime());
        lectureTwo.setEndOfLecture(endOfLectureTwo.getTime());

        listOfLecturesRoomOne.add(lectureTwo);
          //Lecture

        Room roomOne = new Room();
        roomOne.setRoomName("H.1.1");
        roomOne.setListOfLectures(listOfLecturesRoomOne);
        //Ende Raum 1



        this.usedRooms.add(roomOne);
    }
}