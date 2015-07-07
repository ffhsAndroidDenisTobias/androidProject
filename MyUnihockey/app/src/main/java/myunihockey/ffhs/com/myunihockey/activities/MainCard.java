package myunihockey.ffhs.com.myunihockey.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.R;
import myunihockey.ffhs.com.myunihockey.cards.GameAdapter;
import myunihockey.ffhs.com.myunihockey.persistence.dto.Club;
import myunihockey.ffhs.com.myunihockey.persistence.dto.ClubDataSource;
import myunihockey.ffhs.com.myunihockey.persistence.dto.Game;
import myunihockey.ffhs.com.myunihockey.persistence.dto.GameDataSource;
import myunihockey.ffhs.com.myunihockey.persistence.dto.Team;
import myunihockey.ffhs.com.myunihockey.persistence.dto.TeamDataSource;
import myunihockey.ffhs.com.myunihockey.rest.RestConnector;
import myunihockey.ffhs.com.myunihockey.rest.UnihockeyRestFactory;


public class MainCard extends Activity {

    private RecyclerView recList;
    private List<Game> gameList;

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpagecard);
        recList = (RecyclerView) findViewById(R.id.cardList);
        gameList = new ArrayList<Game>();
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        GameTask asyncTask = new GameTask();
        asyncTask.execute();

    }

    private void setGames() {


        GameAdapter ca = new GameAdapter(getGameList());
        recList.setAdapter(ca);
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


    class GameTask extends AsyncTask<String, String, String> {

        ProgressDialog progDailog;

        @Override
        protected void onPreExecute() {
            Log.d("onPreExecute", "start of onPreExecute");
            super.onPreExecute();
            progDailog = new ProgressDialog(MainCard.this);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
        }

        @Override
        protected String doInBackground(String... aurl) {
            Log.d("doInBackground", "start of doInBackground");
            publishProgress("Games loading");


            setGameList(getGames());

            publishProgress("Games loaded");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            progDailog.setMessage(values[0]);
        }

        @Override
        protected void onPostExecute(String unused) {
            Log.d("onPostExecute", "start of onPostExecute");
            super.onPostExecute(unused);
            setGames();
            progDailog.dismiss();
        }
    }

    private List<Game> getGames() {
        //Read from Database
        Log.d("getGames", "load Games from Database");

        GameDataSource cds = new GameDataSource(this);
        return cds.getAllGames();


    }
}
