package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Player")
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "player_id")
    private int id;

    @NotNull
    @Column(name = "player_name")
    private String name;

    @NotNull
    @Column(name = "player_phone")
    private String phone;

    @NotNull
    @Column(name = "player_email")
    private String email;

    @NotNull
    @Column(name = "player_status")
    private String status;

    @ManyToMany(mappedBy = "playerList")
    private Set<Match> matchList = new LinkedHashSet<>();

    public Player() {
    }

    public Player(String name, String phone, String email, String status) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    public Set<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(Set<Match> matchList) {
        this.matchList = matchList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
