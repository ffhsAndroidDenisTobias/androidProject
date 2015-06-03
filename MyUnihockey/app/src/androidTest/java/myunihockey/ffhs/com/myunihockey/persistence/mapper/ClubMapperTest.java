package myunihockey.ffhs.com.myunihockey.persistence.mapper;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.persistence.dto.Club;
import myunihockey.ffhs.com.myunihockey.rest.RestConnector;
import myunihockey.ffhs.com.myunihockey.rest.UnihockeyRestFactory;

/**
 * Created by Denis Bittante on 18.05.2015.
 */
public class ClubMapperTest extends TestCase {

    ClubMapper testee = null;
    InputStream in = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        testee = new ClubMapper();
        in = new RestConnector().callRest(new UnihockeyRestFactory().getAllClubs());

    }

    public void testTesteeWorks() throws IOException, XmlPullParserException {
        List<Club> parse = testee.parse(in);

        Assert.assertEquals(418, parse.size());
    }
}
