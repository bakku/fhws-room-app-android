package de.fhws.fiw.mobile.applications.roommodule.network.request;

import java.io.IOException;

/**
 * Created by christian on 20/06/16.
 */
public class RequestException extends IOException {

    public RequestException(IOException e) {
        super(e);
    }
}
