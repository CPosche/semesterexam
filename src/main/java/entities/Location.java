package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Location")
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "location_id")
    private int id;

    @NotNull
    @Column(name = "location_address")
    private String address;

    @NotNull
    @Column(name = "location_city")
    private String city;

    @NotNull
    @Column(name = "location_condition")
    private String condition;

    @OneToMany(mappedBy = "location")
    private Set<Match> matchList = new LinkedHashSet<>();

    public Location() {
    }

    public Location(String address, String city, String condition) {
        this.address = address;
        this.city = city;
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Set<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(Set<Match> matchList) {
        this.matchList = matchList;
    }


}
