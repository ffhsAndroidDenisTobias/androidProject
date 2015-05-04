package myunihockey.ffhs.com.myunihockey.persistence.dto;

/**
 * Created by Joker on 01.05.2015.
 */
public class Team_has_game {
    private int team_id;
    private int game_id;

    public Team_has_game(int team_id, int game_id) {
        this.team_id = team_id;
        this.game_id = game_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }
}
