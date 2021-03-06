package de.fhws.fiw.mobile.applications.roommodule.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import de.fhws.fiw.mobile.applications.roommodule.models.Room;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomCreator;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;
import de.fhws.fiw.mobile.applications.roommodule.network.request.Request;

/**
 * Created by christian on 03/06/16.
 */
public class RoomDownloader extends AsyncTask<Void, Void, Void> {

    private DownloadListener downloadListener;

    public RoomDownloader(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    protected Void doInBackground(Void... params) {
        String response;
        JSONArray roomArray;

        try {
            String url = new BackendURLBuilder().allRooms().build();
            response = new Request(url).execute();
            roomArray = new JSONArray(response);
            RoomCreator.createRoomsFromJsonArray(roomArray);
        }
        catch (Exception e) {
            Log.e("TAG", "" + e.getMessage());
            cancel(true);
        }

        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        downloadListener.onDownloadError();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        WorkCounter workCounter = new WorkCounter(RoomData.getInstance(null, false).getAllRooms().size(), downloadListener);

        for (Room room : RoomData.getInstance(null, false).getAllRooms()) {
            new LectureDownloader(downloadListener, room, workCounter).execute();
        }
    }

}
