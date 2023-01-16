package dtos;

import entities.Location;
import entities.Match;

import java.util.List;

public class MatchDTO {

    private List<String> players;
    private String opponent;

    private String judge;

    private String type;

    private boolean inDoors;

    private Location location;

    public MatchDTO(Match match) {
        this.opponent = match.getOpponent();
        this.judge = match.getJudge();
        this.type = match.getType();
        this.inDoors = match.isInDoors();
        this.players = match.getPlayersAsStrings();
    }

    public MatchDTO(String opponent, String judge, String type, boolean inDoors, Location location) {
        this.opponent = opponent;
        this.judge = judge;
        this.type = type;
        this.inDoors = inDoors;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
