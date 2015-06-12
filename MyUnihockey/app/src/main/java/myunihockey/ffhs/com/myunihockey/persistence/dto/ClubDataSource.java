package myunihockey.ffhs.com.myunihockey.persistence.dto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.persistence.SqlliteHelper;
import myunihockey.ffhs.com.myunihockey.persistence.mapper.GameMapper;

/**
 * Created by Denis Bittante on 25.05.2015.
 */
public class ClubDataSource {


    // Database fields
    private SQLiteDatabase database;
    private SqlliteHelper dbHelper;
    private String[] allColumns = {SqlliteHelper.KEY_CLUB_ID,
            SqlliteHelper.KEY_CLUB_NAME};


    public ClubDataSource(Context context) {
        dbHelper = new SqlliteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public void insertClub(InputStream inputStream) {

        GameMapper gameMapper = new GameMapper();
        try {
            List<Club> clubs = gameMapper.parse(inputStream);
            open();

            database.beginTransaction();
            for (Club club : clubs) {
                database.rawQuery(insertClub(club), null);
            }
            database.endTransaction();


        } catch (SQLException e) {

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String insertClub(Club club) {
        String statement = "INSERT INTO clubs (id, clubName) "
                + "VALUES ('" + club.getId()
                + "', '" + club.getClubName()
                + "');";
        return statement;
    }


    public List<Club> getAllClubs() {
        List<Club> comments = new ArrayList<Club>();

        Cursor cursor = database.query(SqlliteHelper.TABLE_CLUB,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.d("CursorTest: ", cursor.getString(1));
            Club comment = cursorToClub(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;

    }


    private Club cursorToClub(Cursor cursor) {
        Club club = new Club();
        club.setId(cursor.getInt(0));
        club.setClubName(cursor.getString(1));
        return club;

    }

}
