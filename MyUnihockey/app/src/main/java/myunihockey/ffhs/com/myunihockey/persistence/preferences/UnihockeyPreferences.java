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
        PREFERENCE_CLUB
    }


    private static final String FIRSTSTART = "firstStart";
    private Activity activity;

    public UnihockeyPreferences(Activity activity) {
        this.activity = activity;
        settings = this.activity.getSharedPreferences(UNIHOCKEYPREF, Context.MODE_PRIVATE);

    }

    public boolean isFirstStart() {

        return settings.getBoolean(FIRSTSTART, true);
    }

    public String getPreference(UnihockeyPref unihockeyPref) {
        return settings.getString(unihockeyPref.name(), "");
    }

    public void setPreference(UnihockeyPref unihockeyPref, String prefValue) {
        settings.edit().putString(unihockeyPref.name(), prefValue).commit();
    }

}
