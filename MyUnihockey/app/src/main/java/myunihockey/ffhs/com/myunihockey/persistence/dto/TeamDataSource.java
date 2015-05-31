package myunihockey.ffhs.com.myunihockey.persistence.dto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.persistence.SqlliteHelper;

/**
 * Created by Denis Bittante on 25.05.2015.
 */
public class TeamDataSource {


    // Database fields
    private SQLiteDatabase database;
    private SqlliteHelper dbHelper;
    private String[] allColumns = {SqlliteHelper.KEY_GAME_ID,
            SqlliteHelper.KEY_GAME_AWAYTEAM_ID,
            SqlliteHelper.KEY_GAME_AWAYTEAM_NAME,
            SqlliteHelper.KEY_GAME_HOMETEAM_ID,
            SqlliteHelper.KEY_GAME_HOMETEAM_NAME,
            SqlliteHelper.KEY_GAME_GOALSHOME,
            SqlliteHelper.KEY_GAME_GOALSAWAY,
            SqlliteHelper.KEY_GAME_LEAGUECODE,
            SqlliteHelper.KEY_GAME_GROUP,
            SqlliteHelper.KEY_GAME_PLAYED,
            SqlliteHelper.KEY_GAME_DATE,
            SqlliteHelper.KEY_GAME_TIME
            };


    public TeamDataSource(Context context) {
        dbHelper = new SqlliteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public String insertTeam(Team team) {
        String statement = "INSERT INTO teams (id, teamName, club_id, leaguetext, group, grouptext, leaguecode) "
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
        List<Team> comments = new ArrayList<Team>();

        Cursor cursor = database.query(SqlliteHelper.TABLE_TEAM,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Team comment = cursorToTeam(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;

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
