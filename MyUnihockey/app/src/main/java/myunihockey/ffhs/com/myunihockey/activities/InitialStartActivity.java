package myunihockey.ffhs.com.myunihockey.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import myunihockey.ffhs.com.myunihockey.R;
import myunihockey.ffhs.com.myunihockey.activities.wizard.WizardPage;
import myunihockey.ffhs.com.myunihockey.binder.UnihockeyDataBinder;

import myunihockey.ffhs.com.myunihockey.persistence.preferences.UnihockeyPreferences;
import myunihockey.ffhs.com.myunihockey.services.UnihockeyDataService;

import static myunihockey.ffhs.com.myunihockey.persistence.preferences.UnihockeyPreferences.*;


public class InitialStartActivity extends Activity {


    private int pageCount = 0;
    private Button btnnext;
    private Button btnprev;
    private Spinner spinner;
    private TextView txtViewHeader;
    private TextView txtViewSubheading;
    private ArrayList<WizardPage> wizardContent = new ArrayList<WizardPage>();
    private ArrayList<RadioButton> radioButtons;
    private UnihockeyPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_start);
        preferences = new UnihockeyPreferences(this);

        //Check properties
        if (!preferences.isFirstStart()) {
            startMainPage(false);
        }

        //connectToService();

        SetUpWizardContent();

        setSpinner();
        setUpButtons();
        setUpHeaders();
        setUpProgressBar();

        setUpPage(0);
    }

    private void startMainPage(boolean firstStart) {
        Intent mainIntent = new Intent(this, MainCard.class);
        startActivity(mainIntent);

    }

    private void setUpPage(final int i) {

        pageCount = i;

        if (i >= wizardContent.size()) {
            startMainPage(true);
            return;
        }

        for (int index = 0; index < radioButtons.size(); index++) {
            RadioButton radioButton = radioButtons.get(index);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v instanceof RadioButton) {
                        RadioButton v1 = (RadioButton) v;
                        setUpPage((int) v1.getTag(R.id.TAG_WIZARD_PAGEID));
                    }
                }
            });

            radioButton.setChecked(false);
            if (index == i) {
                radioButton.setChecked(true);
            }
        }


        btnnext.setText("Next");
        btnprev.setText("Prev.");

        if (i == 0) {
            btnprev.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.INVISIBLE);
        } else {
            btnprev.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);

        }

        WizardPage wizardPage = wizardContent.get(i);
        txtViewHeader.setText(wizardPage.getTitle());
        txtViewSubheading.setText(wizardPage.getSubtitle());
        if (wizardPage.hasSpinner()) {
            setSpinnerContent(wizardPage.getValues(), i);
        }


    }


    private void setUpProgressBar() {

        radioButtons = new ArrayList<RadioButton>();
        LinearLayout progressLayout1 = (LinearLayout) findViewById(R.id.progressLayout);
        progressLayout1.removeAllViews();

        for (int i = 0; i < wizardContent.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setTag(R.id.TAG_WIZARD_PAGEID, i);
            radioButtons.add(radioButton);
            progressLayout1.addView(radioButton);
        }
    }

    private void setUpHeaders() {
        txtViewHeader = (TextView) findViewById(R.id.txtviewheader);
        txtViewSubheading = (TextView) findViewById(R.id.txtviewsubheading);
    }

    private void setUpButtons() {
        btnnext = (Button) findViewById(R.id.btnnext);
        btnprev = (Button) findViewById(R.id.btnprev);

        btnnext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pageCount++;
                setUpPage(pageCount);
            }
        });
        btnprev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pageCount--;
                setUpPage(pageCount);
            }
        });
    }

    private void setSpinner() {
        //spinner:
        spinner = (Spinner) findViewById(R.id.spinOptions);
    }

    private void setSpinnerContent(ArrayList<String> data, int pageindex) {

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, data);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spinner.setTag(R.id.TAG_WIZARD_PAGEID, pageindex);

        spinner.setAdapter(adapter);

        int position = getPreferedSelection(pageindex, adapter);


        spinner.setSelection(position);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent instanceof Spinner) {
                    Spinner spin = (Spinner) parent;
                    int a = (int) spin.getTag(R.id.TAG_WIZARD_PAGEID);
                    WizardPage wizardPage = wizardContent.get(a);

                    if (wizardPage.hasSpinner() && !(wizardPage.getPreferenceKey().isEmpty())) {

                        String selectedValue = spin.getSelectedItem().toString();
                        UnihockeyPref unihockeyPref = UnihockeyPref.valueOf(wizardPage.getPreferenceKey());
                        preferences.setPreference(unihockeyPref, selectedValue);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

    }

    private int getPreferedSelection(int pageindex, ArrayAdapter adapter) {
        WizardPage wizardPage = wizardContent.get(pageindex);

        String preference = "";
        if (wizardPage.hasSpinner() && !(wizardPage.getPreferenceKey().isEmpty())) {

            UnihockeyPref unihockeyPref = UnihockeyPref.valueOf(wizardPage.getPreferenceKey());
            preference = preferences.getPreference(unihockeyPref);
        }
        int position = 0;
        if (preference != "") {

            position = adapter.getPosition(preference);
        }
        return position;
    }

    private void SetUpWizardContent() {
        wizardContent.add(new WizardPage("Welcome", "Before we start, may we know something of you? Which is your favorite..."));
        wizardContent.add(new WizardPage("Club", "Just select the one you like.", true, getclubs(), UnihockeyPref.PREFERENCE_CLUB.name()));
        wizardContent.add(new WizardPage("Team", "You must have a favorite, right? ;-)", true, getTeams(), UnihockeyPref.PREFERENCE_TEAM.name()));
    }


    private ArrayList<String> getclubs()

    {
        ArrayList<String> clubs = new ArrayList<String>();
        clubs.add("club1");
        clubs.add("club2");
        clubs.add("club3");
        clubs.add("club4");
        return clubs;
    }

    private ArrayList<String> getTeams() {
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
        destroyService();
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