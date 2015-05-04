package myunihockey.ffhs.com.myunihockey.rest;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Denis Bittante on 26.04.2015.
 */
public class RestConnector {


    public String callRest(String urlString) throws IOException {


        String resultToDisplay = "";

        InputStream in = null;

        // HTTP Get

        URL url = new URL(urlString);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        in = new BufferedInputStream(urlConnection.getInputStream());
        resultToDisplay = IOUtils.toString(in, "UTF-8");

        return resultToDisplay;
    }


}


