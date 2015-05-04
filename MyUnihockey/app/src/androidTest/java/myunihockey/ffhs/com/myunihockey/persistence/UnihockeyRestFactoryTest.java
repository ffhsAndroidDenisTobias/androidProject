package myunihockey.ffhs.com.myunihockey.persistence;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.net.URI;

/**
 * Created by Denis Bittante on 29.04.2015.
 */

public class UnihockeyRestFactoryTest extends TestCase {

    UnihockeyRestFactory testee;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        testee = new UnihockeyRestFactory();
    }

    public void testGetAllClubs() {

        expectedMinimumUri(testee.getAllClubs());
    }

    public void testSearchClub() {
        String q = "testquery";
        URI testuri = testee.searchClub(q);
        expectedMinimumUri(testuri);
        expectedQuery(testuri, "q=testquery");
    }


    public void testGetByClubId() {
        String clubId = "1234";
        URI byClubId = testee.getClubById(clubId);
        expectedMinimumUri(byClubId);
        expectedPath(byClubId, "/rest/v1.0/clubs/1234/");
    }

    public void testClub() {
        String testquery = "query";
        URI testuri = testee.searchClub(testquery);
        expectedMinimumUri(testuri);
        expectedQuery(testuri, "q=query");
    }

    public void testGetLeagues() {
        expectedMinimumUri(testee.getLeagues());
    }

    public void testGetGames(){
        String testquery = "query";

        String leagueId = "leagueId";
        String groupId = "groupId";
        URI testuri = testee.getGames(leagueId, groupId);
        expectedMinimumUri(testuri);
        expectedPath(testuri, "/rest/v1.0/leagues/leagueId/groups/groupId/games");
    }


    public void testGetGamesWithLeaguesArgs() {
        URI byLeagueByGroupGames = testee.getGames("leagueId", "groupId", "clubId", "gameStatus", "limit");
        expectedMinimumUri(byLeagueByGroupGames);

        expectedPath(byLeagueByGroupGames, "/rest/v1.0/leagues/leagueId/groups/groupId/games");
        expectedQuery(byLeagueByGroupGames, "club=clubId");
        expectedQuery(byLeagueByGroupGames, "limit=limit");
        expectedQuery(byLeagueByGroupGames, "status=gameStatus");
    }

    public void testGetByLeagueByGroupGamesWithNull() {
        URI byLeagueByGroupGames = testee.getGames("leagueId", "groupId", null, null, "limit");
        expectedMinimumUri(byLeagueByGroupGames);
        expectedExactQuery(byLeagueByGroupGames, "apikey=diqkOP74wp5UdhBQNKwk2f0SpBY=&limit=limit");
    }

    public void testGetByLeagueIdGroups() {
        URI byByLeagueIdGroups = testee.getByLeagueIdGroups("leagueId");
        expectedMinimumUri(byByLeagueIdGroups);
        expectedPath(byByLeagueIdGroups, "/rest/v1.0/leagues/leagueId/groups/");
    }


    private void expectedMinimumUri(URI testuri) {
        expectedHost(testuri);
        expectedApiKey(testuri);
    }

    private void expectedHost(URI testuri) {
        String expectedPath = "api.swissunihockey.ch";
        String rawPath = testuri.getHost();
        Assert.assertEquals(expectedPath, rawPath);
    }


    private void expectedApiKey(URI testuri) {
        String expectedQuery = "apikey=diqkOP74wp5UdhBQNKwk2f0SpBY=";
        expectedQuery(testuri, expectedQuery);
    }

    private void expectedQuery(URI testuri, String expectedQuery) {

        Assert.assertNotNull(testuri);
        String query = testuri.getQuery();
        Assert.assertNotNull(query);
        Assert.assertTrue(query, query.contains(expectedQuery));
    }

    private void expectedExactQuery(URI testuri, String expectedQuery) {

        Assert.assertNotNull(testuri);
        String query = testuri.getQuery();
        Assert.assertNotNull(query);
        Assert.assertEquals(expectedQuery, query);
    }


    private void expectedPath(URI testuri, String expectedQuery) {

        Assert.assertNotNull(testuri);
        String query = testuri.getPath();
        Assert.assertNotNull(query);
        Assert.assertEquals(expectedQuery, query);
    }

}
