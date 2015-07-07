package myunihockey.ffhs.com.myunihockey.activities.wizard;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import myunihockey.ffhs.com.myunihockey.R;
import myunihockey.ffhs.com.myunihockey.activities.wizard.WizardPage;
import myunihockey.ffhs.com.myunihockey.persistence.preferences.UnihockeyPreferences;

/**
 * Created by Denis Bittante on 16.05.2015.
 */
public abstract class AbstractWizard extends Activity {
    protected ArrayList<WizardPage> wizardContent = new ArrayList<WizardPage>();
    protected UnihockeyPreferences preferences;
    private int pageCount = 0;
    private Button btnnext;
    private Button btnprev;
    private Spinner spinner;
    private TextView txtViewHeader;
    private TextView txtViewSubheading;
    private ArrayList<RadioButton> radioButtons;


    protected void initWizard() {

        preferences = new UnihockeyPreferences(this);
        setSpinner();
        setUpButtons();
        setUpHeaders();
        setUpProgressBar();

        setUpPage(0);
    }


    protected abstract void startMainPage(boolean firstStart);

    protected void setUpPage(final int i) {

        pageCount = i;

        if (i >= wizardContent.size()) {
            startMainPage(false);
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


        btnnext.setText(getString(R.string.navNext));
        btnprev.setText(getString(R.string.navBack));

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

    protected void setUpProgressBar() {

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

    protected void setUpHeaders() {
        txtViewHeader = (TextView) findViewById(R.id.txtviewheader);
        txtViewSubheading = (TextView) findViewById(R.id.txtviewsubheading);
    }

    protected void setUpButtons() {
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

    protected void setSpinner() {
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
                        UnihockeyPreferences.UnihockeyPref unihockeyPref = UnihockeyPreferences.UnihockeyPref.valueOf(wizardPage.getPreferenceKey());

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

            UnihockeyPreferences.UnihockeyPref unihockeyPref = UnihockeyPreferences.UnihockeyPref.valueOf(wizardPage.getPreferenceKey());
            preference = preferences.getPreference(unihockeyPref);
        }
        int position = 0;
        if (preference != "") {

            position = adapter.getPosition(preference);
        }
        return position;
    }
}
