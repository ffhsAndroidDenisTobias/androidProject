package myunihockey.ffhs.com.myunihockey.persistence.mapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.persistence.dto.Club;

/**
 * Created by Denis Bittante on 18.05.2015.
 */
public class ClubMapper extends AbstractXMLMapper<Club> {

    @Override
    List<Club> parse(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<Club> entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "clubs");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("club")) {

                Integer id = Integer.valueOf(parser.getAttributeValue(0));
                String clubname = readText(parser);
                Club c = new Club(id, clubname);

                entries.add(c);
            } else {
                skip(parser);
            }
        }
        return entries;
    }


}
