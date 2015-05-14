package myunihockey.ffhs.com.myunihockey.activities;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import myunihockey.ffhs.com.myunihockey.R;
import myunihockey.ffhs.com.myunihockey.binder.UnihockeyDataBinder;
import myunihockey.ffhs.com.myunihockey.services.UnihockeyDataService;


public class InitialStartActivity extends ActionBarActivity {

    private static final String MYPREFERENCES = "MY_PREFERENCES";
    private static final String FIRSTSTART = "firstStart";

    private int pageCount = 0;
    private Button btnnext;
    private Button btnprev;
    private Spinner spinner;
    private TextView txtViewHeader;
    private TextView txtViewSubheading;
    private ArrayList<WizardPage> wizardContent = new ArrayList<WizardPage>();
    private ArrayList<RadioButton> radioButtons;

    public class WizardPage {

        private String title;
        private String subtitle;
        private boolean spinnerVisible;

        public WizardPage(String title, String subtitle) {
            createWizard(title, subtitle, false);
        }

        public WizardPage(String title, String subtitle, boolean spinnerVisible) {
            createWizard(title, subtitle, spinnerVisible);

        }

        private void createWizard(String title, String subtitle, boolean spinnerVisible) {
            this.title = title;
            this.subtitle = subtitle;
            this.spinnerVisible = spinnerVisible;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getTitle() {
            return title;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_start);


        //Check properties

        SharedPreferences settings = getSharedPreferences(MYPREFERENCES, MODE_PRIVATE);
        boolean firstStart = settings.getBoolean(FIRSTSTART, false);
        if (!firstStart) {
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
        mainIntent.putExtra(FIRSTSTART, firstStart);
        startActivity(mainIntent);

    }

    private void setUpPage(int i) {


        if (i > wizardContent.size()) {
            startMainPage(true);
            return;
        }

        for (int index = 0; index < radioButtons.size(); index++) {
            RadioButton radioButton = radioButtons.get(index);

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


    }

    private void setUpProgressBar() {

        radioButtons = new ArrayList<RadioButton>();
        LinearLayout progressLayout1 = (LinearLayout) findViewById(R.id.progressLayout);
        progressLayout1.removeAllViews();

        for (int i = 0; i < wizardContent.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
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
        spinner = (Spinner) findViewById(R.id.spn);
    }

    private void setSpinnerContent() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.clubs_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private void SetUpWizardContent() {
        wizardContent.add(new WizardPage("Welcome", "Before we start, may we know something of you? Which is your favorite..."));
        wizardContent.add(new WizardPage("Club", "Just select the one you like.", true));
        wizardContent.add(new WizardPage("Team", "You must have a favorite, right? ;-)", true));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyService();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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


    public void onClickStartService(View view) {

        showAlertDialog();


    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage("Hallo")
                .setPositiveButton("positivButton", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("Oh No!!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();

    }


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