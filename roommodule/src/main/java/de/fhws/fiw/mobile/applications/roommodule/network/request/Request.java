package de.fhws.fiw.mobile.applications.roommodule.network.request;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by christian on 20/06/16.
 */
public class Request {

    private String url;
    private HttpURLConnection connection;

    public Request(String url) {
        this.url = url;
    }

    public String execute() throws RequestException {
        try {
            URL url = new URL(this.url);
            connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();

            String response = IOUtils.toString(is);

            connection.disconnect();

            return response;
        }
        catch (IOException e) {
            connection.disconnect();

            throw new RequestException(e);
        }
    }


}
