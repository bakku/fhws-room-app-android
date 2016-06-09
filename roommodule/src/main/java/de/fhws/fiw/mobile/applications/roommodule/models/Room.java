package de.fhws.fiw.mobile.applications.roommodule.models;

import java.util.List;

/**
 * Created by christian on 09/06/16.
 */
public class Room {

    private String title;

    private List<Lecture> listOfLectures;

    public Room() {
    }

    public Room(String title, List<Lecture> listOfLectures) {
        this.title = title;
        this.listOfLectures = listOfLectures;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Lecture> getListOfLectures() {
        return listOfLectures;
    }

    public void setListOfLectures(List<Lecture> listOfLectures) {
        this.listOfLectures = listOfLectures;
    }
    
}
