package myunihockey.ffhs.com.myunihockey.persistence.mapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.persistence.dto.Club;
import myunihockey.ffhs.com.myunihockey.persistence.dto.Game;

/**
 * Created by Denis Bittante on 19.05.2015.
 */
public class GameMapper extends AbstractXMLMapper<Game> {

    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyy");


    @Override
    public List<Game> parse(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<Game> entries = new ArrayList();


        parser.require(XmlPullParser.START_TAG, ns, "games");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }


            int id = -1;
            int leaguecode = -1;
            String group = null;
            boolean played = false;
            int goalshome = -1;
            int goalsaway = -1;
            String hometeam_id = null;
            String awayteam_id = null;
            String hometeam_name = null;
            String awayteam_name = null;
            String date = null;
            String time = null;


            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("game")) {

                int attributeCount = parser.getAttributeCount();
                if (attributeCount == -1) {
                    break;
                }
                for (int i = 0; i < attributeCount; i++) {

                    String attributeValue = parser.getAttributeName(i);
                    switch (attributeValue) {
                        case "id":
                            id = Integer.valueOf(parser.getAttributeValue(i));
                            break;
                        case "leaguecode":
                            leaguecode = Integer.valueOf(parser.getAttributeValue(i));
                            break;
                        case "group":
                            group = parser.getAttributeValue(i);
                            break;
                        case "played":
                            played = Boolean.valueOf(parser.getAttributeValue(i));
                            break;
                        case "goalshome":
                            goalshome = Integer.valueOf(parser.getAttributeValue(i));
                            break;
                        case "goalsaway":
                            goalsaway = Integer.valueOf(parser.getAttributeValue(i));
                            break;
                        case "hometeam_id":
                            hometeam_id = parser.getAttributeValue(i);
                            break;
                        case "awayteam_id":
                            awayteam_id = parser.getAttributeValue(i);
                            break;
                        case "hometeam_name":
                            hometeam_name = parser.getAttributeValue(i);
                            break;
                        case "awayteam_name":
                            awayteam_name = parser.getAttributeValue(i);
                            break;
                        case "date":
                            date = parser.getAttributeValue(i);
                            break;
                        case "time":
                            time = parser.getAttributeValue(i);
                            break;

                    }

                }

                Game g = new Game(id, leaguecode, group, hometeam_id, hometeam_name, awayteam_id, awayteam_name, goalshome, goalsaway, date, time);
                entries.add(g);

            } else {
                skip(parser);
            }
        }


        return entries;
    }
}
