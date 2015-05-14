package myunihockey.ffhs.com.myunihockey.binder;

import android.os.Binder;
import android.os.Handler;

/**
 * Created by Denis Bittante on 22.04.2015.
 */
public class UnihockeyDataBinder extends Binder {

    private Handler callback;


    public void setHandler(final Handler callback) {
        this.callback = callback;
    }


}
