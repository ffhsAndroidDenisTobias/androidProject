package myunihockey.ffhs.com.myunihockey.persistence.mapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.persistence.dto.Team;

/**
 * Created by Denis Bittante on 18.05.2015.
 */
public class TeamMapper extends AbstractXMLMapper<Team> {

    @Override
    List<Team> parse(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<Team> entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "teams");
        Integer club_id = Integer.valueOf(parser.getAttributeValue(0));
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("team")) {

                Integer id = Integer.valueOf(parser.getAttributeValue(0));
                Integer leaguecode = Integer.valueOf(parser.getAttributeValue(1));
                String teamname = parser.getAttributeValue(2);
                Integer group = Integer.valueOf(parser.getAttributeValue(4));
                String grouptext = parser.getAttributeValue(5);
                String teamFullName = readText(parser);
                Team c = new Team(teamFullName, teamname, grouptext, id, club_id, group, leaguecode);

                entries.add(c);
            } else {
                skip(parser);
            }
        }
        return entries;
    }


}
