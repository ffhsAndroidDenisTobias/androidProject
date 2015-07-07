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
public class GameDataSource {


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
            SqlliteHelper.KEY_GAME_TIME,
            SqlliteHelper.KEY_GAME_ORGANIZER,
            SqlliteHelper.KEY_GAME_ORGANIZER_ID
    };


    public GameDataSource(Context context) {
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


    public String insertClub(Game game) {
        String statement = "INSERT INTO games (id, leaguecode, group, played, goalshome, goalsaway, hometeam_id, awayteam_id, hometeam_name, awayteam_name, datum, zeit, organizer, organizer_id) "
                + "VALUES ('" + game.getId()
                + "', '" + game.getLeaguecode()
                + "', '" + game.getGroup()
                + "', '" + game.isPlayed()
                + "', '" + game.getGoalshome()
                + "', '" + game.getGoalsaway()
                + "', '" + game.getHometeam_id()
                + "', '" + game.getAwayteam_id()
                + "', '" + game.getHometeam_name()
                + "', '" + game.getAwayteam_name()
                + "', '" + game.getDate()
                + "', '" + game.getTime()
                + "', '" + game.getOrganizer()
                + "', '" + game.getOrganizer_id()
                + "');";
        return statement;
    }


    public List<Game> getAllGames() {
        List<Game> comments = new ArrayList<Game>();
        try {
            openReadable();

            Cursor cursor = database.query(SqlliteHelper.TABLE_GAME,
                    allColumns, null, null, null, null, null);


            while (cursor.moveToNext()) {
                Game comment = cursorToGame(cursor);
                comments.add(comment);

            }
            cursor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return comments;

    }


    private Game cursorToGame(Cursor cursor) {
        Game game = new Game();
        game.setId(cursor.getInt(0));
        game.setAwayteam_id(cursor.getString(1));
        game.setAwayteam_name(cursor.getString(2));
        game.setHometeam_id(cursor.getString(3));
        game.setHometeam_name(cursor.getString(4));
        game.setGoalshome(cursor.getInt(5));
        game.setGoalsaway(cursor.getInt(6));
        game.setLeaguecode(cursor.getInt(7));
        game.setGroup(cursor.getString(8));
        game.setPlayed(cursor.getString(9));
        game.setDate(cursor.getString(10));
        game.setTime(cursor.getString(11));

        return game;
    }
}
