package myunihockey.ffhs.com.myunihockey;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import myunihockey.ffhs.com.myunihockey.binder.UnihockeyDataBinder;
import myunihockey.ffhs.com.myunihockey.services.UnihockeyDataService;

import static android.app.PendingIntent.getActivity;


public class InitialStartActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectToService();

        setContentView(R.layout.activity_initial_start);

        //spinner:
        Spinner spinner = (Spinner) findViewById(R.id.clubs_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.clubs_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        final Button button = (Button) findViewById(R.id.btnSubmit);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_initial_start, menu);
        return true;
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


    private static Handler callback= new Handler();

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