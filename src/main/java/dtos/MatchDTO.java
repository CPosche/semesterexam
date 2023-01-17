package dtos;

import entities.Location;
import entities.Match;

import java.util.List;

public class MatchDTO {

    private int id;

    private List<String> players;
    private String opponent;

    private String judge;

    private String type;

    private boolean inDoors;

    private LocationDTO locationDTO;

    public MatchDTO(Match match) {
        this.id = match.getId();
        this.opponent = match.getOpponent();
        this.judge = match.getJudge();
        this.type = match.getType();
        this.inDoors = match.isInDoors();
        this.players = match.getPlayersAsStrings();
    }

    public MatchDTO(Match match, Location location) {
        this.id = match.getId();
        this.opponent = match.getOpponent();
        this.judge = match.getJudge();
        this.type = match.getType();
        this.inDoors = match.isInDoors();
        this.players = match.getPlayersAsStrings();
        this.locationDTO = new LocationDTO(location);
    }

    public MatchDTO(String opponent, String judge, String type, boolean inDoors, LocationDTO locationDTO) {
        this.opponent = opponent;
        this.judge = judge;
        this.type = type;
        this.inDoors = inDoors;
        this.locationDTO = locationDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
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
