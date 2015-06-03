package myunihockey.ffhs.com.myunihockey.activities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpagecard);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        Service s = new Service(this);
        s.doInBackground(recList);

    }


    private class Service extends AsyncTask<RecyclerView, String, String>

    {

        Context context;

        public Service(Context context) {
            this.context = context;
        }


        @Override
        protected String doInBackground(RecyclerView... recList) {

            RecyclerView recycleView = recList[0];

            GameDataSource dataSource = new GameDataSource(this.context);
            TeamDataSource teamDataSource = new TeamDataSource(this.context);
            ClubDataSource clubDataSource = new ClubDataSource(this.context);


            UnihockeyRestFactory unihockeyRestFactory = new UnihockeyRestFactory();

            try {

                URI allClubs = unihockeyRestFactory.getAllClubs();
                clubDataSource.insertClub(new RestConnector().callRest(allClubs));
                List<Club> allClubs1 = clubDataSource.getAllClubs();
                URI teamsByClubId = unihockeyRestFactory.getTeamsByClubId(String.valueOf(allClubs1.get(0).getId()));

                teamDataSource.insertTeam(new RestConnector().callRest(teamsByClubId));
                List<Team> allTeams = teamDataSource.getAllTeams();

                //TODO: Load all Teams-Games save them and load them again. like above.
                //TODO: Return all games

            } catch (IOException e) {
                e.printStackTrace();

            }
            List<Game> allGames = dataSource.getAllGames();

            GameAdapter ca = new GameAdapter(allGames);
            recycleView.setAdapter(ca);

            return "finished";
        }

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
}
