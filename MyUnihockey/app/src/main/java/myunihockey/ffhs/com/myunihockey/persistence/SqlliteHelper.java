package myunihockey.ffhs.com.myunihockey.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Denis Bittante on 28.03.2015.
 */
public class SqlliteHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    private static final String DATABASE_NAME = "unihockey.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_LEAGUE = "leagues";
    private static final String TABLE_GROUP = "groups";
    private static final String TABLE_GAME = "games";
    private static final String TABLE_TEAM = "teams";
    private static final String TABLE_CLUB = "clubs";
    private static final String TABLE_TEAM_HAS_GAME = "team_has_games";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // TEAM Table - column names
    private static final String KEY_TEAM_NAME = "teamName";
    private static final String KEY_CLUB_ID = "club_id";
    private static final String KEY_LEAGUETEXT = "leaguetext";
    private static final String KEY_GROUP = "group";
    private static final String KEY_GROUP_TEXT = "grouptext";
    private static final String KEY_LEAGUECODE = "leaguecode";

    // CLUB Table - column names
    private static final String KEY_CLUB_NAME = "clubName";


    // Database creation sql statements
    // Table Create Statements
    // Team table create statement
    private static final String CREATE_TABLE_TEAM = "CREATE TABLE "
            + TABLE_TEAM + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TEAM_NAME+ " TEXT,"
            + KEY_CLUB_ID + " INTEGER,"
            + KEY_LEAGUETEXT + " TEXT,"
            + KEY_GROUP + " INTEGER,"
            + KEY_GROUP_TEXT + " TEXT,"
            + KEY_LEAGUECODE + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME"
            + ")";

    // Club table create statement
    private static final String CREATE_TABLE_CLUB = "CREATE TABLE "
            + TABLE_CLUB + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CLUB_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME"
            + ")";

    public SqlliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_CLUB);
        database.execSQL(CREATE_TABLE_TEAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SqlliteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLUB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        onCreate(db);
    }

}
