package de.fhws.fiw.mobile.applications.roommodule.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by Patrick Müller on 21.06.2016.
 */
public class RoomTestData {

    private Room testRoom;

    public RoomTestData() {
        createTestData();
        System.out.println(this.testRoom.toString());
    }

    public Room getTestRoom() {
        return testRoom;
    }

    private void createTestData() {

        Lecture lectureOne = new Lecture();
        lectureOne.setLectureName("Erste Vorlesung");
        lectureOne.setStartOfLecture(new GregorianCalendar(2016, 01, 01, 8, 0).getTime());
        lectureOne.setEndOfLecture(new GregorianCalendar(2016, 01, 01, 9, 30).getTime());

        Lecture lectureTwo = new Lecture();
        lectureTwo.setLectureName("Zweite Vorlesung");
        lectureTwo.setStartOfLecture(new GregorianCalendar(2016, 01, 01, 10, 30).getTime());
        lectureTwo.setEndOfLecture(new GregorianCalendar(2016, 01, 01, 11, 0).getTime());

        Lecture lectureThree = new Lecture();
        lectureThree.setLectureName("Dritte Vorlesung");
        lectureThree.setStartOfLecture(new GregorianCalendar(2016, 01, 01, 15, 30).getTime());
        lectureThree.setEndOfLecture(new GregorianCalendar(2016, 01, 01, 18, 30).getTime());

        List<Lecture> listOfLectures = new LinkedList<>();
        listOfLectures.add(lectureOne);
        listOfLectures.add(lectureTwo);
        listOfLectures.add(lectureThree);

        Room room = new Room();
        room.setRoomName("H.1.1");
        room.setListOfLectures(listOfLectures);

        this.testRoom = room;
    }
}