package entities;

import security.entities.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "boat")
public class Boat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "boat_id")
    private int id;

    @NotNull
    @Column(name = "boat_brand")
    private String brand;

    @NotNull
    @Column(name = "boat_make")
    private String make;

    @NotNull
    @Column(name = "boat_name")
    private String name;

    @NotNull
    @Column(name = "boat_image")
    private String image;

    @ManyToMany(mappedBy = "boatList")
    private Set<Owner> ownerList = new LinkedHashSet<>();

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_harbour_id")
    private Harbour harbour;

    public Boat() {
    }

    public Boat(String brand, String make, String name, String image) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(Set<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    public Harbour getHarbour() {
        return harbour;
    }

    public void setHarbour(Harbour harbour) {
        this.harbour = harbour;
    }

    public void addHabour(Harbour harbour) {
        this.harbour = harbour;
        harbour.getBoatList().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Boat)) return false;
        Boat boat = (Boat) o;
        return getId() == boat.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
