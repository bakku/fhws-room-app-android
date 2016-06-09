package de.fhws.fiw.mobile.applications.roommodule.network;

import android.content.Context;
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

    private Context context;
    private DownloadFinishedListener downloadFinishedListener;

    @Override
    protected Void doInBackground(Void... params) {
        HttpURLConnection connection = null;
        String response;

        try {
            URL url = new URL(RoomURL.ALL_ROOMS_URL);
            connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            response = IOUtils.toString(is);

            JSONArray jsonArray = new JSONArray(response);
            RoomCreator.createRoomsFromJsonArray(jsonArray);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.e("TAG", RoomData.getInstance().getRoom(0).getRoomName());
        Log.e("TAG", RoomData.getInstance().getRoom(0).getUrl());
    }
}
