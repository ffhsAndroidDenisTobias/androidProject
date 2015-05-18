package myunihockey.ffhs.com.myunihockey.rest;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Denis Bittante on 26.04.2015.
 */
public class RestConnector {


    public InputStream callRest(String urlString) throws IOException {


        InputStream in = null;

        URI url = null;
        try {
            url = new URI(urlString);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return callRest(url);
    }

    public InputStream callRest(URI url) throws IOException {
        return downloadUrl(url.toURL());
    }

    private InputStream downloadUrl(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        return conn.getInputStream();
    }
}


