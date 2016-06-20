package de.fhws.fiw.mobile.applications.roommodule.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;

/**
 * Created by christian on 20/06/16.
 */
public class LectureCreator {

    public static Lecture createLectureFromEventJSONObject(JSONObject event) throws JSONException {
        Lecture lecture = new Lecture();

        lecture.setLectureName(event.getString("name"));
        lecture.setStartOfLecture(new Date(event.getLong("startdate")));
        lecture.setEndOfLecture(new Date(event.getLong("enddate")));

        return lecture;
    }
}
