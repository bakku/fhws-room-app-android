package de.fhws.fiw.mobile.applications.roommodule.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by christian on 09/06/16.
 */
public class Room {

    private String roomName;

    private String freeInMinutes;

    private String freeForMinutes;

    private List<Lecture> listOfLectures;

    public Room() {
    }

    public Room(String roomName) {
        this.roomName = roomName;
        this.listOfLectures = new LinkedList<>();
    }

    public Room(String roomName, List<Lecture> listOfLectures) {
        this.roomName = roomName;
        this.listOfLectures = listOfLectures;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<Lecture> getListOfLectures() {
        return listOfLectures;
    }

    public void setListOfLectures(List<Lecture> listOfLectures) {
        this.listOfLectures = listOfLectures;
    }

    public String getFreeForMinutes() {
        return freeForMinutes;
    }

    public void setFreeForMinutes(String freeForMinutes) {
        this.freeForMinutes = freeForMinutes;
    }

    public String getFreeInMinutes() {
        return freeInMinutes;
    }

    public void setFreeInMinutes(String freeInMinutes) {
        this.freeInMinutes = freeInMinutes;
    }

    public void addLecture(Lecture lecture) {
        for (Lecture l : listOfLectures) {
            if (lecture.equals(l)) {
                return;
            }
        }

        listOfLectures.add(lecture);
    }

    @Override
    public String toString(){

        StringBuffer sb = new StringBuffer();

        sb.append("Info für Raum " + this.roomName + "\n");

        for(Lecture lecture : this.listOfLectures){
            sb.append(lecture.toString() + "\n");
        }

        return sb.toString();
    }
}
