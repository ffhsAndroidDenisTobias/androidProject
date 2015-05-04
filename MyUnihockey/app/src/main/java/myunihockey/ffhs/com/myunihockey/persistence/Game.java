package myunihockey.ffhs.com.myunihockey.persistence;

/**
 * Created by Joker on 01.05.2015.
 */
public class Game {

    private int id;
    private int leaguecode;
    private String group;
    private String season;
    private String leaguetype;
    private String leaguetext;
    private String fieldsize;
    private boolean played;
    private int goalshome;
    private int goalsaway;
    private boolean overtime;
    private boolean penaltyshooting;
    private boolean forfait;
    private boolean canceled;
    private String organizer;
    private String organizer_id;
    private String eventtype;
    private String hometeam_id;
    private String awayteam_id;
    private String hometeam_name;
    private String awayteam_name;

    public Game(int id, int leaguecode, String group, String season, String leaguetype, String leaguetext, String fieldsize, String organizer, String organizer_id, String eventtype, String hometeam_id, String hometeam_name, String awayteam_id, String awayteam_name, int goalshome, int goalsaway) {
        this.id = id;
        this.leaguecode = leaguecode;
        this.group = group;
        this.season = season;
        this.leaguetype = leaguetype;
        this.leaguetext = leaguetext;
        this.fieldsize = fieldsize;
        this.organizer = organizer;
        this.organizer_id = organizer_id;
        this.eventtype = eventtype;
        this.hometeam_id = hometeam_id;
        this.hometeam_name = hometeam_name;
        this.awayteam_id = awayteam_id;
        this.awayteam_name = awayteam_name;
        this.goalshome = goalshome;
        this.goalsaway = goalsaway;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeaguecode() {
        return leaguecode;
    }

    public void setLeaguecode(int leaguecode) {
        this.leaguecode = leaguecode;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getLeaguetype() {
        return leaguetype;
    }

    public void setLeaguetype(String leaguetype) {
        this.leaguetype = leaguetype;
    }

    public String getLeaguetext() {
        return leaguetext;
    }

    public void setLeaguetext(String leaguetext) {
        this.leaguetext = leaguetext;
    }

    public String getFieldsize() {
        return fieldsize;
    }

    public void setFieldsize(String fieldsize) {
        this.fieldsize = fieldsize;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public int getGoalshome() {
        return goalshome;
    }

    public void setGoalshome(int goalshome) {
        this.goalshome = goalshome;
    }

    public int getGoalsaway() {
        return goalsaway;
    }

    public void setGoalsaway(int goalsaway) {
        this.goalsaway = goalsaway;
    }

    public boolean isOvertime() {
        return overtime;
    }

    public void setOvertime(boolean overtime) {
        this.overtime = overtime;
    }

    public boolean isPenaltyshooting() {
        return penaltyshooting;
    }

    public void setPenaltyshooting(boolean penaltyshooting) {
        this.penaltyshooting = penaltyshooting;
    }

    public boolean isForfait() {
        return forfait;
    }

    public void setForfait(boolean forfait) {
        this.forfait = forfait;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(String organizer_id) {
        this.organizer_id = organizer_id;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public String getHometeam_id() {
        return hometeam_id;
    }

    public void setHometeam_id(String hometeam_id) {
        this.hometeam_id = hometeam_id;
    }

    public String getAwayteam_id() {
        return awayteam_id;
    }

    public void setAwayteam_id(String awayteam_id) {
        this.awayteam_id = awayteam_id;
    }

    public String getHometeam_name() {
        return hometeam_name;
    }

    public void setHometeam_name(String hometeam_name) {
        this.hometeam_name = hometeam_name;
    }

    public String getAwayteam_name() {
        return awayteam_name;
    }

    public void setAwayteam_name(String awayteam_name) {
        this.awayteam_name = awayteam_name;
    }
}
