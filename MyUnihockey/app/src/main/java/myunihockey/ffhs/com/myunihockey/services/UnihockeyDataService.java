package myunihockey.ffhs.com.myunihockey.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import myunihockey.ffhs.com.myunihockey.binder.UnihockeyDataBinder;

/**
 * Created by Denis Bittante on 22.04.2015.
 */
public class UnihockeyDataService extends Service {

    private Handler callback;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mUnihockeyDataBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        callback=null;
        return super.onUnbind(intent);
    }

    private final IBinder mUnihockeyDataBinder = new UnihockeyDataBinder();






}
