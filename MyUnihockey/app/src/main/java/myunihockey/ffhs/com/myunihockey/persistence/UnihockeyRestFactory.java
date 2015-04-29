package myunihockey.ffhs.com.myunihockey.persistence;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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


    public URI getAllClubs() {

        UriBuilderHelper helper = new UriBuilderHelper(REST_CLUB);
        helper.addQuery("apikey", REST_API_KEY);
        return helper.build();
    }

    public URI getByClubId(String clubId) {
        UriBuilderHelper helper = new UriBuilderHelper(REST_CLUB + "%s/", clubId);
        helper.addQuery("apikey", REST_API_KEY);
        return helper.build();
    }


    public URI searchClub(String q) {

        String s = REST_CLUB + "search/";
        UriBuilderHelper helper = new UriBuilderHelper(s);

        helper.addQuery("apikey", REST_API_KEY).addQuery("q", q);

        return helper.build();
    }


    public URI getByClubIdTeams(String id) {

        String apiKey = "apikey=%s";
        String tempUrl = REST_CLUB + "%s/teams?";
        return buildUri(tempUrl + apiKey, id, REST_API_KEY);
    }


    public URI getLeagues() {
        return buildUri(REST_LEAGUE + "?apikey=%s", REST_API_KEY);
    }

    public URI getByLeagueByGroupGames(String leagueCode, String groupId, String clubId, String gameStatus, String limit, String clubId){
        return buildUri(REST_LEAGUE + "%s/groups/%s/games?apikey=%s&status=%s&limit=%s&club=%s", leagueCode, groupId, REST_API_KEY, gameStatus, limit, clubId);
    }

    public URI getByLeagueIdGroups(String leagueId) {
        return buildUri(REST_LEAGUE + "%s/groups?apikey=%s", leagueId, REST_API_KEY);
    }

    public URI getByLeagueByGroupGames(String leagueCode, String groupId) {
        return buildUri(REST_LEAGUE + "%s/groups/%s/games?apikey=%s", leagueCode, groupId, REST_API_KEY);
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

            uri.append(name + "=%s&");
            paramArray.add(param);
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



    public URI buildUri(String URL, String... param) {

        String urlString = String.format(URL, param);

        URI uri = URI.create(urlString);
        return uri;
    }
}
