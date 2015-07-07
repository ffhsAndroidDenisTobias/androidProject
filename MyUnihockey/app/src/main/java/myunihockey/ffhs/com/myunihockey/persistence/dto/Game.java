package myunihockey.ffhs.com.myunihockey.persistence.dto;

import java.util.Date;

/**
 * Created by Joker on 01.05.2015.
 */
public class Game {

    private int id;
    private int leaguecode;
    private String group;
    private boolean played;
    private int goalshome;
    private int goalsaway;
    private String hometeam_id;
    private String awayteam_id;
    private String hometeam_name;
    private String awayteam_name;
    private String date;
    private String time;
    private String organizer;
    private String organizer_id;
    private String place;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }
    public void setPlayed(String played) {
        if (played.equals("false")){
            this.played = false;
        }else if(played.equals("true")){
            this.played = true;
        }
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setOrganizer (String organizer) { this.organizer = organizer; }

    public String getOrganizer () { return this.organizer; }

    public void setOrganizer_id (String organizer_id) { this.organizer_id = organizer_id; }

    public String getOrganizer_id () { return this.organizer_id; }

    public Game(int id, int leaguecode, String group, String hometeam_id, String hometeam_name, String awayteam_id, String awayteam_name, int goalshome, int goalsaway, String date, String time, String organizer, String organizer_id, String place) {
        this.id = id;

        this.leaguecode = leaguecode;
        this.group = group;
        this.hometeam_id = hometeam_id;
        this.hometeam_name = hometeam_name;
        this.awayteam_id = awayteam_id;
        this.awayteam_name = awayteam_name;
        this.goalshome = goalshome;
        this.goalsaway = goalsaway;
        this.date = date;
        this.time = time;
        this.organizer = organizer;
        this.organizer_id = organizer_id;
        this.place = place;
    }

    public Game(){};



}
