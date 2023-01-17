package dtos;

import entities.Location;

public class LocationDTO {

    private String address;

    private String city;

    private String condition;

    public LocationDTO(Location location) {
        this.address = location.getAddress();
        this.city = location.getCity();
        this.condition = location.getCondition();
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
}
