package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Matches")
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "match_id")
    private int id;

    @Column(name = "match_opponent")
    private String opponent;

    @NotNull
    @Column(name = "match_judge")
    private String judge;

    @NotNull
    @Column(name = "match_type")
    private String type;

    @NotNull
    @Column(name = "match_inDoors")
    private boolean inDoors;

    @JoinTable(name = "match_has_player", joinColumns = {
            @JoinColumn(name = "FK_match_id", referencedColumnName = "match_id")}, inverseJoinColumns = {
            @JoinColumn(name = "FK_player_id", referencedColumnName = "player_id")})
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Player> playerList = new LinkedHashSet<>();

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_location_id")
    private Location location;

    public Match() {
    }

    public Match(String opponent, String judge, String type, boolean inDoors) {
        this.opponent = opponent;
        this.judge = judge;
        this.type = type;
        this.inDoors = inDoors;
    }

    public List<String> getPlayersAsStrings() {
        if (playerList.isEmpty()) {
            return null;
        }
        List<String> playersAsStrings = new ArrayList<>();
        playerList.forEach((player) -> {
            playersAsStrings.add(player.getName());
        });
        return playersAsStrings;
    }

    public int getId() {
        return id;
    }

    public Set<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Set<Player> playerList) {
        this.playerList = playerList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpponent() {
        return opponent;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void addLocation(Location location) {
        this.location = location;
        location.getMatchList().add(this);
    }

    public void removeLocation(Location location) {
        this.location = null;
        location.getMatchList().remove(this);
    }

    public void addPlayer(Player player) {
        this.playerList.add(player);
        player.getMatchList().add(this);
    }
}
