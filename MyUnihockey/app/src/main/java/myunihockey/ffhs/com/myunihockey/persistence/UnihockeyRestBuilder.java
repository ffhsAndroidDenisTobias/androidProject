package myunihockey.ffhs.com.myunihockey.persistence;

import java.net.URI;
import java.net.URL;

/**
 * Created by Denis Bittante on 27.04.2015.
 */
public class UnihockeyRestBuilder {

    private static final String REST_TEAM = "http://hallo...%s&team=%s";

    public URI buildUri(String URL, String... param) {

        String urlString = String.format(URL, param[0]);

        URI uri = URI.create(urlString);
        return uri;
    }


    public URI getTeamUri(String team, String id) {
        // check param
        return buildUri(REST_TEAM, team, id);

    }


}
