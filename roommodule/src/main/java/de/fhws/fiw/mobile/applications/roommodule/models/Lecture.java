package de.fhws.fiw.mobile.applications.roommodule.models;

import java.util.Date;

/**
 * Created by christian on 09/06/16.
 */
public class Lecture {

    private String title;

    private Date startOfLecture;

    private Date endOfLecture;

    public Lecture() {
    }

    public Lecture(String title, Date startOfLecture, Date endOfLecture) {
        this.title = title;
        this.startOfLecture = startOfLecture;
        this.endOfLecture = endOfLecture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartOfLecture() {
        return startOfLecture;
    }

    public void setStartOfLecture(Date startOfLecture) {
        this.startOfLecture = startOfLecture;
    }

    public Date getEndOfLecture() {
        return endOfLecture;
    }

    public void setEndOfLecture(Date endOfLecture) {
        this.endOfLecture = endOfLecture;
    }
}
