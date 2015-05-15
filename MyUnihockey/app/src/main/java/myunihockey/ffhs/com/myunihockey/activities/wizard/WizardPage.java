package myunihockey.ffhs.com.myunihockey.activities.wizard;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WizardPage {

    private String title;
    private String subtitle;
    private boolean spinnerVisible;
    private ArrayList<String> values;
    private String preferenceKey;

    public WizardPage(String title, String subtitle) {
        createWizard(title, subtitle, false, new ArrayList<String>(), "");
    }

    public WizardPage(String title, String subtitle, boolean spinnerVisible, ArrayList<String> data, String preferenceKey) {
        createWizard(title, subtitle, spinnerVisible, data, preferenceKey);
    }

    private void createWizard(String title, String subtitle, boolean spinnerVisible, ArrayList<String> values, String preferenceKey) {
        this.title = title;
        this.subtitle = subtitle;
        this.spinnerVisible = spinnerVisible;
        this.values = values;
        this.preferenceKey = preferenceKey;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public boolean hasSpinner() {
        return this.spinnerVisible;
    }

    public String getPreferenceKey() {
        return preferenceKey;
    }
}