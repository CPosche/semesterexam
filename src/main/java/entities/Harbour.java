package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "harbour")
public class Harbour implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "harbour_id")
    private int id;

    @NotNull
    @Column(name = "harbour_name")
    private String name;

    @NotNull
    @Column(name = "harbour_address")
    private String address;

    @NotNull
    @Column(name = "harbour_cap")
    private int capacity;

    @OneToMany(mappedBy = "harbour")
    private Set<Boat> boatList = new LinkedHashSet<>();

    public Harbour() {
    }

    public Harbour(String name, String address, int capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Boat> getBoatList() {
        return boatList;
    }

    public void setBoatList(Set<Boat> boatList) {
        this.boatList = boatList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Harbour)) return false;
        Harbour harbour = (Harbour) o;
        return getId() == harbour.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
