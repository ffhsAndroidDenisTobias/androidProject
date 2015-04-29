package myunihockey.ffhs.com.myunihockey.persistence;

import java.net.URI;
import java.net.URL;

/**
 * Created by Denis Bittante on 27.04.2015.
 */
public class UnihockeyRestFactory {


    private static final String RESTROOT = "http://api.swissunihockey.ch/rest/v1.0";

    private static final String REST_API_KEY = "diqkOP74wp5UdhBQNKwk2f0SpBY=";


    private static final String REST_CLUB = RESTROOT + "/clubs/";
    private static final String REST_GAME = RESTROOT + "/games/";
    private static final String REST_TEAM = RESTROOT + "/teams/";
    private static final String REST_LEAGUE = RESTROOT + "/league/";


    public URI getAllClubs() {
        return buildUri(REST_CLUB);
    }

    public URI getByClubId(String clubId) {
    return buildUri(REST_CLUB +"/%s/");
    }


    public URI searchClub(String q) {

       return  buildUri(REST_CLUB + "/search/?apikey=%s=&q=%s", REST_API_KEY, q);
    }

    public URI buildUri(String URL, String... param) {

        String urlString = String.format(URL, param);

        URI uri = URI.create(urlString);
        return uri;
    }


}
