package myunihockey.ffhs.com.myunihockey.persistence;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.Preference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import myunihockey.ffhs.com.myunihockey.cards.CardInterface;

/**
 * Created by Denis Bittante on 26.04.2015.
 */
public class DataBaseAsyncTask extends AsyncTask<Preference, Integer, List<CardInterface>> {


    protected static final String KEYTEAMS = "prefTeams";

    @Override
    protected List<CardInterface> doInBackground(Preference... params) {

        ArrayList<CardInterface> cardInterfaces = new ArrayList<CardInterface>();

        for (Preference pref : params) {
            SharedPreferences sharedPreferences = pref.getSharedPreferences();

            Map<String, ?> all = sharedPreferences.getAll();

            if (all.containsKey(KEYTEAMS)) {

                Object teams = all.get(KEYTEAMS);

                // call SQL for teams

                // create List for Card

            }
        }
        return cardInterfaces;
    }
}

