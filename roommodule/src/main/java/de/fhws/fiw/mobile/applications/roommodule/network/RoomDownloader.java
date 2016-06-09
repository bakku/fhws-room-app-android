package de.fhws.fiw.mobile.applications.roommodule.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by christian on 03/06/16.
 */
public class RoomDownloader extends AsyncTask<Void, Void, Void> {

    private Context context;
    private DownloadFinishedListener downloadFinishedListener;

    private static final String ROOMS_URL = "http://backend2.applab.fhws.de:8080/fhwsapi/v1/rooms";

    @Override
    protected Void doInBackground(Void... params) {
        HttpURLConnection connection = null;
        String response = "";

        try {
            URL url = new URL(ROOMS_URL);
            connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            response = IOUtils.toString(is);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
        }

        Log.e("TAG", response);

        return null;
    }
}
