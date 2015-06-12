package myunihockey.ffhs.com.myunihockey.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.R;
import myunihockey.ffhs.com.myunihockey.activities.wizard.AbstractWizard;
import myunihockey.ffhs.com.myunihockey.activities.wizard.WizardPage;
import myunihockey.ffhs.com.myunihockey.persistence.dto.Club;
import myunihockey.ffhs.com.myunihockey.services.UnihockeyDataService;

import static myunihockey.ffhs.com.myunihockey.persistence.preferences.UnihockeyPreferences.UnihockeyPref;


public class InitialStartActivity extends AbstractWizard {


    boolean isBound = false;
   private UnihockeyDataService unihockeyDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_start);


        doBindService();


        //TODO: make Preferences be an Interface

        //Check properties
        //if (!preferences.isFirstStart()) {
        //    startMainPage(false);
        // }

        //connectToService();
        //Check if the Service was already called once a day

        SetUpWizardContent();

        initWizard();


    }


    @Override
    protected void startMainPage(boolean firstStart) {
        preferences.setFirstStart(firstStart);
        Intent mainIntent = new Intent(this, MainCard.class);
        startActivity(mainIntent);

    }


    private void SetUpWizardContent() {
        wizardContent.add(new WizardPage("Welcome", "Before we start, may we know something of you? Which is your favorite..."));
        wizardContent.add(new WizardPage("Club", "Just select the one you like.", true, getclubs(), UnihockeyPref.PREFERENCE_CLUB.name()));
        wizardContent.add(new WizardPage("Team", "You must have a favorite, right? ;-)", true, getTeams(), UnihockeyPref.PREFERENCE_TEAM.name()));
    }


    private ArrayList<String> getclubs() {
        //Read from Database
        Log.d("getClubs", "wiesowiesowieso");
        List<Club> clubList = unihockeyDataService.loadClubsInDatabase(this);
        ArrayList<String> clubs = new ArrayList<String>();
        clubs.add("club1");
        clubList.addAll(clubList);
        return clubs;
    }

    private ArrayList<String> getTeams() {
        //Read from Database
        ArrayList<String> teams = new ArrayList<String>();
        teams.add("team1");
        teams.add("team2");
        teams.add("team3");
        teams.add("team4");
        return teams;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            unihockeyDataService = ((UnihockeyDataService.UnihockeyDataBinder) service).getService();
            Toast.makeText(InitialStartActivity.this, R.string.local_service_connected,
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            unihockeyDataService = null;
            Toast.makeText(InitialStartActivity.this, R.string.local_service_disconnected,
                    Toast.LENGTH_SHORT).show();
        }
    };

    void doBindService() {
        bindService(new Intent(InitialStartActivity.this, UnihockeyDataService.class), serviceConnection, Context.BIND_AUTO_CREATE);
        isBound = true;
    }

    void doUnbindService() {

        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }


}