package myunihockey.ffhs.com.myunihockey.persistence.mapper;

import android.os.AsyncTask;

public class NetworkActivity extends AsyncTask<String, String, String> {
    public static final String WIFI = "Wi-Fi";
    public static final String ANY = "Any";
    private static final String URL = "http://stackoverflow.com/feeds/tag?tagnames=android&sort=newest";

    // Whether there is a Wi-Fi connection.
    private static boolean wifiConnected = false;
    // Whether there is a mobile connection.
    private static boolean mobileConnected = false;
    // Whether the display should be refreshed.
    public static boolean refreshDisplay = true;
    public static String sPref = null;


    // Uses AsyncTask to download the XML feed from stackoverflow.com.
    public void loadPage() {

        if ((sPref.equals(ANY)) && (wifiConnected || mobileConnected)) {
            //new DownloadXmlTask().execute(URL);
        } else if ((sPref.equals(WIFI)) && (wifiConnected)) {
            // new DownloadXmlTask().execute(URL);
        } else {
            // show error
        }
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }
}