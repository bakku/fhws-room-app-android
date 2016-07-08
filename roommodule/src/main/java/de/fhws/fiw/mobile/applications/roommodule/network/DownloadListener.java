package de.fhws.fiw.mobile.applications.roommodule.network;

/**
 * Created by christian on 03/06/16.
 */
public interface DownloadListener {

    void onDownloadStarted();
    void onDownloadSuccess();
    void onDownloadError();

}
