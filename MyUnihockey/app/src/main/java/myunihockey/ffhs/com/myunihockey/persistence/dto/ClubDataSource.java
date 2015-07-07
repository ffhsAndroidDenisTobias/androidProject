package myunihockey.ffhs.com.myunihockey.persistence.dto;

import android.content.ContentValues;
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
import myunihockey.ffhs.com.myunihockey.persistence.mapper.ClubMapper;
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

    public void openReadable() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public void insertClub(InputStream inputStream) {

        ClubMapper clubMapper = new ClubMapper();
        try {
            List<Club> clubs = clubMapper.parse(inputStream);
            open();

            Log.d("ClubDataSource", "Start Transaction");

            for (Club club : clubs) {
                database.execSQL(insertClub(club));
            }


        } catch (SQLException e) {
            Log.e("Error in ClubDataSource", "An SQL Exception occured ", e);
        } catch (XmlPullParserException e) {
            Log.e("Error in ClubDataSource", "An XmlPullParserException occured ", e);
        } catch (IOException e) {
            Log.e("Error in ClubDataSource", "An IOException occured ", e);
        } finally {
            close();
        }
    }

    public String insertClub(Club club) {


        String statement = "INSERT INTO clubs (club_id, clubName) "
                + "VALUES ('" + club.getId()
                + "', '" + club.getClubName().replaceAll("\'", " ")
                + "');";
        return statement;


       /* ContentValues contentValues = new ContentValues();

        contentValues.put(SqlliteHelper.KEY_CLUB_ID, club.getId());
        contentValues.put(SqlliteHelper.KEY_CLUB_NAME, club.getClubName().replaceAll("\'", " "));
        return contentValues; */
    }


    public List<Club> getAllClubs() {
        try {
            openReadable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Club> clubArray = new ArrayList<Club>();

        Cursor cursor = database.query(SqlliteHelper.TABLE_CLUB,
                allColumns, null, null, null, null, null);


        while (cursor.moveToNext()) {
            Log.d("CursorTest: ", cursor.getString(1));
            Club club = cursorToClub(cursor);
            clubArray.add(club);
        }
        // make sure to close the cursor
        cursor.close();
        close();
        return clubArray;

    }


    public int getCount() {
        try {
            openReadable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = database.query(SqlliteHelper.TABLE_CLUB,
                allColumns, null, null, null, null, null);

        int count = cursor.getCount();

        cursor.close();
        close();

        return count;

    }


    private Club cursorToClub(Cursor cursor) {
        Club club = new Club();
        club.setId(cursor.getInt(0));
        club.setClubName(cursor.getString(1));
        return club;

    }

}
