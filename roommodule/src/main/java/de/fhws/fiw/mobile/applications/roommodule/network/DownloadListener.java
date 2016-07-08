package de.fhws.fiw.mobile.applications.roommodule.network;

import de.fhws.fiw.mobile.applications.roommodule.models.Room;

/**
 * Created by christian on 03/06/16.
 */
public interface DownloadListener {

    void onDownloadStarted();
    void onOneRoomFinished(Room room);
    void onDownloadSuccess();
    void onDownloadError();

}
