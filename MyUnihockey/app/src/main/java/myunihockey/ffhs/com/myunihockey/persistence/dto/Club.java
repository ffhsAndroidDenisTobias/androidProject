package myunihockey.ffhs.com.myunihockey.persistence.dto;

/**
 * Created by Joker on 01.05.2015.
 */
public class Club {

    private int id;
    private String clubName;

    public Club(int id, String clubName) {
        this.id = id;
        this.clubName = clubName;
    }

    public Club() {
    }

    public int getId() {
        return id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
