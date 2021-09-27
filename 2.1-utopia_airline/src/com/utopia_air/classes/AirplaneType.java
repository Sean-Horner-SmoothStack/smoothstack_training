package com.utopia_air.classes;

public class AirplaneType {

    private Integer id;
    private Integer max_capacity;

    public AirplaneType() {};

    public AirplaneType(Integer ID, Integer max_cap) {
        this.id = ID;
        this.max_capacity = max_cap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(Integer max_capacity) {
        this.max_capacity = max_capacity;
    }

    public String toString() {
        return String.format("Type %d --> Max Cap: %d", this.id, this.max_capacity);
    }
}
