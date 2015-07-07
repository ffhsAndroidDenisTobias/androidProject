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
import myunihockey.ffhs.com.myunihockey.persistence.mapper.TeamMapper;

/**
 * Created by Denis Bittante on 25.05.2015.
 */
public class TeamDataSource {


    // Database fields
    private SQLiteDatabase database;
    private SqlliteHelper dbHelper;
    private String[] allColumns = {
            SqlliteHelper.KEY_TEAM_ID,
            SqlliteHelper.KEY_TEAM_NAME,
            SqlliteHelper.KEY_CLUB_ID,
            SqlliteHelper.KEY_LEAGUETEXT,
            SqlliteHelper.KEY_GROUP,
            SqlliteHelper.KEY_GROUP_TEXT,
            SqlliteHelper.KEY_LEAGUECODE
    };


    public TeamDataSource(Context context) {
        dbHelper = new SqlliteHelper(context);
    }


    public void openReadable() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public void insertTeam(InputStream inputStream) {
        TeamMapper teamMapper = new TeamMapper();
        try {
            openReadable();
            List<Team> parse = teamMapper.parse(inputStream);

            for (Team team : parse) {
                database.execSQL(insertTeam(team));
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();

    }


    public String insertTeam(Team team) {
        String statement = "INSERT INTO teams (id, teamName, club_id, leaguetext, groupName, grouptext, leaguecode) "
                + "VALUES ('" + team.getId()
                + "', '" + team.getTeamName()
                + "', '" + team.getClub_id()
                + "', '" + team.getLeaguetext()
                + "', '" + team.getGroup()
                + "', '" + team.getGrouptext()
                + "', '" + team.getLeaguecode()
                + "');";
        return statement;
    }


    public List<Team> getAllTeams() {
        List<Team> teamList = new ArrayList<Team>();
        try {
            openReadable();

            Cursor cursor = database.query(SqlliteHelper.TABLE_TEAM,
                    allColumns, null, null, null, null, null);

            while (cursor.moveToNext()) {
                Log.d("CursorTest: ", cursor.getString(1));
                Team comment = cursorToTeam(cursor);
                teamList.add(comment);

            }
            // make sure to close the cursor


            cursor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return teamList;

    }


    public int getCount() {
        try {
            openReadable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = database.query(SqlliteHelper.TABLE_TEAM,
                allColumns, null, null, null, null, null);
        int count = cursor.getCount();

        cursor.close();

        close();

        return count;

    }


    private Team cursorToTeam(Cursor cursor) {
        Team team = new Team();
        team.setId(cursor.getInt(0));
        team.setTeamName(cursor.getString(1));
        team.setClub_id(cursor.getInt(2));
        team.setLeaguetext(cursor.getString(3));
        team.setGroup(cursor.getInt(4));
        team.setGrouptext(cursor.getString(5));
        team.setLeaguecode(cursor.getInt(6));
        return team;
    }
}
