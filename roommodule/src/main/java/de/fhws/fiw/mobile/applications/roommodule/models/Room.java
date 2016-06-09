package de.fhws.fiw.mobile.applications.roommodule.models;

import java.util.List;

/**
 * Created by christian on 09/06/16.
 */
public class Room {

    private String roomName;

    private String url;

    private List<Lecture> listOfLectures;

    public Room() {
    }

    public Room(String roomName, String url) {
        this.roomName = roomName;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
