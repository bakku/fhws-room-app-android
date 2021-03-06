package de.fhws.fiw.mobile.applications.roommodule.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import de.fhws.fiw.mobile.applications.roommodule.helper.TimeFormatter;
import de.fhws.fiw.mobile.applications.roommodule.models.Lecture;
import de.fhws.fiw.mobile.applications.roommodule.models.LectureCreator;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;
import de.fhws.fiw.mobile.applications.roommodule.network.request.Request;

/**
 * Created by christian on 16/06/16.
 */
public class LectureDownloader extends AsyncTask<Void, Void, Void> {

    private DownloadListener downloadListener;
    private Room room;
    WorkCounter workCounter;

    public LectureDownloader(DownloadListener downloadListener, Room room, WorkCounter workCounter) {
        this.downloadListener = downloadListener;
        this.room = room;
        this.workCounter = workCounter;
    }

    @Override
    protected Void doInBackground(Void... params) {
        String url = buildUrlForRoom(this.room);

        List<String> eventUrls = retrieveEventUrls(url);

        addEventsToRoomData(room, eventUrls);

        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        downloadListener.onDownloadError();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        workCounter.taskFinished();
        downloadListener.onOneRoomFinished(this.room);
    }

    private String buildUrlForRoom(Room room) {
        return new BackendURLBuilder()
                .lectures()
                .forRoom(room)
                .andStartingDate(TimeFormatter.todayAsMilliseconds())
                .andEndDate(TimeFormatter.tomorrowAsMilliseconds())
                .build();
    }

    private List<String> retrieveEventUrls(String url) {
        List<String> eventUrls = new LinkedList<>();

        try {
            String response = new Request(url).execute();

            JSONArray allLectures = new JSONArray(response);

            for (int i = 0; i < allLectures.length(); i++) {
                JSONObject currentLecture = allLectures.getJSONObject(i);

                JSONArray eventsForCurrentLecture = currentLecture.getJSONArray("events");

                for (int j = 0; j < eventsForCurrentLecture.length(); j++) {
                    eventUrls.add(eventsForCurrentLecture.getJSONObject(j).getString("url"));
                }
            }
        }
        catch (Exception e) {
            Log.e("TAG", "" + e.getMessage());
            cancel(true);
        }

        return eventUrls;
    }

    private void addEventsToRoomData(Room room, List<String> eventUrls) {
        for (String currentEventUrl : eventUrls) {
            try {
                String currentEvent = new Request(currentEventUrl).execute();

                JSONObject eventAsJson = new JSONObject(currentEvent);

                Lecture newLecture = LectureCreator.createLectureFromEventJSONObject(eventAsJson);

                room.addLecture(newLecture);
            }
            catch (Exception e) {
                Log.e("TAG", "" + e.getMessage());
                cancel(true);
            }
        }
    }
}
