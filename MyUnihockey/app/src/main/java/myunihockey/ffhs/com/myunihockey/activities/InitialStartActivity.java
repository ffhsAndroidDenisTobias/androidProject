package myunihockey.ffhs.com.myunihockey.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Window;

import java.util.ArrayList;

import myunihockey.ffhs.com.myunihockey.R;
import myunihockey.ffhs.com.myunihockey.activities.wizard.AbstractWizard;
import myunihockey.ffhs.com.myunihockey.activities.wizard.WizardPage;
import myunihockey.ffhs.com.myunihockey.binder.UnihockeyDataBinder;

import myunihockey.ffhs.com.myunihockey.persistence.preferences.UnihockeyPreferences;
import myunihockey.ffhs.com.myunihockey.services.UnihockeyDataService;

import static myunihockey.ffhs.com.myunihockey.persistence.preferences.UnihockeyPreferences.*;


public class InitialStartActivity extends AbstractWizard {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_start);

        //TODO: make Preferences be an Interface
        preferences = new UnihockeyPreferences(this);

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

        ArrayList<String> clubs = new ArrayList<String>();
        clubs.add("club1");
        clubs.add("club2");
        clubs.add("club3");
        clubs.add("club4");
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
        super.onDestroy();        // destroyService();

    }


    private static Handler callback = new Handler();

    private static ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ((UnihockeyDataBinder) service).setHandler(callback);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };


    private void destroyService() {
        callback.removeCallbacksAndMessages(null);
        unbindService(serviceConnection);
        stopService(new Intent(this, UnihockeyDataService.class));
        //super.onDestroy();
    }


    private void connectToService() {
        final Intent unihockeyServiceIntent = new Intent(this, UnihockeyDataService.class);
        bindService(unihockeyServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

    }
}