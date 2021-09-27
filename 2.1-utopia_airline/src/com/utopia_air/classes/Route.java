package com.utopia_air.classes;

public class Route {

    private Integer id;
    private String origin_id;
    private String destination_id;

    public Route() {};

    public Route(String orig, String dest) {
        this.origin_id = orig;
        this.destination_id = dest;
    }

    public Route(Integer ID, String orig, String dest) {
        this.id = ID;
        this.origin_id = orig;
        this.destination_id = dest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(String origin_id) {
        this.origin_id = origin_id;
    }

    public String getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(String destination_id) {
        this.destination_id = destination_id;
    }

    public String toString() {
        return String.format("Route ID: %d\t|\sOrigin: %s --> Destination: %s",
                this.id, this.origin_id, this.destination_id);
    }

}
