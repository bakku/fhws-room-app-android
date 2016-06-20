package de.fhws.fiw.mobile.applications.roommodule.network;

import android.os.AsyncTask;

import de.fhws.fiw.mobile.applications.roommodule.helper.TimeFormatter;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;
import okhttp3.OkHttpClient;

/**
 * Created by christian on 16/06/16.
 */
public class LectureDownloader extends AsyncTask<Void, Void, Void> {

    private DownloadListener downloadListener;
    private RoomData roomData;
    private OkHttpClient client;

    public LectureDownloader(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
        this.roomData = RoomData.getInstance();
        this.client = new OkHttpClient();
    }

    @Override
    protected Void doInBackground(Void... params) {

        for (Room room : roomData.getAllRooms()) {
            String url = new BackendURLBuilder()
                    .lectures()
                    .forRoom(room)
                    .andStartingDate(TimeFormatter.todayAsMilliseconds())
                    .andEndDate(TimeFormatter.tomorrowAsMilliseconds())
                    .build();

            System.out.println(url);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (isCancelled()) {
            downloadListener.onDownloadError();
        }
        else {
            downloadListener.onDownloadSuccess();
        }
    }
}
