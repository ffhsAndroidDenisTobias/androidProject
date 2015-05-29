package myunihockey.ffhs.com.myunihockey.persistence.dto;

/**
 * Created by Tobi on 20.05.2015.
 */
public class DTOPersistanceProvider{

    public String insertGame(Game game){
        String statement = "INSERT INTO games (id, leaguecode, group, played, goalshome, goalsaway, hometeam_id, awayteam_id, hometeam_name, awayteam_name, date, time) "
                + "VALUES ('" + game.getId()
                + "', '" + game.getLeaguecode()
                + "', '" + game.getGroup()
                + "', '" + game.isPlayed()
                + "', '" + game.getGoalshome()
                + "', '" + game.getGoalsaway()
                + "', '" + game.getHometeam_id()
                + "', '" + game.getAwayteam_id()
                + "', '" + game.getHometeam_name()
                + "', '" + game.getAwayteam_name()
                + "', '" + game.getDate()
                + "', '" + game.getTime()
                + "');";
        return statement;
    }
    public String insertTeam(Team team){
        String statement = "INSERT INTO teams (id, teamName, club_id, leaguetext, group, grouptext, leaguecode)"
                + "VALUES ('" + team.getId()
                + "', '" + team.getTeamName()
                + "', '" + team.getClub_id()
                + "', '" + team.getLeaguetext()
                + "', '" + team.getGroup()
                + "', '" + team.getGrouptext()
                + "', '" + team.getLeaguetext()
                + "', '" + team.getLeaguecode()
                + "');";
        return statement;
    }


}
