package myunihockey.ffhs.com.myunihockey.binder;

import android.os.Binder;
import android.os.Handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.persistence.RestConnector;
import myunihockey.ffhs.com.myunihockey.services.Team;

/**
 * Created by Denis Bittante on 22.04.2015.
 */
public class UnihockeyDataBinder extends Binder {

    private Handler callback;

    private List<Team> teams;

    public List<Team> getTeams(String verein) {


        RestConnector restConnector = new RestConnector();

        try {
            String response = restConnector.callRest("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public void setHandler(final Handler callback) {
        this.callback = callback;
    }


}
