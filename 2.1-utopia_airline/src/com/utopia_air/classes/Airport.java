package com.utopia_air.classes;

public class Airport {

    private String iata_id;
    private String city;

    public Airport() {};

    public Airport(String iata_ID, String city) {
        this.iata_id = iata_ID;
        this.city = city;
    }

    public String getIata_id() {
        return iata_id;
    }

    public void setIata_id(String iata_id) {
        this.iata_id = iata_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("%s --> %s", iata_id, city);
    }
}
