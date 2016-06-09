package de.fhws.fiw.mobile.applications.roommodule.network;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by christian on 03/06/16.
 */
public class RoomDownloader extends AsyncTask<Void, Void, Void> {

    private Context context;
    private DownloadFinishedListener downloadFinishedListener;

    public RoomDownloader(Context context, DownloadFinishedListener downloadFinishedListener) {
        this.context = context;
        this.downloadFinishedListener = downloadFinishedListener;
    }

    @Override
    protected Void doInBackground(Void... params) {

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        downloadFinishedListener.onDownloadFinished();
    }
}
