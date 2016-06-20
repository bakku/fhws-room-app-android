package de.fhws.fiw.mobile.applications.roommodule.models;

import java.util.Date;

/**
 * Created by christian on 09/06/16.
 */
public class Lecture {

    private String lectureName;

    private Date startOfLecture;

    private Date endOfLecture;

    public Lecture() {
    }

    public Lecture(String lectureName, Date startOfLecture, Date endOfLecture) {
        this.lectureName = lectureName;
        this.startOfLecture = startOfLecture;
        this.endOfLecture = endOfLecture;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecture lecture = (Lecture) o;

        if (!lectureName.equals(lecture.lectureName)) return false;
        if (!startOfLecture.equals(lecture.startOfLecture)) return false;
        return endOfLecture.equals(lecture.endOfLecture);
    }
}
