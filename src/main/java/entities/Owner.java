package entities;

import security.entities.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "owner")
public class Owner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "owner_id")
    private int id;

    @NotNull
    @Column(name = "owner_name")
    private String name;

    @NotNull
    @Column(name = "owner_address")
    private String address;

    @NotNull
    @Column(name = "owner_phone")
    private String phone;

    @JoinTable(name = "owner_has_boat", joinColumns = {
            @JoinColumn(name = "FK_owner_id", referencedColumnName = "owner_id")}, inverseJoinColumns = {
            @JoinColumn(name = "FK_boat_id", referencedColumnName = "boat_id")})
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Boat> boatList = new LinkedHashSet<>();

    public Owner() {
    }

    public Owner(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Boat> getBoatList() {
        return boatList;
    }

    public void setBoatList(Set<Boat> boatList) {
        this.boatList = boatList;
    }

    public void addBoat(Boat boat) {
        boatList.add(boat);
        boat.getOwnerList().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;
        Owner owner = (Owner) o;
        return getId() == owner.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
