package dtos;

import entities.Match;

import java.util.List;

public class MatchDTO {

    private List<String> players;
    private String opponent;

    private String judge;

    private String type;

    private boolean inDoors;

    public MatchDTO(Match match) {
        this.opponent = match.getOpponent();
        this.judge = match.getJudge();
        this.type = match.getType();
        this.inDoors = match.isInDoors();
        this.players = match.getPlayersAsStrings();
    }

    public String getOpponent() {
        return opponent;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isInDoors() {
        return inDoors;
    }

    public void setInDoors(boolean inDoors) {
        this.inDoors = inDoors;
    }
}
