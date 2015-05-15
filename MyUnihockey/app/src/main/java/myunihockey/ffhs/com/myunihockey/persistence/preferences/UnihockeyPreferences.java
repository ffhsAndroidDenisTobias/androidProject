package myunihockey.ffhs.com.myunihockey.persistence.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Objects;

/**
 * Created by Denis Bittante on 15.05.2015.
 */
public class UnihockeyPreferences {

    private static final String UNIHOCKEYPREF = "UNIHOCKEYPREF";

    private SharedPreferences settings;

    public enum UnihockeyPref {
        PREFERENCE_TEAM,
        PREFERENCE_CLUB,
        FIRSTSTART


    }

    private Activity activity;

    public UnihockeyPreferences(Activity activity) {
        this.activity = activity;
        settings = this.activity.getSharedPreferences(UNIHOCKEYPREF, Context.MODE_PRIVATE);

    }

    public boolean isFirstStart() {

        return settings.getBoolean(UnihockeyPref.FIRSTSTART.name(), true);
    }

    public void setFirstStart(boolean isFirst) {
        settings.edit().putBoolean(UnihockeyPref.FIRSTSTART.name(), isFirst).commit();

    }


    public String getPreference(UnihockeyPref unihockeyPref) {
        return settings.getString(unihockeyPref.name(), "");
    }

    public void setPreference(UnihockeyPref unihockeyPref, String prefValue) {
        settings.edit().putString(unihockeyPref.name(), prefValue).commit();
    }

}
