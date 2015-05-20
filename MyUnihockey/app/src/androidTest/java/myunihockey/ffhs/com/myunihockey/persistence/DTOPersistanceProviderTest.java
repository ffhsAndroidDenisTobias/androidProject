package myunihockey.ffhs.com.myunihockey.persistence;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.net.URI;

import myunihockey.ffhs.com.myunihockey.persistence.dto.Club;
import myunihockey.ffhs.com.myunihockey.persistence.dto.DTOPersistanceProvider;

/**
 * Created by Tobi on 20.05.2015.
 */
public class DTOPersistanceProviderTest extends TestCase {

    public void testInsertClub() {
        Club testClub = new Club(10, "UHC FFHS");
        String testquery = "IINSERT INTO clubs (id, clubName) VALUES ('10', 'UHC FFHS');";
        DTOPersistanceProvider pp = new DTOPersistanceProvider();
        String query = pp.insertClub(testClub);
        Assert.assertEquals(testquery, query);
    }
}
