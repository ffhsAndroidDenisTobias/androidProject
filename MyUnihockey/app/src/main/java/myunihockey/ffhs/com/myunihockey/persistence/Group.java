package myunihockey.ffhs.com.myunihockey.persistence;

/**
 * Created by Joker on 01.05.2015.
 */
public class Group {


    private int id;
    private String leaguetype;
    private String leaguetext;
    private String fieldsize;
    private String wertungsmodus;
    private int wertungsmodus_id;
    private int leaguecode;

    public Group (int id, int wertungsmodus_id, int leaguecode, String leaguetext, String leaguetype, String fieldsize, String wertungsmodus){
        this.leaguetype = leaguetype;
        this.leaguetext = leaguetext;
        this.fieldsize = fieldsize;
        this.wertungsmodus = wertungsmodus;
        this.id = id;
        this.wertungsmodus_id = wertungsmodus_id;
        this.leaguecode = leaguecode;
    }

    public int getId() {
        return id;
    }

    public String getLeaguetype() {
        return leaguetype;
    }

    public String getLeaguetext() {
        return leaguetext;
    }

    public String getFieldsize() {
        return fieldsize;
    }

    public String getWertungsmodus() {
        return wertungsmodus;
    }

    public int getWertungsmodus_id() {
        return wertungsmodus_id;
    }

    public int getLeaguecode() {
        return leaguecode;
    }

    public void setLeaguecode(int leaguecode) {
        this.leaguecode = leaguecode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLeaguetype(String leaguetype) {
        this.leaguetype = leaguetype;
    }

    public void setLeaguetext(String leaguetext) {
        this.leaguetext = leaguetext;
    }

    public void setFieldsize(String fieldsize) {
        this.fieldsize = fieldsize;
    }

    public void setWertungsmodus(String wertungsmodus) {
        this.wertungsmodus = wertungsmodus;
    }

    public void setWertungsmodus_id(int wertungsmodus_id) {
        this.wertungsmodus_id = wertungsmodus_id;
    }
}
