package myunihockey.ffhs.com.myunihockey.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.R;
import myunihockey.ffhs.com.myunihockey.activities.MainCard;
import myunihockey.ffhs.com.myunihockey.persistence.dto.Club;
import myunihockey.ffhs.com.myunihockey.persistence.dto.ClubDataSource;
import myunihockey.ffhs.com.myunihockey.persistence.dto.Game;
import myunihockey.ffhs.com.myunihockey.persistence.dto.GameDataSource;
import myunihockey.ffhs.com.myunihockey.persistence.dto.Team;
import myunihockey.ffhs.com.myunihockey.persistence.dto.TeamDataSource;
import myunihockey.ffhs.com.myunihockey.rest.RestConnector;
import myunihockey.ffhs.com.myunihockey.rest.UnihockeyRestFactory;

/**
 * Created by Denis Bittante on 22.04.2015.
 */
public class UnihockeyDataService extends Service {

    private static final String TAG = "UnihockeyDataService";
    private NotificationManager mNM;
    private int NOTIFICATION = R.string.local_service_started;


    public class UnihockeyDataBinder extends Binder {

        public UnihockeyDataService getService() {
            return UnihockeyDataService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        showNotification();

        onStartCommand(null, 0, 0);
    }

    private void showNotification() {


        CharSequence text = getText(R.string.local_service_started);


        Notification notification = new Notification(R.drawable.floorballlogo, text,
                System.currentTimeMillis());

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainCard.class), 0);

        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(this, getText(R.string.local_service_label),
                text, contentIntent);

        // Send the notification.
        mNM.notify(NOTIFICATION, notification);
    }

    private final IBinder mUnihockeyDataBinder = new UnihockeyDataBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mUnihockeyDataBinder;
    }


    @Override
    public void onDestroy() {
        mNM.cancel(NOTIFICATION);
        Toast.makeText(this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand");


        // new Thread(new TeamsAndClubsRunnable(UnihockeyDataService.this));

        //Creating new thread for my service
        //Always write your long running tasks in a separate thread, to avoid ANR
        new Thread(new ClubRunnable(UnihockeyDataService.this)).start();

        return Service.START_STICKY;
    }


    class TeamsAndClubsRunnable implements Runnable {

        private Context context;

        TeamsAndClubsRunnable(Context context) {
            this.context = context;

        }


        @Override
        public void run() {


        }

    }

    class ClubRunnable implements Runnable {

        private Context context;

        ClubRunnable(Context context) {
            this.context = context;

        }

        @Override
        public void run() {

            ClubDataSource clubDataSource = new ClubDataSource(context);
            int count = clubDataSource.getCount();
            if (count < 5) {
                loadClubs();
            }

            TeamDataSource teamDataSource = new TeamDataSource(context);
            int tcount = teamDataSource.getCount();

            if (tcount < 5) {
                loadTeams();
            }


            stopSelf();


        }

        private void loadClubs() {
            ClubDataSource clubDataSource = new ClubDataSource(context);
            Log.i("UnihockeyDataService", "Start loading Clubs");
            UnihockeyRestFactory unihockeyRestFactory = new UnihockeyRestFactory();
            URI allClubs = unihockeyRestFactory.getAllClubs();
            try {
                InputStream inputStream = new RestConnector().callRest(allClubs);
                clubDataSource.insertClub(inputStream);
            } catch (IOException e) {
                Log.e("Error Clubs load", "A problem occured while loading Clubs", e);
            }

            Log.i("UnihockeyDataService", "Finished loading Clubs");
        }


        public void loadTeams() {

            Log.d("TestStartService", context.getPackageName());

            TeamDataSource teamDataSource = new TeamDataSource(context);
            ClubDataSource clubDataSource = new ClubDataSource(context);


            UnihockeyRestFactory unihockeyRestFactory = new UnihockeyRestFactory();
            List<Club> allClubs1 = null;
            try {
                // Loads all Clubs

                allClubs1 = clubDataSource.getAllClubs();
                for (Club c : allClubs1) {

                    URI teamsByClubId = unihockeyRestFactory.getTeamsByClubId(String.valueOf(c.getId()));
                    // Loads all Teams
                    teamDataSource.insertTeam(new RestConnector().callRest(teamsByClubId));
                }
                List<Team> allTeams = teamDataSource.getAllTeams();


            } catch (IOException e) {
                e.printStackTrace();

            }
            // List<Game> allGames = dataSource.getAllGames();
        }

    }
}
