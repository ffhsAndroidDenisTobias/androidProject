package myunihockey.ffhs.com.myunihockey.persistence;

/**
 * Created by Joker on 01.05.2015.
 */
public class League {

    private int id;
    private String leaguetext;
    private String fieldsize;
    private String wertungsmodus_id;
    private int group_id;

    public League (int id, int group_id, String leaguetext, String fieldsize, String wertungsmodus_id){
        this.id = id;
        this.leaguetext = leaguetext;
        this.fieldsize = fieldsize;
        this.wertungsmodus_id = wertungsmodus_id;
        this.group_id = group_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getWertungsmodus_id() {
        return wertungsmodus_id;
    }

    public void setWertungsmodus_id(String wertungsmodus_id) {
        this.wertungsmodus_id = wertungsmodus_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

}
