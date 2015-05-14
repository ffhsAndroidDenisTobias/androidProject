package myunihockey.ffhs.com.myunihockey.persistence.dto;

/**
 * Created by Joker on 01.05.2015.
 */
public class Team {

    private int id;
    private String teamName;
    private int club_id;
    private String leaguetext;
    private int group;
    private String grouptext;
    private int leaguecode;

    public Team(String teamName, String leaguetext, String grouptext, int id, int club_id, int group, int leaguecode) {
        this.teamName = teamName;
        this.leaguetext = leaguetext;
        this.grouptext = grouptext;
        this.id = id;
        this.club_id = club_id;
        this.group = group;
        this.leaguecode = leaguecode;
    }

    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getClub_id() {
        return club_id;
    }

    public String getLeaguetext() {
        return leaguetext;
    }

    public int getGroup() {
        return group;
    }

    public String getGrouptext() {
        return grouptext;
    }

    public int leaguecode() {
        return leaguecode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public void setLeaguetext(String leaguetext) {
        this.leaguetext = leaguetext;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setGrouptext(String grouptext) {
        this.grouptext = grouptext;
    }

    public void setLeaguecode(int leaguecode) {
        this.leaguecode = leaguecode;
    }

}
