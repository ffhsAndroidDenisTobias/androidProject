package myunihockey.ffhs.com.myunihockey.persistence;

/**
 * Created by Joker on 01.05.2015.
 */
public class Club {

    private int id;
    private String clubName;

    public Club (int id, String clubName){
        this.id = id;
        this.clubName = clubName;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getClubName() {
        return clubName;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
