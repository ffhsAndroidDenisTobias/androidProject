package myunihockey.ffhs.com.myunihockey.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import myunihockey.ffhs.com.myunihockey.R;
import myunihockey.ffhs.com.myunihockey.cards.GameAdapter;
import myunihockey.ffhs.com.myunihockey.persistence.dto.Game;


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


        GameAdapter ca = new GameAdapter(createList(30));
        recList.setAdapter(ca);


    }


    private List createList(int size) {

        List result = new ArrayList();
        for (int i = 1; i <= size; i++) {
            Game ci = new Game();
            ci.setAwayteam_name("AwayTeam_" + i);
            ci.setHometeam_name("Hometeam" + i);
            ci.setDate("12.14.201" + i);
            ci.setGoalsaway(i);
            ci.setGoalshome(i + 1);
            ci.setPlayed((i % 2 >= 1) ? true : false);
            result.add(ci);

        }

        return result;
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
