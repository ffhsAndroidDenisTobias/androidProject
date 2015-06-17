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
    public static final String LOG = "DatabaseHelper";

    public static final String DATABASE_NAME = "unihockey.db";
    public static final int DATABASE_VERSION = 1;

    // Table Names
    public static final String TABLE_GAME = "games";
    public static final String TABLE_TEAM = "teams";
    public static final String TABLE_CLUB = "clubs";

    // Common column names
    public static final String KEY_ID = "id";
    public static final String KEY_LEAGUETEXT = "leaguetext";
    public static final String KEY_LEAGUECODE = "leaguecode";
    // TEAM Table - column names
    public static final String KEY_TEAM_ID = "id";
    public static final String KEY_TEAM_NAME = "teamName";
    public static final String KEY_CLUB_ID = "club_id";
    public static final String KEY_GROUP = "group";
    public static final String KEY_GROUP_TEXT = "grouptext";

    // CLUB Table - column names
    public static final String KEY_CLUB_NAME = "clubName";


    // GAME Table - column names
    public static final String KEY_GAME_ID = "id";
    public static final String KEY_GAME_LEAGUECODE = "leaguecode";
    public static final String KEY_GAME_GROUP = "group";
    public static final String KEY_GAME_SEASON = "season";
    public static final String KEY_GAME_LEAGUETYPE = "leaguetype";
    public static final String KEY_GAME_LEAGUETEXT = "leaguetext";
    public static final String KEY_GAME_FIELDSIZE = "fieldsize";
    public static final String KEY_GAME_PLAYED = "played";
    public static final String KEY_GAME_GOALSHOME = "goalshome";
    public static final String KEY_GAME_GOALSAWAY = "goalsaway";
    public static final String KEY_GAME_OVERTIME = "overtime";
    public static final String KEY_GAME_PENALTY = "penaltyshooting";
    public static final String KEY_GAME_FORFAIT = "forfait";
    public static final String KEY_GAME_CANCELED = "canceled";
    public static final String KEY_GAME_ORGANIZER = "organizer";
    public static final String KEY_GAME_ORGANIZER_ID = "organizer_id";
    public static final String KEY_GAME_EVENTTYPE = "eventtype";
    public static final String KEY_GAME_HOMETEAM_ID = "hometeam_id";
    public static final String KEY_GAME_AWAYTEAM_ID = "awayteam_id";
    public static final String KEY_GAME_HOMETEAM_NAME = "hometeam_name";
    public static final String KEY_GAME_AWAYTEAM_NAME = "awayteam_name";
    public static final String KEY_GAME_DATE = "date";
    public static final String KEY_GAME_TIME = "time";

    // Team table create statement
    private static final String CREATE_TABLE_TEAM = "CREATE TABLE "
            + TABLE_TEAM + "("
            + KEY_TEAM_ID + " INTEGER PRIMARY KEY,"
            + KEY_TEAM_NAME + " TEXT,"
            + KEY_CLUB_ID + " INTEGER,"
            + KEY_LEAGUETEXT + " TEXT,"
            + KEY_GROUP + " INTEGER,"
            + KEY_GROUP_TEXT + " TEXT,"
            + KEY_LEAGUECODE + " INTEGER"
            + ")";

    // Club table create statement
    private static final String CREATE_TABLE_CLUB = "CREATE TABLE "
            + TABLE_CLUB + "("
            + KEY_CLUB_ID + " INTEGER PRIMARY KEY,"
            + KEY_CLUB_NAME + " TEXT"
            + ")";


    // Game table create statements
    private static final String CREATE_TABLE_GAME = "CREATE TABLE "
            + TABLE_GAME + "("
            + KEY_GAME_ID + " INTEGER PRIMARY KEY, "
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


    public SqlliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_CLUB);
        //database.execSQL(CREATE_TABLE_TEAM);
        //database.execSQL(CREATE_TABLE_GAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SqlliteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLUB);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAME);

        onCreate(db);
    }

}
