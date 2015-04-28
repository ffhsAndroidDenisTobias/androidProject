package myunihockey.ffhs.com.myunihockey.persistence;

import java.net.URI;
import java.net.URL;

/**
 * Created by Denis Bittante on 27.04.2015.
 */
public class UnihockeyRestBuilder {
    // Bsp auf:
    // http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_21_004.htm#mjeb9209c2668af7983ec6bb0564bc6b4d
    private static final String REST_API_KEY="diqkOP74wp5UdhBQNKwk2f0SpBY=";
    private static final String REST_TEAM = "http://hallo...%s&team=%s";
    private static final String REST_LEAGUE = "http://hallo...%s&team=%s";
    private static final String REST_CLUB = "http://api.swissunihockey.ch/rest/v1.0/clubs/";
    private static final String REST_GAME = "http://api.swissunihockey.ch/rest/v1.0/games/"; // + /GameID oder /search


    public URI buildUri(String URL, String... param) {

        String urlString = String.format(URL, param[0]);

        URI uri = URI.create(urlString);
        return uri;
    }

    public URI getClubs() {
        // check param
        return buildUri(REST_CLUB);

    }

    public URI getClubsByQuery(String query) {
        // check param
        return buildUri(REST_CLUB, query);

    }

    public URI getTeamUriByTeam(String teamQuery, String id) {
        // check param
        return buildUri(REST_TEAM, teamQuery, id);

    }

    public URI getTeamUriByLeague(String league, String id) {
        // check param
        return buildUri(REST_TEAM, league, id);

    }

    public URI getLeagueUri(String team, String id) {
        // check param
        return buildUri(REST_LEAGUE, team, id);

    }

    public URI getGame(String team, String id, String season) {
        // check param
        return buildUri(REST_GAME, team, id);

    }



}
