package de.fhws.fiw.mobile.applications.roommodule.network;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.fhws.fiw.mobile.applications.roommodule.models.RoomCreator;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;

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
        HttpURLConnection connection = null;
        String response;
        JSONArray roomArray;
        InputStream is;

        try {
            URL url = new URL(BackendURL.ALL_ROOMS_URL);
            connection = (HttpURLConnection) url.openConnection();
            is = connection.getInputStream();
            response = IOUtils.toString(is);

            roomArray = new JSONArray(response);
            RoomCreator.createRoomsFromJsonArray(roomArray);
        }
        catch (Exception e) {
            Log.e("TAG", "" + e.getMessage());
            cancel(true);
        }
        finally {
            connection.disconnect();
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
