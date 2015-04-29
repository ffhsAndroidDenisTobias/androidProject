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

        testUri(testee.getAllClubs());
    }

    public void testSearchClub() {
        String q = "testquery";
        URI testuri = testee.searchClub(q);
        testUri(testuri);
        expectedQuery(testuri, "q=testquery");
    }


    public void testGetByClubId() {
        String clubId = "1234";
        URI byClubId = testee.getByClubId(clubId);
        testUri(byClubId);
        expectedPath(byClubId, "/rest/v1.0/clubs/1234/");

    }

    public void testClub() {
        String testquery = "query";
        testUri(testee.searchClub(testquery));

    }

    private void testUri(URI testuri) {
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

    private void expectedPath(URI testuri, String expectedQuery) {

        Assert.assertNotNull(testuri);
        String query = testuri.getPath();
        Assert.assertNotNull(query);
        Assert.assertEquals(expectedQuery,query);
    }

}
