package dtos;

import entities.Owner;

public class OwnerDTO {

    private String name;

    private String address;

    private String phone;

    private int boats;

    public OwnerDTO(Owner owner, long boats) {
        this.name = owner.getName();
        this.address = owner.getAddress();
        this.phone = owner.getPhone();
        this.boats = (int) boats;
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

    public int getBoats() {
        return boats;
    }

    public void setBoats(int boats) {
        this.boats = boats;
    }
}
