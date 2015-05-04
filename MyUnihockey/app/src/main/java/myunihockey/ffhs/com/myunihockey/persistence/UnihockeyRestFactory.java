package myunihockey.ffhs.com.myunihockey.persistence;

import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by Denis Bittante on 27.04.2015.
 */
public class UnihockeyRestFactory {


    private static final String RESTROOT = "http://api.swissunihockey.ch/rest/v1.0";

    private static final String REST_API_KEY = "diqkOP74wp5UdhBQNKwk2f0SpBY=";


    private static final String REST_CLUB = RESTROOT + "/clubs/";
    private static final String REST_GAME = RESTROOT + "/games/";
    private static final String REST_TEAM = RESTROOT + "/teams/";
    private static final String REST_LEAGUE = RESTROOT + "/leagues/";

    //Clubs Requests
    public URI getAllClubs() {
        return new UriBuilderHelper(REST_CLUB)
                .addQuery("apikey", REST_API_KEY)
                .build();
    }

    public URI getClubById(String clubId) {
        return new UriBuilderHelper(REST_CLUB + "%s/", clubId)
                .addQuery("apikey", REST_API_KEY)
                .build();
    }

    public URI searchClub(String q) {
        return new UriBuilderHelper(REST_CLUB + "search/")
                .addQuery("apikey", REST_API_KEY)
                .addQuery("q", q)
                .build();
    }

    public URI getTeamsByClubId(String clubId) {
        return new UriBuilderHelper(REST_CLUB + "%s/teams/", clubId)
                .addQuery("apikey", REST_API_KEY)
                .build();
    }

    //League Requests
    public URI getLeagues() {
        return new UriBuilderHelper(REST_LEAGUE)
                .addQuery("apikey", REST_API_KEY)
                .build();
    }

    public URI getGames(String leagueId, String groupId) {
        return getGames(leagueId, groupId, null, null, null);
    }


    public URI getGames(String leagueId, String groupId, String clubId, String gameStatus, String limit) {

        return new UriBuilderHelper(REST_LEAGUE + "%s/groups/%s/games", leagueId, groupId)
                .addQuery("apikey", REST_API_KEY)
                .addQuery("status", gameStatus)
                .addQuery("limit", limit)
                .addQuery("club", clubId)
                .build();

    }

    public URI getByLeagueIdGroups(String leagueId) {
        return new UriBuilderHelper(REST_LEAGUE + "%s/groups/", leagueId)
                .addQuery("apikey", REST_API_KEY)
                .build();
    }


    private class UriBuilderHelper {

        StringBuffer uri;
        ArrayList<String> paramArray;


        public UriBuilderHelper(String uri, String... params) {
            this.uri = new StringBuffer().append(uri);
            this.uri.append("?");

            paramArray = new ArrayList<String>();
            for (String param : params) {
                paramArray.add(param);
            }
        }

        public UriBuilderHelper addQuery(String name, String param) {
            if (!StringUtils.isEmpty(param)) {
                uri.append(name + "=%s&");
                paramArray.add(param);
            }
            return this;
        }

        public URI build() {

            String[] stringArray = paramArray.toArray(new String[paramArray.size()]);

            return buildUri(uri.toString(), stringArray);

        }


        public URI buildUri(String URL, String... param) {

            String urlString = String.format(URL, param);

            URI uri = URI.create(urlString);
            return uri;
        }
    }

}
