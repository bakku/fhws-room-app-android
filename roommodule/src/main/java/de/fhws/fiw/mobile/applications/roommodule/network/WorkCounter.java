package de.fhws.fiw.mobile.applications.roommodule.network;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by christian on 20/06/16.
 */
public class WorkCounter {

    private AtomicInteger runningTasks;
    private DownloadListener downloadListener;

    public WorkCounter(int runningTasks, DownloadListener downloadListener) {
        this.runningTasks = new AtomicInteger(runningTasks);
        this.downloadListener = downloadListener;
    }

    public void taskFinished() {
        if (runningTasks.decrementAndGet() == 0) {
            downloadListener.onDownloadSuccess();
        }
    }
}
