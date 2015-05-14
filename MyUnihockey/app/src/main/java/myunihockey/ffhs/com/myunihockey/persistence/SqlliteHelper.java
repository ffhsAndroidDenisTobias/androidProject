package myunihockey.ffhs.com.myunihockey.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.ref.SoftReference;

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
    private static final String KEY_LEAGUETEXT = "leaguetext";
    private static final String KEY_LEAGUECODE = "leaguecode";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_WERTUNGSMODUS_ID = "wertungsmodus_id";
    // TEAM Table - column names
    private static final String KEY_TEAM_NAME = "teamName";
    private static final String KEY_CLUB_ID = "club_id";
    private static final String KEY_GROUP = "group";

    private static final String KEY_GROUP_TEXT = "grouptext";

    // CLUB Table - column names
    private static final String KEY_CLUB_NAME = "clubName";
    // League Table - column names
    private static final String KEY_FIELD_SIZE = "fieldsize";
    private static final String KEY_GROUP_ID = "group_id";

    // GROUP Table - column names
    private static final String KEY_WERTUNGSMODUS = "wertungsmodus";
    private static final String KEY_LEAGUETYPE = "leaguetype";
    private static final String KEY_FIELDSIZE = "fieldsize";

    // GAME Table - column names
    private static final String KEY_GAME_LEAGUECODE = "leaguecode";
    private static final String KEY_GAME_GROUP = "group";
    private static final String KEY_GAME_SEASON = "season";
    private static final String KEY_GAME_LEAGUETYPE = "leaguetype";
    private static final String KEY_GAME_LEAGUETEXT = "leaguetext";
    private static final String KEY_GAME_FIELDSIZE = "fieldsize";
    private static final String KEY_GAME_PLAYED = "played";
    private static final String KEY_GAME_GOALSHOME = "goalshome";
    private static final String KEY_GAME_GOALSAWAY = "goalsaway";
    private static final String KEY_GAME_OVERTIME = "overtime";
    private static final String KEY_GAME_PENALTY = "penaltyshooting";
    private static final String KEY_GAME_FORFAIT = "forfait";
    private static final String KEY_GAME_CANCELED = "canceled";
    private static final String KEY_GAME_ORGANIZER = "organizer";
    private static final String KEY_GAME_ORGANIZER_ID = "organizer_id";
    private static final String KEY_GAME_EVENTTYPE = "eventtype";
    private static final String KEY_GAME_HOMETEAM_ID = "hometeam_id";
    private static final String KEY_GAME_AWAYTEAM_ID = "awayteam_id";
    private static final String KEY_GAME_HOMETEAM_NAME = "hometeam_name";
    private static final String KEY_GAME_AWAYTEAM_NAME = "awayteam_name";

    // TEAM_HAS_GAME Table - column names
    private static final String KEY_THG_TEAM_ID = "team_id";
    private static final String KEY_THG_GAME_ID = "game_id";
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

    // League table create statement
    private static final String CREATE_TABLE_LEAGUE = "CREATE TABLE "
            + TABLE_LEAGUE + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_LEAGUETEXT + " TEXT,"
            + KEY_FIELD_SIZE + " TEXT,"
            + KEY_WERTUNGSMODUS_ID + " TEXT,"
            + KEY_GROUP_ID + " TEXT"
            + ")";

    // Group table create statements
    private static final String CREATE_TABLE_GROUP = "CREATE TABLE "
            + TABLE_GROUP + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_WERTUNGSMODUS_ID + " INTEGER,"
            + KEY_WERTUNGSMODUS + " TEXT,"
            + KEY_LEAGUETYPE + " TEXT,"
            + KEY_FIELDSIZE + " TEXT,"
            + KEY_LEAGUETEXT + " TEXT,"
            + KEY_LEAGUECODE + " Integer"
            + ")";

    // Game table create statements
    private static final String CREATE_TABLE_GAME = "CREATE TABLE "
            + TABLE_GAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_GAME_LEAGUECODE + " INTEGER,"
            + KEY_GAME_GROUP + " TEXT,"
            + KEY_GAME_SEASON + " TEXT,"
            + KEY_GAME_LEAGUETYPE + " TEXT,"
            + KEY_GAME_LEAGUETEXT + " TEXT,"
            + KEY_GAME_FIELDSIZE + " TEXT,"
            + KEY_GAME_PLAYED + " BOOLEAN,"
            + KEY_GAME_GOALSHOME + " INTEGER,"
            + KEY_GAME_GOALSAWAY + " INTEGER,"
            + KEY_GAME_OVERTIME + " BOOLEAN,"
            + KEY_GAME_PENALTY + " BOOLEAN,"
            + KEY_GAME_FORFAIT + " BOOLEAN,"
            + KEY_GAME_CANCELED + " BOOLEAN,"
            + KEY_GAME_ORGANIZER + " TEXT,"
            + KEY_GAME_ORGANIZER_ID + " TEXT,"
            + KEY_GAME_EVENTTYPE + " TEXT,"
            + KEY_GAME_HOMETEAM_ID + " INTEGER,"
            + KEY_GAME_AWAYTEAM_ID + " INTEGER,"
            + KEY_GAME_HOMETEAM_NAME + " TEXT,"
            + KEY_GAME_AWAYTEAM_NAME + " TEXT"
            + ")";

    // Team_has_game table create statements
    private static final String CREATE_TABLE_TEAM_HAS_GAME = "CREATE TABLE "
            + TABLE_TEAM_HAS_GAME + "("
            + KEY_THG_TEAM_ID + " INTEGER,"
            + KEY_THG_GAME_ID + " INTEGER"
            + ")";

    public SqlliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_CLUB);
        database.execSQL(CREATE_TABLE_TEAM);
        database.execSQL(CREATE_TABLE_GAME);
        database.execSQL(CREATE_TABLE_GROUP);
        database.execSQL(CREATE_TABLE_LEAGUE);
        database.execSQL(CREATE_TABLE_TEAM_HAS_GAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SqlliteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLUB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM_HAS_GAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEAGUE);
        onCreate(db);
    }

}
