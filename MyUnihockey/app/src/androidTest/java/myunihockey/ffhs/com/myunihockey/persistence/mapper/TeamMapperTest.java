package myunihockey.ffhs.com.myunihockey.persistence.mapper;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.persistence.dto.Club;
import myunihockey.ffhs.com.myunihockey.persistence.dto.Team;
import myunihockey.ffhs.com.myunihockey.persistence.mapper.TeamMapper;
import myunihockey.ffhs.com.myunihockey.rest.RestConnector;
import myunihockey.ffhs.com.myunihockey.rest.UnihockeyRestFactory;

/**
 * Created by Denis Bittante on 18.05.2015.
 */
public class TeamMapperTest extends TestCase {

    TeamMapper testee = null;
    InputStream in = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        testee = new TeamMapper();
        in = new RestConnector().callRest(new UnihockeyRestFactory().getAllClubs());

    }

    public void testTesteeWorks() throws IOException, XmlPullParserException {
        List<Club> parse = testee.parse(in);

        Assert.assertEquals(419, parse.size());
    }
}
